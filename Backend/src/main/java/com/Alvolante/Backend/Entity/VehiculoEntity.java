package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
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
    private Date fechaUltimoMantenimiento;
    @Lob
    private byte[] fotoVehiculo;

    public VehiculoEntity(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo) {
        this.idVehiculo = getIdVehiculo();
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
}


