package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

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

    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;

    @Column(name = "id_vehiculo", nullable = false)
    private long idVehiculo;

    @ManyToMany
    @JoinTable(
            name = "extras_reserva",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_extra"))
    private List<ExtraEntity> extrasReserva; // Extras opcionales asociados a la reserva

    public ReservaEntity(Date fechaInicioReserva, Date fechaFinReserva, int estadoReserva, float costoReserva, long idUsuario, long idVehiculo, List<ExtraEntity> extrasReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.estadoReserva = estadoReserva;
        this.costoReserva = costoReserva;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.extrasReserva = extrasReserva;
    }

    public ReservaEntity() {

    }
}

