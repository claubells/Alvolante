package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * ExtraEntity es una entidad que representa los datos de un extra.
 */
@Getter
@Setter
@Entity
@Table(name = "Extra")
public class ExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExtra;

    private int tipoExtra; // 1 portabicicletas, 2 sillabebe
    private int maxCantidadExtra;
    private int cantidadSeleccionada;
    private String nombreExtra;
    private String descripcionExtra;
    private String costoExtra;
    private boolean disponibilidadExtra;

    @Lob
    private byte[] fotoExtra;

    /**
     * Constructor con todos los atributos.
     *
     * @param tipoExtra El tipo de extra (1 portabicicletas, 2 sillabebe).
     * @param maxCantidadExtra La cantidad máxima del extra.
     * @param cantidadSeleccionada La cantidad seleccionada del extra.
     * @param nombreExtra El nombre del extra.
     * @param descripcionExtra La descripción del extra.
     * @param costoExtra El costo del extra.
     * @param disponibilidadExtra La disponibilidad del extra.
     * @param fotoExtra La foto del extra.
     */
    public ExtraEntity(int tipoExtra, int maxCantidadExtra, int cantidadSeleccionada, String nombreExtra, String descripcionExtra, String costoExtra, boolean disponibilidadExtra, byte[] fotoExtra) {
        this.tipoExtra = tipoExtra;
        this.maxCantidadExtra = maxCantidadExtra;
        this.cantidadSeleccionada = cantidadSeleccionada;
        this.nombreExtra = nombreExtra;
        this.descripcionExtra = descripcionExtra;
        this.costoExtra = costoExtra;
        this.disponibilidadExtra = disponibilidadExtra;
        this.fotoExtra = fotoExtra;
    }

    /**
     * Constructor vacío.
     */
    public ExtraEntity() {
    }
}
