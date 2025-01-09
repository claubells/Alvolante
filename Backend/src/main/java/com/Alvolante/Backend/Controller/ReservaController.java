package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Config.JwtUtil;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.ReservaRepository;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import com.Alvolante.Backend.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @PostMapping("/crear-reserva")
    public ReservaEntity createReserva(@RequestBody ReservaEntity nuevaReserva,
                                       @RequestHeader("Authorization") String token) {
        try {
            System.out.println("\nCREANDO RESERVA\n");

            // Extraer el email del token
            String email = jwtUtil.getEmail(token.replace("Bearer ", ""));

            // se busca el usuario por email
            UsuarioEntity usuario = usuarioRepository.findByEmail(email);
            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado con email: " + email);
            }

            System.out.println("Usuario autenticado: " + usuario.getIdUsuario());

            // Asignar el usuario autenticado a la reserva
            nuevaReserva.setIdUsuario(usuario.getIdUsuario());

            // Validar que el vehículo existe
            if (!vehiculoRepository.existsById(nuevaReserva.getIdVehiculo())) {
                throw new RuntimeException("Vehículo no encontrado con ID: " + nuevaReserva.getIdVehiculo());
            }

            // Crear la reserva en el servicio
            ReservaEntity reservaCreada = reservaService.createReserva(nuevaReserva);

            System.out.println("Reserva creada con éxito: " + reservaCreada);

            System.out.println("\nId Reserva: "+ nuevaReserva.getIdReserva());
            System.out.println("\nFecha Inicio: "+ nuevaReserva.getFechaInicioReserva());
            System.out.println("\nFecha Final: "+ nuevaReserva.getFechaFinReserva());
            System.out.println("\nEstado de Reserva: "+ nuevaReserva.getEstadoReserva());
            System.out.println("\nCosto reserva: "+nuevaReserva.getCostoReserva());
            System.out.println("\nUsuario-> idUsuario:"+nuevaReserva.getIdUsuario());
            System.out.println("\nVehiculo-> idVehiculo: "+nuevaReserva.getIdVehiculo());

            // Guardar la reserva
            return reservaRepository.save(nuevaReserva);
        } catch (Exception e) {
            System.err.println("Error al crear la reserva: " + e.getMessage());
            throw new RuntimeException("Error al crear la reserva: " + e.getMessage(), e);
        }
    }

    @GetMapping("/reservaPorId/{id}")
    public ReservaEntity findReservaById(@PathVariable Long id){return reservaService.getReservaById(id);}


}