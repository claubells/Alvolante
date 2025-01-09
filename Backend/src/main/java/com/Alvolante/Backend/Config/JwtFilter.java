package com.Alvolante.Backend.Config;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;

/**
 * JwtFilter es un componente que intercepta todas las solicitudes entrantes y verifica si el token es válido.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    // el filtro intercepta todas las solicitudes entrantes y verifica si el token es valido

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    /**
     * Constructor para JwtFilter.
     *
     * @param jwtUtil Utilidad para manejo de tokens JWT.
     * @param userDetailsService Servicio para obtener detalles del usuario.
     */    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Método que filtra cada solicitud entrante.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @param filterChain La cadena de filtros.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException Si ocurre un error de E/S.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {



        String requestUri = request.getRequestURI();
        // Ignorar rutas públicas como /auth/**
        if (requestUri.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return; // Salir del filtro
        }

        // 1. Se obtiene el header Authorization
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Se extra el token del encabezado
        String jwt = authHeader.split(" ")[1].trim();

        // 2. Validar que el token sea válido
        if (!this.jwtUtil.isValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraer el correo del token
        String email = this.jwtUtil.getEmail(jwt);

        //4 se busca por email
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);


        // 5. Cargar al usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        // se añade el rol
                        userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("Autenticación exitosa: " +authenticationToken);

        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println("Usuario autenticado con roles: " + roles);

        filterChain.doFilter(request, response);
    }
}