package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.ExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ExtraRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad ExtraEntity.
 */
public interface ExtraRepository extends JpaRepository<ExtraEntity, Long> {

    /**
     * Encuentra un extra por su ID.
     *
     * @param id El ID del extra.
     * @return El extra correspondiente al ID proporcionado.
     */
    @Query("SELECT e FROM ExtraEntity e WHERE e.idExtra = id")
    ExtraEntity findExtraById(Long id);
}
