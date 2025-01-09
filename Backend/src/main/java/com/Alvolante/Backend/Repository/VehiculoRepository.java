package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * VehiculoRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad VehiculoEntity.
 */
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

    /**
     * Encuentra un vehículo por su número de chasis.
     *
     * @param chasis El número de chasis del vehículo.
     * @return El vehículo correspondiente al número de chasis proporcionado.
     */
    VehiculoEntity findByNumeroChasis(String chasis);

    /**
     * Encuentra un vehículo por su patente.
     *
     * @param patente La patente del vehículo.
     * @return El vehículo correspondiente a la patente proporcionada.
     */
    VehiculoEntity findByPatente(String patente);

    /**
     * Encuentra todos los vehículos.
     *
     * @return Una lista de todos los vehículos.
     */
    List<VehiculoEntity> findAll();

    /**
     * Encuentra todos los vehículos disponibles.
     *
     * @return Una lista de todos los vehículos disponibles.
     */
    List<VehiculoEntity> findByDisponibilidadTrue();

    /**
     * Encuentra una lista de vehículos únicos con menor kilometraje.
     *
     * @return Una lista de vehículos únicos disponibles ordenados por modelo y kilometraje.
     */
    @Query("SELECT v FROM VehiculoEntity v WHERE v.disponibilidad = true ORDER BY v.modelo, v.kilometraje")
    List<VehiculoEntity> findVehiculosUnicosConMenorKilometraje();
}
