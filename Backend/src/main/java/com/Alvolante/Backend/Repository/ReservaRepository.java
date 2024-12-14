package com.Alvolante.Backend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExtraRepository extends JpaRepository<ExtraRepository, Long> {
    VehiculoEntity findByNumeroChasis(String chasis);
    VehiculoEntity findByPatente(String patente);
    List<VehiculoEntity> findAll();
}
