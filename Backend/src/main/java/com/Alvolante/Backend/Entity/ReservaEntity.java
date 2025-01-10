package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

/**
 * ReservaEntity es una entidad que representa los datos de una reserva.
 */
@Setter
@Getter
@Entity
@Table(name = "Reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReserva;

    private Date fechaInicioReserva;
    private Date fechaFinReserva;
    private int estadoReserva; // 0 enEsperaDeExtras, 1 activo, 2 inactivo
    private float costoReserva;

    @Column(name = "id_sucursal_retiro", nullable = false)
    private long idSucursalRetiro;

    @Column(name = "id_sucursal_entrega", nullable = false)
    private long idSucursalEntrega;

    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;

    @Column(name = "id_vehiculo", nullable = false)
    private long idVehiculo;

    private List<ExtraEntity> extrasReserva; // Extras opcionales asociados a la reserva

    /**
     * Constructor con todos los atributos.
     *
     * @param fechaInicioReserva La fecha de inicio de la reserva.
     * @param fechaFinReserva La fecha de fin de la reserva.
     * @param estadoReserva El estado de la reserva (0 enEsperaDeExtras, 1 activo, 2 inactivo).
     * @param costoReserva El costo de la reserva.
     * @param idUsuario El ID del usuario.
     * @param idVehiculo El ID del vehículo.
     * @param extrasReserva La lista de extras opcionales asociados a la reserva.
     */
    public ReservaEntity(Date fechaInicioReserva, Date fechaFinReserva, int estadoReserva, float costoReserva, long idUsuario, long idVehiculo, List<ExtraEntity> extrasReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.estadoReserva = estadoReserva;
        this.costoReserva = costoReserva;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.extrasReserva = extrasReserva;
    }

    /**
     * Constructor vacío.
     */
    public ReservaEntity() {
    }
}
