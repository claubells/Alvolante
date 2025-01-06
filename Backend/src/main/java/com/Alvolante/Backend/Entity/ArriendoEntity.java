package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Setter
@Getter

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Arriendo")
public class ArriendoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArriendo;
    //Atributos asociados al cliente
    private String rutAsociado;
    private String nameAsociado;
    private String emailAsociado;
    private String phoneAsociado;
    @JsonFormat
    private Date birthdateAsociado;
    //Atributos asociados a la reserva
    private Date fechaInicioArriendo;
    private Date fechaFinArriendo;
    private int horaArriendo;
    private float costoArriendo;
    private boolean estadoPago; // true = pagado, false = pendiente

    //Constructor con todos los atributos
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

    //Constructor vacio
    public ArriendoEntity() {
    }
}