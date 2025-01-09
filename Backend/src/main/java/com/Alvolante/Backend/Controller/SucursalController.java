package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.SucursalEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;


    @GetMapping("/all")
    public List<SucursalEntity> getAllSucursales(){
        return sucursalService.getAllSucursales();
    }

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<List<VehiculoEntity>> getVehiculosBySucursal(@PathVariable long id) {
        List<VehiculoEntity> vehiculos = sucursalService.getVehiculosBySucursal(id);
        return ResponseEntity.ok(vehiculos);
    }

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