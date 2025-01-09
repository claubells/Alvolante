package com.Alvolante.Backend.dto;

import lombok.Data;

/**
 * LoginDto es una clase de transferencia de datos que representa los datos de inicio de sesión de un usuario.
 */
@Data
public class LoginDto {
    private String email;
    private String password;
}
