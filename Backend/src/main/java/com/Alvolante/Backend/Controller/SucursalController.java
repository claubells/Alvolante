package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.SucursalEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SucursalController es un controlador que maneja las solicitudes HTTP relacionadas con las sucursales.
 */
@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    /**
     * Endpoint para obtener todas las sucursales.
     *
     * @return Una lista de todas las sucursales.
     */
    @GetMapping("/all")
    public List<SucursalEntity> getAllSucursales() {
        return sucursalService.getAllSucursales();
    }

    /**
     * Endpoint para obtener los vehículos de una sucursal por su ID.
     *
     * @param id El ID de la sucursal.
     * @return La respuesta HTTP con la lista de vehículos de la sucursal.
     */
    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<List<VehiculoEntity>> getVehiculosBySucursal(@PathVariable long id) {
        List<VehiculoEntity> vehiculos = sucursalService.getVehiculosBySucursal(id);
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Endpoint para obtener una sucursal por su ID.
     *
     * @param id El ID de la sucursal.
     * @return La respuesta HTTP con la sucursal encontrada o un mensaje de error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SucursalEntity> getSucursalById(@PathVariable long id) {
        SucursalEntity sucursal = sucursalService.getSucursalById(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
