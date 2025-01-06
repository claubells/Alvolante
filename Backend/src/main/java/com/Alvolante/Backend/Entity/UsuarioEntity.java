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
    private String nameParam;
    private String email;
    private String phone;
    @JsonFormat
    private Date birthdate;
    private String password;
    private String role;

    //Constructor de usuario

    public UsuarioEntity(String rut, String nameParam, String email, String phone, Date birthdate, String password, String role) {
        this.rut = rut;
        this.nameParam = nameParam;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.password = password;
        this.role = role;
    }

    public UsuarioEntity() {

    }


}