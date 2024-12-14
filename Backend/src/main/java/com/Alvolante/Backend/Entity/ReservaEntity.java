package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Extras")
public class ExtraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExtra;
    private int tipoExtra; // 1 portabicicletas, 2 sillabebe
    private int cantidadExtra;
    private int maxCantidadExtra;
    private String nombreExtra;
    private String descripcionExtra;
    private String costoExtra;
    private boolean disponibilidadExtra;
    @Lob
    private byte[] fotoExtra;

    public ExtraEntity(int tipoExtra, int cantidadExtra, int maxCantidadExtra, String nombreExtra, String descripcionExtra, String costoExtra, boolean disponibilidadExtra, byte[] fotoExtra) {
        this.tipoExtra = tipoExtra;
        this.cantidadExtra = cantidadExtra;
        this.maxCantidadExtra = maxCantidadExtra;
        this.nombreExtra = nombreExtra;
        this.descripcionExtra = descripcionExtra;
        this.costoExtra = costoExtra;
        this.disponibilidadExtra = disponibilidadExtra;
        this.fotoExtra = fotoExtra;
    }

    public ExtraEntity() {

    }
}


