package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SucursalRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad SucursalEntity.
 */
public interface SucursalRepository extends JpaRepository<SucursalEntity, Long> {
}
