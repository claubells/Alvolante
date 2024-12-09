package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.SucursalEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<VehiculoEntity> getVehiculosBySucursal(long id) {
        SucursalEntity sucursal = sucursalRepository.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        return sucursal.getVehiculos();
    }

    public SucursalEntity getSucursalById(long id) {
        Optional<SucursalEntity> sucursal = sucursalRepository.findById(id);
        return sucursal.orElse(null);
    }
    public List<SucursalEntity> getAllSucursales(){
        return (List<SucursalEntity>)sucursalRepository.findAll();
    }

}