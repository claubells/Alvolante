package com.Alvolante.Backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {
    private String rut;
    private String name;
    private String email;
    private String phone;
    private Date fechaDeNacimiento;
    private String password;
    private String rol;
}
