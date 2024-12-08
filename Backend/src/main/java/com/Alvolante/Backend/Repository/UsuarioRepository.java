package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByEmail(String email);
    UsuarioEntity findByRut(String rut);
}
