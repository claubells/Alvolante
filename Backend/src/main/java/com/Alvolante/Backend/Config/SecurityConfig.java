package com.Alvolante.Backend.Config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
// habilita la anotación @PreAuthorize q permite proteger métodos específicos con roles o permisos.
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CorsConfig corsConfig;

    public SecurityConfig(JwtFilter jwtFilter, CorsConfig corsConfig) {
        this.jwtFilter = jwtFilter;
        this.corsConfig = corsConfig;
    }

    @Bean
    //define las reglas de seguridad, endpoints publicos y cuales necesitan autenticación
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Configurando seguridad...");
        http
                // Deshabilitar CSRF porque es una API
                .csrf(csrf -> csrf.disable())

                // Configurar CORS
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) // Uso correcto del método

                // Configuración de rutas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll() // Permite acceso sin autenticación a /auth/**
                        .requestMatchers("/usuario/**").hasRole("CLIENTE")//Acceso exclusivo para clientes
                        .requestMatchers("/api/**").hasAnyRole("ADMIN", "TRABAJADOR", "CLIENTE")
                        .anyRequest().authenticated() // Requiere autenticación para las demás rutas
                )
                // Configurar política de sesiones stateless
                .sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))

                // Configurar manejador de acceso denegado directamente
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            // Obtener roles requeridos y roles actuales
                            String requiredRoles = "ADMIN, TRABAJADOR"; // Cambia según la lógica de tus rutas
                            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                            String currentRoles = authentication != null
                                    ? authentication.getAuthorities().toString()
                                    : "No autenticado";

                            String requestUri = request.getRequestURI();

                            // Mostrar en consola usando printf
                            System.out.printf("Acceso denegado:\n");
                            System.out.printf("  Ruta solicitada: %s\n", requestUri);
                            System.out.printf("  Roles actuales del usuario: %s\n", currentRoles);
                            System.out.printf("  Mensaje de excepción: %s\n", accessDeniedException.getMessage());


                            // Mostrar en la consola de IntelliJ
                            System.err.println("Acceso denegado:");
                            System.err.println("Ruta solicitada: " + requestUri);
                            System.err.println("Roles actuales del usuario: " + currentRoles);
                            System.err.println("Mensaje de excepción: " + accessDeniedException.getMessage());

                            // Respuesta al cliente
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Acceso denegado. No tiene permisos para acceder a este recurso.\"}");
                        })
                )

                // Agregar el filtro JWT antes del filtro de autenticación
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    // maneja el proceso de autenticación, spring lo configura de forma automatica, y se expone como un bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    // cifra las contraseñas antes de almacenarlas y compararlas en las autenticaciones
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
