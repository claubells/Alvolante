package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * VehiculoService es una clase de servicio que maneja la lógica de negocio relacionada con los vehículos.
 */
@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    /**
     * Crea un nuevo vehículo.
     *
     * @param codigoACRISS El código ACRISS del vehículo.
     * @param estadoVehiculo El estado del vehículo.
     * @param marca La marca del vehículo.
     * @param modelo El modelo del vehículo.
     * @param patente La patente del vehículo.
     * @param numeroChasis El número de chasis del vehículo.
     * @param kilometraje El kilometraje del vehículo.
     * @param costo El costo del vehículo.
     * @param anio El año del vehículo.
     * @param tipo El tipo de vehículo.
     * @param color El color del vehículo.
     * @param capacidadPasajeros La capacidad de pasajeros del vehículo.
     * @param disponibilidad La disponibilidad del vehículo.
     * @param fechaUltimoMantenimiento La fecha del último mantenimiento del vehículo.
     * @param fotoVehiculo La foto del vehículo.
     * @param combustible El tipo de combustible del vehículo.
     * @return Un código indicando el resultado de la operación.
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRABAJADOR')")
    public int createVehiculo(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo, String combustible) {
        VehiculoEntity existentePatente = vehiculoRepository.findByPatente(patente);
        VehiculoEntity existenteChasis = vehiculoRepository.findByNumeroChasis(numeroChasis);
        if (existentePatente != null) { // si se encuentra una patente ya registrada
            System.out.println("Patente existente: " + existentePatente);
            return 4; // patente duplicada
        }
        if (existenteChasis != null) {
            System.out.println("Chasis existente: " + existenteChasis);
            return 2; // chasis duplicado
        }

        VehiculoEntity vehiculoNuevo = new VehiculoEntity(codigoACRISS, estadoVehiculo, marca, modelo, patente, numeroChasis, kilometraje, costo, anio, tipo, color, capacidadPasajeros, true, fechaUltimoMantenimiento, fotoVehiculo, combustible);
        vehiculoRepository.save(vehiculoNuevo);
        return 0; // éxito
    }

    /**
     * Obtiene una lista de todos los vehículos.
     *
     * @return Una lista de todos los vehículos.
     */
    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Obtiene un vehículo por su ID.
     *
     * @param id El ID del vehículo.
     * @return El vehículo correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public VehiculoEntity getVehiculoById(Long id) {
        Optional<VehiculoEntity> getVehiculo = vehiculoRepository.findById(id);
        return getVehiculo.orElse(null);
    }

    /**
     * Obtiene una lista de vehículos disponibles.
     *
     * @return Una lista de vehículos disponibles.
     */
    public List<VehiculoEntity> getVehiculosByDisponible() {
        return vehiculoRepository.findByDisponibilidadTrue();
    }

    /**
     * Obtiene una lista de vehículos únicos con menor kilometraje.
     *
     * @return Una lista de vehículos únicos disponibles ordenados por modelo y kilometraje.
     */
    public List<VehiculoEntity> getVehiculosUnicosConMenorKilometraje() {
        List<VehiculoEntity> allAvailable = vehiculoRepository.findVehiculosUnicosConMenorKilometraje();
        Map<String, VehiculoEntity> lowestMileageByModel = new HashMap<>();

        for (VehiculoEntity vehiculo : allAvailable) {
            String modelo = vehiculo.getModelo();
            if (!lowestMileageByModel.containsKey(modelo)) {
                lowestMileageByModel.put(modelo, vehiculo);
            }
        }

        return new ArrayList<>(lowestMileageByModel.values());
    }

    /**
     * Obtiene una lista de vehículos disponibles.
     *
     * @return Una lista de vehículos disponibles.
     */
    public List<VehiculoEntity> obtenerVehiculosDisponibles() {
        return vehiculoRepository.findByDisponibilidadTrue();
    }
}
