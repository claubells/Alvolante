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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Arriendo")
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
    private int horaArriendo;
    private float costoArriendo;
    private boolean estadoPago; // true = pagado, false = pendiente

    /**
     * Constructor con todos los atributos.
     *
     * @param rutAsociado El RUT asociado al arriendo.
     * @param nameAsociado El nombre asociado al arriendo.
     * @param emailAsociado El email asociado al arriendo.
     * @param phoneAsociado El teléfono asociado al arriendo.
     * @param birthdateAsociado La fecha de nacimiento asociada al arriendo.
     * @param fechaInicioArriendo La fecha de inicio del arriendo.
     * @param fechaFinArriendo La fecha de fin del arriendo.
     * @param horaArriendo La hora del arriendo.
     * @param costoArriendo El costo del arriendo.
     * @param estadoPago El estado de pago del arriendo (true = pagado, false = pendiente).
     */
    public ArriendoEntity(String rutAsociado, String nameAsociado, String emailAsociado, String phoneAsociado, Date birthdateAsociado, Date fechaInicioArriendo, Date fechaFinArriendo, int horaArriendo, float costoArriendo, boolean estadoPago) {
        this.rutAsociado = rutAsociado;
        this.nameAsociado = nameAsociado;
        this.emailAsociado = emailAsociado;
        this.phoneAsociado = phoneAsociado;
        this.birthdateAsociado = birthdateAsociado;
        this.fechaInicioArriendo = fechaInicioArriendo;
        this.fechaFinArriendo = fechaFinArriendo;
        this.horaArriendo = horaArriendo;
        this.costoArriendo = costoArriendo;
        this.estadoPago = estadoPago;
    }

    /**
     * Constructor vacío.
     */
    public ArriendoEntity() {
    }
}
