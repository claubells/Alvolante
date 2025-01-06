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

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('TRABAJADOR')")
    public int createVehiculo(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo, String combustible) {
        VehiculoEntity existentePatente = vehiculoRepository.findByPatente(patente);
        VehiculoEntity existenteChasis = vehiculoRepository.findByNumeroChasis(numeroChasis);
        if(existentePatente != null ) { // si se encuentra una patente ya registrada
            System.out.println("Patente existente: " + existentePatente);
            return 4; //patente duplicada
        }
        if (existenteChasis != null) {
            System.out.println("Chasis existente: " + existenteChasis);
            return 2; //chasis duplicado
        }

        VehiculoEntity vehiculoNuevo = new VehiculoEntity(codigoACRISS, estadoVehiculo, marca, modelo, patente, numeroChasis, kilometraje, costo, anio, tipo, color, capacidadPasajeros, true, fechaUltimoMantenimiento, fotoVehiculo, combustible);
        vehiculoRepository.save(vehiculoNuevo);
        return 0; //exito
    }

    public List<VehiculoEntity> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public VehiculoEntity getVehiculoById(Long id) {
        Optional<VehiculoEntity> getVehiculo = vehiculoRepository.findById(id);
        if (getVehiculo.isPresent()) {
            return getVehiculo.get();
        } else {
            return null;
        }
    }

    public List<VehiculoEntity> getVehiculosByDisponible() {
        return vehiculoRepository.findByDisponibilidadTrue();
    }

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

    public List<VehiculoEntity> obtenerVehiculosDisponibles() {
        return vehiculoRepository.findByDisponibilidadTrue();
    }


}