package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.SucursalEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * SucursalService es una clase de servicio que maneja la lógica de negocio relacionada con las sucursales.
 */
@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    /**
     * Obtiene una lista de vehículos por el ID de la sucursal.
     *
     * @param id El ID de la sucursal.
     * @return La lista de vehículos correspondiente a la sucursal proporcionada.
     * @throws RuntimeException Si no se encuentra la sucursal.
     */
    public List<VehiculoEntity> getVehiculosBySucursal(long id) {
        SucursalEntity sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        return sucursal.getVehiculos();
    }

    /**
     * Obtiene una sucursal por su ID.
     *
     * @param id El ID de la sucursal.
     * @return La sucursal correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public SucursalEntity getSucursalById(long id) {
        Optional<SucursalEntity> sucursal = sucursalRepository.findById(id);
        return sucursal.orElse(null);
    }

    /**
     * Obtiene una lista de todas las sucursales.
     *
     * @return Una lista de todas las sucursales.
     */
    public List<SucursalEntity> getAllSucursales() {
        return sucursalRepository.findAll();
    }
}
