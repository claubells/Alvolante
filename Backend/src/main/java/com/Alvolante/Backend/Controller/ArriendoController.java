package com.Alvolante.Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Alvolante.Backend.Entity.ArriendoEntity;
import com.Alvolante.Backend.Service.ArriendoService;

/**
 * ArriendoController es un controlador que maneja las solicitudes HTTP relacionadas con los arriendos.
 */
@RestController
@RequestMapping("/api/arriendo")
public class ArriendoController {

    @Autowired
    private ArriendoService arriendoService;

    /**
     * Endpoint para crear un nuevo arriendo.
     *
     * @param nuevoArriendo Los detalles del nuevo arriendo.
     * @return El arriendo creado.
     */
    @PostMapping("/generarArriendo")
    public ArriendoEntity crearArriendo(@RequestBody ArriendoEntity nuevoArriendo) {
        return arriendoService.crearArriendo(nuevoArriendo);
    }

    /**
     * Endpoint para obtener un arriendo por su ID.
     *
     * @param id El ID del arriendo.
     * @return La respuesta HTTP con el arriendo encontrado o un mensaje de error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getArriendoById(@PathVariable Long id) {
        try {
            ArriendoEntity arriendo = arriendoService.getArriendoById(id);
            return ResponseEntity.ok(arriendo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Nuevo endpoint para buscar arriendos por RUT asociado.
     *
     * @param rutAsociado El RUT asociado al arriendo.
     * @return La respuesta HTTP con los arriendos encontrados.
     */
    @GetMapping("/rut/{rutAsociado}")
    public ResponseEntity<?> getArriendosByRutAsociado(@PathVariable String rutAsociado) {
        return ResponseEntity.ok(arriendoService.getArriendosByRutAsociado(rutAsociado));
    }

    /**
     * Nuevo endpoint para buscar arriendos por estado de pago.
     *
     * @param estadoPago El estado de pago del arriendo.
     * @return La respuesta HTTP con los arriendos encontrados.
     */
    @GetMapping("/estadoPago/{estadoPago}")
    public ResponseEntity<?> getArriendosByEstadoPago(@PathVariable boolean estadoPago) {
        return ResponseEntity.ok(arriendoService.getArriendosByEstadoPago(estadoPago));
    }
}
