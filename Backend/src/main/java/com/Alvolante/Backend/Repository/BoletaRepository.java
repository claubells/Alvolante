package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.BoletaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletaRepository extends JpaRepository<BoletaEntity, Long> {
    BoletaEntity findByNombreCliente(String nombreCliente);
    List<BoletaEntity> findAll();
}