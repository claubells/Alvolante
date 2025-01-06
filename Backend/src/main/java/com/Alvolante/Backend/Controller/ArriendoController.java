package com.Alvolante.Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Alvolante.Backend.Entity.ArriendoEntity;
import com.Alvolante.Backend.Service.ArriendoService;

@RestController
@RequestMapping("/api/arriendo")
public class ArriendoController {

    @Autowired
    private ArriendoService arriendoService;

    @PostMapping("/generarArriendo")
    public ArriendoEntity crearArriendo(@RequestBody ArriendoEntity nuevoArriendo) {
        return arriendoService.crearArriendo(nuevoArriendo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArriendoById(@PathVariable Long id) {
        try {
            ArriendoEntity arriendo = arriendoService.getArriendoById(id);
            return ResponseEntity.ok(arriendo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Nuevo endpoint para buscar por rutAsociado
    @GetMapping("/rut/{rutAsociado}")
    public ResponseEntity<?> getArriendosByRutAsociado(@PathVariable String rutAsociado) {
        return ResponseEntity.ok(arriendoService.getArriendosByRutAsociado(rutAsociado));
    }

    // Nuevo endpoint para buscar por estadoPago
    @GetMapping("/estadoPago/{estadoPago}")
    public ResponseEntity<?> getArriendosByEstadoPago(@PathVariable boolean estadoPago) {
        return ResponseEntity.ok(arriendoService.getArriendosByEstadoPago(estadoPago));
    }
}
