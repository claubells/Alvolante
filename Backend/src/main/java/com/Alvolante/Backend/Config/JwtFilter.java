package com.Alvolante.Backend.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    // el filtro intercepta todas las solicitudes entrantes y verifica si el token es valido
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener el encabezado Authorization
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Extraer el token
            try {
                // Intentar extraer el username del token
                username = jwtUtil.getEmail(token); // Aquí obtiene el "sub"
            } catch (Exception e) {
                // Si ocurre un error al procesar el token
                System.err.printf("Error al procesar el token: %s%n", e.getMessage());
                System.err.println("token: "+token);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write("{\"error\": \"Error al procesar el token.\"}");
                return;
            }
        }

        // Validar si el username es nulo o vacío
        if (username == null || username.isEmpty()) {
            System.err.println("Token no contiene un usuario válido.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.getWriter().write("{\"error\": \"Token no contiene un usuario válido.\"}");
            return; // Detener el procesamiento del filtro
        }

        // Configurar autenticación si el token es válido
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Crear el token de autenticación
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            System.out.println("Autenticación exitosa para el usuario: " + username);
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

}
