package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Date;

@Getter
@Entity
@Table(name = "Vehiculo")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVehiculo;
    private String codigoACRISS;
    private String estadoVehiculo;
    private String marca;
    private String modelo;
    private String patente;
    private String numeroChasis;
    private String kilometraje;
    private float costo;
    private int anio;
    private String tipo;
    private String color;
    private int capacidadPasajeros;
    private boolean disponibilidad;
    @JsonFormat
    private Date fechaUltimoMantenimiento;
    @Lob
    private byte[] fotoVehiculo;


    public VehiculoEntity(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo) {
        this.codigoACRISS = codigoACRISS;
        this.estadoVehiculo = estadoVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.numeroChasis = numeroChasis;
        this.kilometraje = kilometraje;
        this.costo = costo;
        this.anio = anio;
        this.tipo = tipo;
        this.color = color;
        this.capacidadPasajeros = capacidadPasajeros;
        this.disponibilidad = disponibilidad;
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
        this.fotoVehiculo = fotoVehiculo;
    }

    public VehiculoEntity() {

    }



}


