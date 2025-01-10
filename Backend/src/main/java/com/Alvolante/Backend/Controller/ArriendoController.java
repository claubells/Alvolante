package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Config.JwtUtil;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Repository.ArriendoRepository;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArriendoRepository arriendoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para crear un nuevo arriendo.
     *
     * @param nuevoArriendo Los detalles del nuevo arriendo.
     * @return El arriendo creado.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TRABAJADOR')")
    @PostMapping("/crear-arriendo")
    public ArriendoEntity createArriendo(@RequestBody ArriendoEntity nuevoArriendo,
                                         @RequestHeader("Authorization") String token) {
        try{
            System.out.println("\nCREANDO ARRIENDO\n");

            // Extraer el email del token
            String email = jwtUtil.getEmail(token.replace("Bearer ", ""));

            // Se busca el usuario por email
            UsuarioEntity usuario = usuarioRepository.findByEmail(email);
            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado con email: " + email);
            }

            System.out.println("Usuario autenticado: " + usuario.getIdUsuario());

            // Asignar el usuario autenticado al arriendo
            nuevoArriendo.setIdTrabajador(usuario.getIdUsuario());

            // Validar que el vehículo existe
            if (!vehiculoRepository.existsById(nuevoArriendo.getIdVehiculo())) {
                throw new RuntimeException("Vehículo no encontrado con ID: " + nuevoArriendo.getIdVehiculo());
            }

            // Crear el arriendo en el servicio
            ArriendoEntity arriendoCreado = arriendoService.createArriendo(nuevoArriendo);

            System.out.println("Reserva creada con éxito: " + arriendoCreado);

            System.out.println("\nId Arriendo: " + arriendoCreado.getIdArriendo());
            System.out.println("\nFecha Inicio: " + arriendoCreado.getFechaInicioArriendo());
            System.out.println("\nFecha Final: " + arriendoCreado.getFechaFinArriendo());
            System.out.println("\nEstado de pago Arriendo: " + arriendoCreado.isEstadoPago());
            System.out.println("\nSucursal de retiro id: "+arriendoCreado.getIdSucursalRetiro());
            System.out.println("\nSucursal de entrega id: "+arriendoCreado.getIdSucursalEntrega());
            System.out.println("\nCosto Arriendo: " + arriendoCreado.getCostoArriendo());
            System.out.println("\nUsuario-> idUsuario: " + arriendoCreado.getIdTrabajador());
            System.out.println("\nVehiculo-> idVehiculo: " + arriendoCreado.getIdVehiculo());

            return arriendoRepository.save(nuevoArriendo);
        }catch (Exception e) {
            System.err.println("Error al crear el arriendo: " + e.getMessage());
            throw new RuntimeException("Error al crear el arriendo: " + e.getMessage(), e);
        }

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
