package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sucursal")
public class SucursalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSucursal;

    private String nombre;
    private String telefono;
    private String direccion;
    private String horarioAtencion;
    private String gerenteResponsable;
    private String estado;
    private int capacidadMaxima;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sucursal_id")
    private List<UsuarioEntity> empleados;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sucursal_id")
    private List<VehiculoEntity> vehiculos;

    // Constructor vacío
    public SucursalEntity() {}

    // Constructor con parámetros
    public SucursalEntity(String nombre, String telefono, String direccion, String horarioAtencion, String gerenteResponsable, String estado, int capacidadMaxima, List<UsuarioEntity> empleados, List<VehiculoEntity> vehiculos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
        this.gerenteResponsable = gerenteResponsable;
        this.estado = estado;
        this.capacidadMaxima = capacidadMaxima;
        this.empleados = empleados;
        this.vehiculos = vehiculos;
    }

    // Getters y Setters
    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public String getGerenteResponsable() {
        return gerenteResponsable;
    }

    public void setGerenteResponsable(String gerenteResponsable) {
        this.gerenteResponsable = gerenteResponsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public List<UsuarioEntity> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<UsuarioEntity> empleados) {
        this.empleados = empleados;
    }

    public List<VehiculoEntity> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoEntity> vehiculos) {
        this.vehiculos = vehiculos;
    }
}