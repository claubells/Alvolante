package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/extras")
public class ExtraController {
    @Autowired
    private ExtraService extraService;

    @PostMapping("/agrgar-extras")
    public int agregarExtra(@RequestBody ExtraEntity extras) {

        // aqui hay que preguntar si el arriendo tiene un vehiculo asignado, si no tiene debe decirle
        // que necesita reservar un vehiculo antes
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
                    nuevoVehiculo.getFotoVehiculo()
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

    @GetMapping("/todos-los-vehiculos")
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }
    }