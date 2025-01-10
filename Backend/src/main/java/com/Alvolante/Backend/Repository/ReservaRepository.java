package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * ReservaRepository es una interfaz que extiende JpaRepository para manejar operaciones CRUD en la entidad ReservaEntity.
 */
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    /**
     * Encuentra una lista de reservas por el ID del usuario y el estado de la reserva.
     *
     * @param idUsuario El ID del usuario.
     * @param estadoReserva El estado de la reserva.
     * @return Una lista de reservas con el ID del usuario y el estado de la reserva proporcionados.
     */
    @Query
    List<ReservaEntity> findByIdUsuarioAndEstadoReserva(Long idUsuario, int estadoReserva);

    /**
     * Encuentra una reserva pendiente por el ID del usuario.
     *
     * @param idUsuario El ID del usuario.
     * @return La reserva pendiente correspondiente al ID del usuario proporcionado.
     */
    @Query("SELECT r FROM ReservaEntity r WHERE r.idUsuario = :idUsuario AND r.estadoReserva = 0")
    ReservaEntity findPendingReservationByUserId(@Param("idUsuario") Long idUsuario);

    /**
     * Encuentra el estado de la reserva por el ID de la reserva.
     *
     * @param idReserva El ID de la reserva.
     * @return El estado de la reserva correspondiente al ID proporcionado.
     */
    @Query("SELECT r.estadoReserva FROM ReservaEntity r WHERE r.idReserva = :idReserva")
    int StateReservationByIdReservation(@Param("idReserva") Long idReserva);

    /**
     * Encuentra una reserva por el ID del usuario.
     *
     * @param idUsuario El ID del usuario.
     * @return Una Optional con la reserva correspondiente al ID del usuario proporcionado.
     */
    List<ReservaEntity> findByIdUsuario(Long idUsuario);

}
