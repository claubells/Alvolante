package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.ArriendoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArriendoRepository extends JpaRepository<ArriendoEntity, Long> {
    List<ArriendoEntity> findByRutAsociado(String rutAsociado);

    List<ArriendoEntity> findByEstadoPago(boolean estadoPago);
}