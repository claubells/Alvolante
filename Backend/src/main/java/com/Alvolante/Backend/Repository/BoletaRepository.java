package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.BoletaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * BoletaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad BoletaEntity.
 */
public interface BoletaRepository extends JpaRepository<BoletaEntity, Long> {

    /**
     * Encuentra una boleta por el nombre del cliente.
     *
     * @param nombreCliente El nombre del cliente.
     * @return La boleta correspondiente al nombre del cliente proporcionado.
     */
    BoletaEntity findByNombreCliente(String nombreCliente);

    /**
     * Encuentra todas las boletas.
     *
     * @return Una lista de todas las boletas.
     */
    List<BoletaEntity> findAll();
}
