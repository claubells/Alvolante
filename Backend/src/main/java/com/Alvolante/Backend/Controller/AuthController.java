package com.Alvolante.Backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.Alvolante.Backend.Config.JwtUtil;
import com.Alvolante.Backend.dto.RegisterDto;
import com.Alvolante.Backend.dto.LoginDto;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Entity.UsuarioEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Realizar login del usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        // se imprimen los datos recibidos en el back
        System.out.println("Datos recibidos en el back: "+loginDto);

        try{

            // Se autentica al usuario con los datos proporcionados:
            UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword());

            authenticationManager.authenticate(loginToken);

            //si es exitosa se genera JWT
            String jwt = jwtUtil.createToken(loginDto.getEmail());

            //se obtiene el usuario autenticado de la base de datos:
            UsuarioEntity user = usuarioRepository.findByEmail(loginDto.getEmail());
            if(user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Usuario no encontrado");
            }

            // se construye la respuesta con el token y la de idUsuario
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("userId", user.getIdUsuario());

            return ResponseEntity.ok(response); // Devolver el token y el ID del usuario

        }catch (BadCredentialsException e) {
            //401 el cliente no proporciona un token jwt valido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas.");
        }

    }

    //Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        // se verifica si el usuario ya existe:
        UsuarioEntity foundUser = usuarioRepository.findByEmail(registerDto.getEmail());
        if(foundUser != null){
            // 409 la solicitud no se puede completar por un conflicto(ya existe)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya se encuentra registrado.\n");
        }

        try {
            // se crea un nuevo usuario

            // Cifrar la contraseña
            String hashedPassword = passwordEncoder.encode(registerDto.getPassword());

            UsuarioEntity newUser = new UsuarioEntity(
                    registerDto.getRut(),
                    registerDto.getName(),
                    registerDto.getEmail(),
                    registerDto.getPhone(),
                    registerDto.getFechaDeNacimiento(),
                    hashedPassword,
                    registerDto.getRol());

            usuarioRepository.save(newUser);
            // 201 solicitud exitosa
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado con éxito.\n");

        } catch (Exception e) {
            // 500 error en la logica del backend
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno en el servidor.\n");
        }

    }

    @PostMapping("/check-token")
    public ResponseEntity<?> checkToken(@RequestBody String token) {
        try {
            if(token == null || !token.startsWith("Bearer")){
                // 403 el cliente no tiene permisos para acceder al recurso solicitado.
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El token no es valido.");
            }
            // se elimina el prefijo "Bearer"
            token = token.replace("Bearer ", "");
            boolean isValid = jwtUtil.isValid(token);

            if(!isValid){
                // 403 el cliente no tiene permisos para acceder al recurso solicitado.
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El token no es valido.");
            }
            return ResponseEntity.ok(Map.of("message","Token valido."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor.\n");
        }
    }


}
