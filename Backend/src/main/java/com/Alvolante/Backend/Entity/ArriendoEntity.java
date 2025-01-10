package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ArriendoEntity es una entidad que representa los datos de un arriendo.
 */
@Setter
@Getter
@Entity
@Table(name = "arriendo")
public class ArriendoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArriendo;

    // Atributos asociados al cliente
    private String rutAsociado;
    private String nameAsociado;
    private String emailAsociado;
    private String phoneAsociado;
    @JsonFormat
    private Date birthdateAsociado;

    // Atributos asociados a la reserva
    private Date fechaInicioArriendo;
    private Date fechaFinArriendo;
    private float costoArriendo;
    private boolean estadoPago; // true = pagado, false = pendiente

    @Column(name = "id_sucursal_retiro", nullable = false)
    private long idSucursalRetiro;

    @Column(name = "id_sucursal_entrega", nullable = false)
    private long idSucursalEntrega;

    @Column(name = "id_trabajador", nullable = false)
    private long idTrabajador;

    @Column(name = "id_vehiculo", nullable = false)
    private long idVehiculo;

    public ArriendoEntity(long idArriendo, String rutAsociado, String nameAsociado, String emailAsociado, String phoneAsociado, Date birthdateAsociado, Date fechaInicioArriendo, Date fechaFinArriendo, float costoArriendo, boolean estadoPago, long idSucursalRetiro, long idSucursalEntrega, long idTrabajador, long idVehiculo) {
        this.idArriendo = idArriendo;
        this.rutAsociado = rutAsociado;
        this.nameAsociado = nameAsociado;
        this.emailAsociado = emailAsociado;
        this.phoneAsociado = phoneAsociado;
        this.birthdateAsociado = birthdateAsociado;
        this.fechaInicioArriendo = fechaInicioArriendo;
        this.fechaFinArriendo = fechaFinArriendo;
        this.costoArriendo = costoArriendo;
        this.estadoPago = estadoPago;
        this.idSucursalRetiro = idSucursalRetiro;
        this.idSucursalEntrega = idSucursalEntrega;
        this.idTrabajador = idTrabajador;
        this.idVehiculo = idVehiculo;
    }

    /**
     * Constructor vacío.
     */
    public ArriendoEntity() {
    }
}
