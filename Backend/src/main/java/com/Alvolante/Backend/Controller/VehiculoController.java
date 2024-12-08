package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/crear-vehiculo")
    public ResponseEntity<?> createVehiculo(@RequestBody VehiculoEntity nuevoVehiculo) {

        try {
            VehiculoEntity vehiculoGuardado = vehiculoService.createVehiculo(nuevoVehiculo.getCodigoACRISS(), nuevoVehiculo.getEstadoVehiculo(), nuevoVehiculo.getMarca(), nuevoVehiculo.getModelo(), nuevoVehiculo.getPatente(), nuevoVehiculo.getNumeroChasis(), nuevoVehiculo.getKilometraje(), nuevoVehiculo.getCosto(), nuevoVehiculo.getAnio(), nuevoVehiculo.getTipo(), nuevoVehiculo.getColor(), nuevoVehiculo.getCapacidadPasajeros(), nuevoVehiculo.getDisponibilidad(), nuevoVehiculo.getFechaUltimoMantenimiento(), nuevoVehiculo.getFotoVehiculo());
            return ResponseEntity.ok(vehiculoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }
}
