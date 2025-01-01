package com.Alvolante.Backend.Entity;


import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "boleta")
public class BoletaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBoleta;

    private String nombreEmisor;
    private String rutEmisor;
    private String direccionEmisor;

    private String nombreCliente; // Opcional
    private String rutCliente;    // Opcional

    private LocalDate fechaEmision;
    private LocalTime horaEmision;

    //private List<DetalleBoleta> detalles; // Detalles de productos o servicios

    private double subtotal;
    private double iva;
    private double total;

    private String formaPago;


    public BoletaEntity(long idBoleta, String nombreEmisor, String rutEmisor, String direccionEmisor, String nombreCliente, String rutCliente, LocalDate fechaEmision, LocalTime horaEmision, double subtotal, double iva, double total, String formaPago) {
        this.idBoleta = idBoleta;
        this.nombreEmisor = nombreEmisor;
        this.rutEmisor = rutEmisor;
        this.direccionEmisor = direccionEmisor;
        this.nombreCliente = nombreCliente;
        this.rutCliente = rutCliente;
        this.fechaEmision = fechaEmision;
        this.horaEmision = horaEmision;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.formaPago = formaPago;
    }

    public BoletaEntity() {}


    //---------------getter and setter-------------
    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public LocalTime getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(LocalTime horaEmision) {
        this.horaEmision = horaEmision;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionEmisor() {
        return direccionEmisor;
    }

    public void setDireccionEmisor(String direccionEmisor) {
        this.direccionEmisor = direccionEmisor;
    }

    public String getRutEmisor() {
        return rutEmisor;
    }

    public void setRutEmisor(String rutEmisor) {
        this.rutEmisor = rutEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public long getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(long idBoleta) {
        this.idBoleta = idBoleta;
    }
}