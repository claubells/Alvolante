package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByEmail(String email);

    @Query("SELECT u.idUsuario FROM UsuarioEntity u WHERE u.email = :email")
    Long findIdByEmail(@Param("email") String email);
}