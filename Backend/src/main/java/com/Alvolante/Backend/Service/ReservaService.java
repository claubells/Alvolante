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

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        /*
        if(nuevaReserva.isQuiereExtras()){
            System.out.println("\nQUIERE EXTRAS\n");
            //llevarlo a crear un extra --> estado de reserva 0
            ReservaEntity nuevaReserva = new ReservaEntity(fechaInicioReserva, fechaFinReserva, quiereExtras, 0, horaReserva, costoReserva, usuarioReserva, vehiculoReserva, extrasReserva);
            reservaRepository.save(nuevaReserva);

            System.out.println("\nId Reserva: ");
            System.out.println(nuevaReserva.getIdReserva());

            System.out.println("Reserva creada SÍ quiere extras\n");
            System.out.println(nuevaReserva.toString());
            return 3;//quiere reservar
        }*/

        // Guardar la reserva
        ReservaEntity reservaGuardada = reservaRepository.save(nuevaReserva);

        System.out.println("Reserva guardada con éxito en ReservaService: " + reservaGuardada);
        return reservaGuardada;
    }

    public int estadoDeReserva(Long idReserva) {
        return reservaRepository.StateReservationByIdReservation(idReserva);
    }

    public boolean tieneReservaActiva(Long userId){
        //buscamos las reservas activas segun el id en repository
        if(reservaRepository.findByIdUsuarioAndEstadoReserva(userId, 0).isEmpty()){
            return false; // no encotro, esta vacio
        }
        return true; // tiene reservas, no esta vacio
    }

    public ReservaEntity getReservaById(Long id) {
        Optional<ReservaEntity> getReserva = reservaRepository.findByIdUsuario(id);
        if (getReserva.isPresent()) {
            return getReserva.get();
        }else {
            return null;
        }
    }

}