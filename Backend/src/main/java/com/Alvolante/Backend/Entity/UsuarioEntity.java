package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Date;

@Getter
@Entity
@Table(name = "usuario")

public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Atributos
    private long idUsuario;
    private String rut;
    private String name;
    private String email;
    private String phone;
    @JsonFormat
    private Date fechaDeNacimiento;
    private String password;
    private String rol;

    //Constructor de usuario

    public UsuarioEntity(String rut, String name, String email, String phone, Date fechaDeNacimiento, String password, String rol) {
        this.idUsuario = idUsuario;
        this.rut = rut;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.password = password;
        this.rol = rol;
    }

    public UsuarioEntity() {

    }


}