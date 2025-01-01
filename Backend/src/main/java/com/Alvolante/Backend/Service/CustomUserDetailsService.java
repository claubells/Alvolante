package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    // implementa la interfaz userDetailsService necesaria para integrar Sring Security

    // dependencia para interactuar con la base de datos y recuperar info del usuario
    private final UsuarioRepository usuarioRepository;

    // constructor q recibe el repositorio como dependencia(inyeccion de dependencia)
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // se busca al usuario con el el repositorio
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);

        if(usuario == null) {
            throw new UsernameNotFoundException("Email no encontrado en el sistema : " +email);
        }

        System.out.println("funcion: loadUserByUsername\nUsuario encontrado : " + usuario.getEmail());

        // si si existe se crea un objeto UserDetails utilizando la clase User de Spring Security
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }
}
