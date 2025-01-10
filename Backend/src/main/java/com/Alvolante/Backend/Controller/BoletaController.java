package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Config.JwtUtil;
import com.Alvolante.Backend.Entity.BoletaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Alvolante.Backend.Service.BoletaService;

import java.util.List;

/**
 * BoletaController es un controlador que maneja las solicitudes HTTP relacionadas con las boletas.
 */
@RestController
@RequestMapping("api/boleta")
@CrossOrigin
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint para generar una nueva boleta.
     *
     * @param nuevaBoleta Los detalles de la nueva boleta.
     * @return La respuesta HTTP con la boleta generada o un mensaje de error.
     */
    @PreAuthorize("hasRole('TRABAJADOR') or hasRole('ADMIN') or hasRole('CLIENTE')")
    @PostMapping("/generarBoleta")
    public ResponseEntity<?> generarBoleta(@RequestBody BoletaEntity nuevaBoleta) {
        try {
            // Log para depuración
            System.out.println("Datos recibidos: " + nuevaBoleta);

            BoletaEntity boleta = boletaService.generarBoleta(
                    nuevaBoleta.getIdBoleta(),
                    nuevaBoleta.getNombreEmisor(),
                    nuevaBoleta.getRutEmisor(),
                    nuevaBoleta.getDireccionEmisor(),
                    nuevaBoleta.getNombreCliente(),
                    nuevaBoleta.getRutCliente(),
                    nuevaBoleta.getFechaEmision(),
                    nuevaBoleta.getHoraEmision(),
                    nuevaBoleta.getSubtotal(),
                    nuevaBoleta.getIva(),
                    nuevaBoleta.getTotal(),
                    nuevaBoleta.getFormaPago(),
                    nuevaBoleta.getIdUsuario()
            );

            return ResponseEntity.ok(boleta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener una boleta por el nombre del cliente.
     *
     * @param NombreCliente El nombre del cliente.
     * @return La boleta correspondiente al nombre del cliente.
     */
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ADMIN') or hasRole('TRABAJADOR')")
    @GetMapping("/usuario")
    public List<BoletaEntity> getBoletasByUsuario(@RequestHeader("Authorization") String token) {
        // Extraer el email del token
        String email = jwtUtil.getEmail(token.replace("Bearer ", ""));

        // Buscar el usuario por email
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con email: " + email);
        }

        // Obtener la lista de boletas del usuario
        return boletaService.findBoletasByUsuarioId(usuario.getIdUsuario());
    }

    /**
     * Endpoint para obtener todas las boletas.
     *
     * @return Una lista de todas las boletas.
     */
    @GetMapping("/all")
    public List<BoletaEntity> getAllBoletas() {
        return boletaService.getAllBoletas();
    }
}
