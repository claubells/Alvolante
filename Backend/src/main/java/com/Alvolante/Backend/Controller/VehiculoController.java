package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/crear-vehiculo")
    public ResponseEntity<?> createVehiculo(@RequestBody VehiculoEntity nuevoVehiculo) {

        try {
            VehiculoEntity vehiculoGuardado = vehiculoService.createVehiculo(nuevoVehiculo);
            if (vehiculoGuardado.getFotoVehiculo()!= null ) {
                vehiculoGuardado.setFotoVehiculo(vehiculoGuardado.getFotoVehiculo());
            }
            return ResponseEntity.ok(vehiculoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
