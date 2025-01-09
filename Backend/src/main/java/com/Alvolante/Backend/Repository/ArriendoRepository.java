package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.ArriendoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * ArriendoRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad ArriendoEntity.
 */
public interface ArriendoRepository extends JpaRepository<ArriendoEntity, Long> {

    /**
     * Encuentra una lista de arriendos por el RUT asociado.
     *
     * @param rutAsociado El RUT asociado al arriendo.
     * @return Una lista de arriendos con el RUT asociado proporcionado.
     */
    List<ArriendoEntity> findByRutAsociado(String rutAsociado);

    /**
     * Encuentra una lista de arriendos por el estado de pago.
     *
     * @param estadoPago El estado de pago del arriendo (true = pagado, false = pendiente).
     * @return Una lista de arriendos con el estado de pago proporcionado.
     */
    List<ArriendoEntity> findByEstadoPago(boolean estadoPago);
}
