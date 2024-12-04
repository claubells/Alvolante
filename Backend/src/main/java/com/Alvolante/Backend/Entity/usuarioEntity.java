package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")

public class usuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Atributos
    private long id;
    private String name;
    private String email;
    private String password;
    private String rol;

    //Constructor de usuario

    public usuarioEntity(String name, String email, String password, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
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