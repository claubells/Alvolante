package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public int createVehiculo(String codigoACRISS, String estadoVehiculo, String marca, String modelo, String patente, String numeroChasis, String kilometraje, float costo, int anio, String tipo, String color, int capacidadPasajeros, boolean disponibilidad, Date fechaUltimoMantenimiento, byte[] fotoVehiculo) {
        VehiculoEntity existentePatente = vehiculoRepository.findByPatente(patente);
        VehiculoEntity existenteChasis = vehiculoRepository.findByNumeroChasis(numeroChasis);
        if(existentePatente != null ) { // si se encuentra una patente ya registrada
            //throw new RuntimeException("La patente '"+ patente +"' ya está registrada. Usa una diferente.");  // es decir, existe retornamos null
            System.out.println("Patente existente: " + existentePatente);
            return 4; //patente duplicada
        }
        if (existenteChasis != null) {
            //throw new RuntimeException("El número de chasis '"+ numeroChasis +"' ya está registrado. Por favor ingrese uno distinto.");
            System.out.println("Chasis existente: " + existenteChasis);
            return 2; //chasis duplicado
        }

        /*// es obligatorio poner la patente, el modelo, numero de chasis, marca
        if (patente == null) {
            throw new RuntimeException("Debe de ingresar la patente del vehículo.");
        }
        if (modelo == null){
            throw new RuntimeException("Debe de ingresar el modelo del vehículo.");
        }
        if (numeroChasis == null){
            throw new RuntimeException("Debe de ingresar el Número de chasis del vehículo.");
         }
        if (marca == null){
            throw new RuntimeException("Debe de ingresar la marca del vehículo.");
        }*/
        VehiculoEntity vehiculoNuevo = new VehiculoEntity(codigoACRISS, estadoVehiculo, marca, modelo, patente, numeroChasis, kilometraje, costo, anio, tipo, color, capacidadPasajeros, true, fechaUltimoMantenimiento, fotoVehiculo);
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
        }else {
            return null;
        }
    }

    public List<VehiculoEntity> getVehiculosByDisponible(){
        return vehiculoRepository.findByDisponibilidadTrue();

    }

}