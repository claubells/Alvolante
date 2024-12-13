package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {
    VehiculoEntity findByNumeroChasis(String chasis);
    VehiculoEntity findByPatente(String patente);

    List<VehiculoEntity> findAll();

    List<VehiculoEntity> findByDisponibilidadTrue();
}
