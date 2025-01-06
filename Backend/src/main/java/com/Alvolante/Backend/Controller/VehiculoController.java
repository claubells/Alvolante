package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;


    @PostMapping("/crear-vehiculo")
    public int createVehiculo(@RequestBody VehiculoEntity nuevoVehiculo) {

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
                    nuevoVehiculo.isDisponibilidad(),
                    nuevoVehiculo.getFechaUltimoMantenimiento(),
                    nuevoVehiculo.getFotoVehiculo(),
                    nuevoVehiculo.getCombustible()
            );

            if (resultado == 2) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 2; //chasis duplicado
            }
            if (resultado == 4) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 4; //patente duplicada
            }
            if (resultado == 0) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 0;
            } else {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return -1;
            }

        } catch (Exception e) {
            return -2;
        }
    }

    @GetMapping("/all")
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }

    @GetMapping("/obtenerVehiculoPorId/{id}")
    public VehiculoEntity obtenerVehiculoPorId(@PathVariable Long id) {
        return vehiculoService.getVehiculoById(id);
    }

    @GetMapping("/vehiculosDisponible")
    public List<VehiculoEntity> getVehiculosDisponible() {
        return vehiculoService.getVehiculosByDisponible();
    }
    //Devuelve lista de vehiculos no repetidos por modelo, devuelve los de menor kilometraje y que esten disponibles
    @GetMapping("/disponibles-menor-kilometraje")
    public List<VehiculoEntity> getVehiculosUnicosConMenorKilometraje() {
        return vehiculoService.getVehiculosUnicosConMenorKilometraje();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoEntity>> obtenerVehiculosDisponibles() {
        List<VehiculoEntity> disponibles = vehiculoService.obtenerVehiculosDisponibles();
        return ResponseEntity.ok(disponibles);
    }
}