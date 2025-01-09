package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * VehiculoController es un controlador que maneja las solicitudes HTTP relacionadas con los vehículos.
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    /**
     * Endpoint para crear un nuevo vehículo.
     *
     * @param nuevoVehiculo Los detalles del nuevo vehículo.
     * @return Un código indicando el resultado de la operación.
     */
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
                System.out.println("Chasis duplicado");
                return 2; // Chasis duplicado
            }
            if (resultado == 4) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                System.out.println("Patente duplicada");
                return 4; // Patente duplicada
            }
            if (resultado == 0) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 0; // Éxito
            } else {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return -1; // Error general
            }
        } catch (Exception e) {
            return -2; // Error de excepción
        }
    }

    /**
     * Endpoint para obtener todos los vehículos.
     *
     * @return Una lista de todos los vehículos.
     */
    @GetMapping("/all")
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }

    /**
     * Endpoint para obtener un vehículo por su ID.
     *
     * @param id El ID del vehículo.
     * @return El vehículo correspondiente al ID proporcionado.
     */
    @GetMapping("/obtenerVehiculoPorId/{id}")
    public VehiculoEntity obtenerVehiculoPorId(@PathVariable Long id) {
        return vehiculoService.getVehiculoById(id);
    }

    /**
     * Endpoint para obtener todos los vehículos disponibles.
     *
     * @return Una lista de todos los vehículos disponibles.
     */
    @GetMapping("/vehiculosDisponible")
    public List<VehiculoEntity> getVehiculosDisponible() {
        return vehiculoService.getVehiculosByDisponible();
    }

    /**
     * Endpoint para obtener una lista de vehículos no repetidos por modelo, devolviendo los de menor kilometraje y que estén disponibles.
     *
     * @return Una lista de vehículos únicos con menor kilometraje.
     */
    @GetMapping("/disponibles-menor-kilometraje")
    public List<VehiculoEntity> getVehiculosUnicosConMenorKilometraje() {
        return vehiculoService.getVehiculosUnicosConMenorKilometraje();
    }
}
