package com.Alvolante.Backend.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration
public class JwtUtil {

    // Se encarga de generar, validar y extraer información del token JWT
    private static final String SECRET_KEY = "alvolante";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    // Método para crear un JWT
    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("tbd")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15))) // Duración del token
                .sign(ALGORITHM);
    }

    // Método para validar un JWT
    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true; // Si no lanza excepción, el token es válido
        } catch (JWTVerificationException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    // Verifica si el token ha expirado
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token)
                    .getExpiresAt();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // Si hay algun error, consideramos que el token no es valido
        }
    }

    // Método para extraer el email del JWT
    public String getEmail(String jwt) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM)
                .build()
                .verify(jwt);
        return decodedJWT.getSubject(); // Retorna el "subject" del token
    }

}
