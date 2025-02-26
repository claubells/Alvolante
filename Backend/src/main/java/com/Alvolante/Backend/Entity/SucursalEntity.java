package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * SucursalEntity es una entidad que representa los datos de una sucursal.
 */
@Getter
@Setter
@Entity
@Table(name = "sucursal")
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSucursal;

    private String region;
    private String telefono;
    private String direccion;
    private String gerenteResponsable;
    private String estado;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sucursal_id")
    private List<UsuarioEntity> empleados;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sucursal_id")
    private List<VehiculoEntity> vehiculos;

    /**
     * Constructor vacío.
     */
    public SucursalEntity() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param region La región de la sucursal.
     * @param telefono El teléfono de la sucursal.
     * @param direccion La dirección de la sucursal.
     * @param gerenteResponsable El gerente responsable de la sucursal.
     * @param estado El estado de la sucursal.
     * @param empleados La lista de empleados de la sucursal.
     * @param vehiculos La lista de vehículos de la sucursal.
     */
    public SucursalEntity(String region, String telefono, String direccion, String gerenteResponsable, String estado, List<UsuarioEntity> empleados, List<VehiculoEntity> vehiculos) {
        this.region = region;
        this.telefono = telefono;
        this.direccion = direccion;
        this.gerenteResponsable = gerenteResponsable;
        this.estado = estado;
        this.empleados = empleados;
        this.vehiculos = vehiculos;
    }

    // Getters y Setters
    public List<VehiculoEntity> getVehiculos() {
        return vehiculos;
    }
}
