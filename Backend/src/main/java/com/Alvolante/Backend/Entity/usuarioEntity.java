package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario")

public class usuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Atributos
    private long id;
    private String rut;
    private String name;
    private String email;
    private String phone;
    @JsonFormat
    private Date fechaDeNacimiento;
    private String password;
    private String rol;

    //Constructor de usuario

    public usuarioEntity(String rut, String name, String email, String phone, Date fechaDeNacimiento, String password, String rol) {
        this.id = id;
        this.rut = rut;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.password = password;
        this.rol = rol;
    }

    public usuarioEntity() {

    }

    //Construcor de usuario vacio

    //Getter y setter para el usuario

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public String getPhone() {
        return phone;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}