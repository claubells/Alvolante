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
    private boolean quiereExtras;
    private int estadoReserva; // 0 enEsperaDeExtras, 1 activo, 2 inactivo
    private int horaReserva; // 0 mañana, 1 tarde
    private float costoReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuarioReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntity vehiculoReserva;

    @ManyToMany
    @JoinTable(
            name = "extras_reserva",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_extra"))
    private List<ExtraEntity> extrasReserva;

    public ReservaEntity(Date fechaInicioReserva, Date fechaFinReserva, boolean quiereExtras, int estadoReserva, int horaReserva, float costoReserva, UsuarioEntity usuarioReserva, VehiculoEntity vehiculoReserva, List<ExtraEntity> extrasReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.quiereExtras = quiereExtras;
        this.estadoReserva = estadoReserva;
        this.horaReserva = horaReserva;
        this.costoReserva = costoReserva;
        this.usuarioReserva = usuarioReserva;
        this.vehiculoReserva = vehiculoReserva;
        this.extrasReserva = extrasReserva;
    }

    public ReservaEntity() {

    }
}


