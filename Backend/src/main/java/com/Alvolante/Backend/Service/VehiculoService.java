package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public VehiculoEntity createVehiculo(VehiculoEntity nuevoVehiculo) {
        VehiculoEntity existentePatente = vehiculoRepository.findByPatente(nuevoVehiculo.getPatente());
        VehiculoEntity existenteChasis = vehiculoRepository.findByNumeroChasis(nuevoVehiculo.getNumeroChasis());
        if(existentePatente != null ) { // si se encuentra una patente ya registrada
            throw new RuntimeException("La patente "+ nuevoVehiculo.getPatente() +" ya está registrada. Usa una diferente.");  // es decir, existe retornamos null
        }
        if (existenteChasis != null) {
            throw new RuntimeException("El número de chasis "+ nuevoVehiculo.getNumeroChasis() +" ya está registrado. Por favor ingrese uno distinto.");
        }

        // es obligatorio poner la patente y el modelo
        if (nuevoVehiculo.getPatente() == null) {
            throw new RuntimeException("Debe de ingresar la patente del vehículo.");
        }
        if (nuevoVehiculo.getModelo() == null){
            throw new RuntimeException("Debe de ingresar el modelo del vehículo.");
        }
        return vehiculoRepository.save(nuevoVehiculo);
    }
}
