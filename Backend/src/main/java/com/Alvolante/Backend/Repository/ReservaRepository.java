package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    @Query( "SELECT r " +
            "FROM ReservaEntity r " +
            "WHERE r.usuarioReserva.idUsuario = :idUsuario AND r.estadoReserva = 1")
    List<ReservaEntity> findActiveReservationByUserId(@Param("idUsuario") Long idUsuario);


    @Query( "SELECT r " +
            "FROM ReservaEntity r " +
            "WHERE r.usuarioReserva.idUsuario = :idUsuario AND r.estadoReserva = 0")
    ReservaEntity findPendingReservationByUserId(@Param("idUsuario")Long idUsuario);

    @Query( "SELECT r.estadoReserva " +
            "FROM ReservaEntity r " +
            "WHERE r.idReserva = : idReserva")
    int StateReservationByIdReservation(@Param("idReserva")Long idReserva);

    Optional<ReservaEntity> findByUsuarioReserva_IdUsuario(Long idUsuario);

    @Query("SELECT r FROM ReservaEntity r WHERE r.usuarioReserva.idUsuario = :idUsuario AND r.estadoReserva = 1")
    Optional<ReservaEntity> findActiveReservationEntityByUserId(@Param("idUsuario") Long idUsuario);
}