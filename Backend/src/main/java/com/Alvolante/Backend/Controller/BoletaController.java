package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.BoletaEntity;
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
                    nuevaBoleta.getFormaPago()
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
    @GetMapping("/obtenerBoletaPorId")
    public BoletaEntity getBoletaPorId(@PathVariable String NombreCliente) {
        return boletaService.getBoletaByNombreCliente(NombreCliente);
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
