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
            int resultado = vehiculoService.createVehiculo(
                    nuevoVehiculo.getCodigoACRISS(),
                    nuevoVehiculo.getEstadoVehiculo(),
                    nuevoVehiculo.getMarca(),
                    nuevoVehiculo.getModelo(),
                    nuevoVehiculo.getPatente(),
                    nuevoVehiculo.getNumeroChasis(),
                    nuevoVehiculo.getKilometraje(),
                    nuevoVehiculo.getCosto(),
                    nuevoVehiculo.getAnio(),
                    nuevoVehiculo.getTipo(),
                    nuevoVehiculo.getColor(),
                    nuevoVehiculo.getCapacidadPasajeros(),
                    nuevoVehiculo.getDisponibilidad(),
                    nuevoVehiculo.getFechaUltimoMantenimiento(),
                    nuevoVehiculo.getFotoVehiculo()
            );

            if (resultado == 2) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El número de chasis ya está registrado.");
            } else if (resultado == 3) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La patente ya está registrada.");
            } else if (resultado == 0) {
                return ResponseEntity.ok("Vehículo creado con éxito.");
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error desconocido.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }
    }

    @GetMapping("/all")
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }
}
