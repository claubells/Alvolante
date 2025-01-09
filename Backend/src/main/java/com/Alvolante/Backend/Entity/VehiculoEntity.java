package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Date;

/**
 * VehiculoEntity es una entidad que representa los datos de un vehículo.
 */
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

    private String combustible;

    /**
     * Constructor con todos los atributos.
     *
     * @param codigoACRISS El código ACRISS del vehículo.
     * @param estadoVehiculo El estado del vehículo.
     * @param marca La marca del vehículo.
     * @param modelo El modelo del vehículo.
     * @param patente La patente del vehículo.
     * @param numeroChasis El número de chasis del vehículo.
     * @param kilometraje El kilometraje del vehículo.
     * @param costo El costo del vehículo.
     * @param anio El año del vehículo.
     * @param tipo El tipo de vehículo.
     * @param color El color del vehículo.
     * @param capacidadPasajeros La capacidad de pasajeros del vehículo.
     * @param disponibilidad La disponibilidad del vehículo.
     * @param fechaUltimoMantenimiento La fecha del último mantenimiento del vehículo.
     * @param fotoVehiculo La foto del vehículo.
     * @param combustible El tipo de combustible del vehículo.
     */
    public VehiculoEntity(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo, String combustible) {
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
        this.combustible = combustible;
    }

    /**
     * Constructor vacío.
     */
    public VehiculoEntity() {
    }
}
