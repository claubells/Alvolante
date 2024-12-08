package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehiculo")

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
        this.idVehiculo = idVehiculo;
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

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public long getIdVehiculo() {
        return idVehiculo;
    }

    public String getCodigoACRISS() {
        return codigoACRISS;
    }

    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPatente() {
        return patente;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public float getCosto() {
        return costo;
    }

    public int getAnio() {
        return anio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public Date getFechaUltimoMantenimiento() {
        return fechaUltimoMantenimiento;
    }

    public byte[] getFotoVehiculo() {
        return fotoVehiculo;
    }
}


