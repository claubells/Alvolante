package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.BoletaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Alvolante.Backend.Service.BoletaService;

import java.util.List;

@RestController
@RequestMapping ("api/boleta")
@CrossOrigin
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @PreAuthorize("hasRole('TRABAJADOR') or hasRole('ADMIN')")
    @PostMapping("/generarBoleta")
    public ResponseEntity<?> generarBoleta(@RequestBody BoletaEntity nuevaBoleta) {

        try {
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

    @GetMapping("/obtenerBoletaPorId")
    public BoletaEntity getBoletaPorId(@PathVariable String NombreCliente) {
        return boletaService.getBoletaByNombreCliente(NombreCliente);
    }

    @GetMapping("/all")
    public List<BoletaEntity> getAllBoletas() {
        return boletaService.getAllBoletas();
    }


}