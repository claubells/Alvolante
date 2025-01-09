package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.ReservaRepository;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * ReservaService es una clase de servicio que maneja la lógica de negocio relacionada con las reservas.
 */
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Crea una nueva reserva.
     *
     * @param nuevaReserva Los detalles de la nueva reserva.
     * @return La reserva creada.
     * @throws RuntimeException Si ocurre un error durante la validación o el guardado de la reserva.
     */
    public ReservaEntity createReserva(ReservaEntity nuevaReserva) {
        System.out.println("Ejecutando lógica de negocio en ReservaService");

        // Validar que el usuario existe
        if (!usuarioRepository.existsById(nuevaReserva.getIdUsuario())) {
            throw new RuntimeException("Usuario no encontrado con ID: " + nuevaReserva.getIdUsuario());
        }

        // Validar que el vehículo existe
        if (!vehiculoRepository.existsById(nuevaReserva.getIdVehiculo())) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + nuevaReserva.getIdVehiculo());
        }

        // Validar las fechas
        if (nuevaReserva.getFechaInicioReserva().after(nuevaReserva.getFechaFinReserva())) {
            throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha final.");
        }

        // Validar que el vehículo está disponible
        VehiculoEntity vehiculo = vehiculoRepository.findById(nuevaReserva.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + nuevaReserva.getIdVehiculo()));

        if (!vehiculo.isDisponibilidad()) {
            throw new RuntimeException("El vehículo no está disponible para reservar.");
        }

        // Guardar la reserva
        ReservaEntity reservaGuardada = reservaRepository.save(nuevaReserva);

        System.out.println("Reserva guardada con éxito en ReservaService: " + reservaGuardada);
        return reservaGuardada;
    }

    /**
     * Obtiene el estado de una reserva por su ID.
     *
     * @param idReserva El ID de la reserva.
     * @return El estado de la reserva.
     */
    public int estadoDeReserva(Long idReserva) {
        return reservaRepository.StateReservationByIdReservation(idReserva);
    }

    /**
     * Verifica si un usuario tiene una reserva activa.
     *
     * @param userId El ID del usuario.
     * @return true si el usuario tiene una reserva activa, false en caso contrario.
     */
    public boolean tieneReservaActiva(Long userId) {
        return !reservaRepository.findByIdUsuarioAndEstadoReserva(userId, 0).isEmpty();
    }

    /**
     * Obtiene una reserva por su ID de usuario.
     *
     * @param id El ID del usuario.
     * @return La reserva correspondiente al ID del usuario proporcionado, o null si no se encuentra.
     */
    public ReservaEntity getReservaById(Long id) {
        Optional<ReservaEntity> getReserva = reservaRepository.findByIdUsuario(id);
        return getReserva.orElse(null);
    }
}
