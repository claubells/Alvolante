package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public int createReserva(Date fechaInicioReserva, Date fechaFinReserva, boolean quiereExtras, int estadoReserva, int horaReserva, float costoReserva, UsuarioEntity usuarioReserva, VehiculoEntity vehiculoReserva, List<ExtraEntity> extrasReserva) {
        //verificar si hay una reserva activa con el mismo id del vehículo

        System.out.println("\nServicio createReserva\n");
        if(tieneReservaActiva(usuarioReserva.getIdUsuario())){
            System.out.println("\nTIENE UNA RESERVA ACTIVA\n");
            return 1;// No se puede hacer la reserva pq tiene una activa
        }

        if(fechaInicioReserva.after(fechaFinReserva)) {
            System.out.println("\nLA FECHA INICIAL ES POSTERIOR A LA FINAL\n");
            return 2; //la fecha inicial es posterior a la final
        }

        if(quiereExtras){
            System.out.println("\nQUIERE EXTRAS\n");
            //llevarlo a crear un extra --> estado de reserva 0
            ReservaEntity nuevaReserva = new ReservaEntity(fechaInicioReserva, fechaFinReserva, quiereExtras, 0, horaReserva, costoReserva, usuarioReserva, vehiculoReserva, extrasReserva);
            reservaRepository.save(nuevaReserva);

            System.out.println("\nId Reserva: ");
            System.out.println(nuevaReserva.getIdReserva());

            System.out.println("Reserva creada SÍ quiere extras\n");
            System.out.println(nuevaReserva.toString());
            return 3;//quiere reservar
        }
        //--> estado de reserva 1
        System.out.println("\nSE crea una reserva sin extras\n");
        ReservaEntity nuevaReserva = new ReservaEntity(fechaInicioReserva, fechaFinReserva, quiereExtras, 1, horaReserva, costoReserva, usuarioReserva, vehiculoReserva, extrasReserva);
        reservaRepository.save(nuevaReserva);
        System.out.println("Reserva creada NO quiere extras\n");
        System.out.println(nuevaReserva.toString());
        System.out.println("\nreturn 0\n");
        return 0; // reserva lista sin reservar


    }

    public int estadoDeReserva(Long idReserva) {
        return reservaRepository.StateReservationByIdReservation(idReserva);
    }

    public boolean tieneReservaActiva(Long userId){
        //buscamos las reservas activas segun el id en repository
        if(reservaRepository.findActiveReservationByUserId(userId).isEmpty()){
            return false; // no encotro, esta vacio
        }
        return true; // tiene reservas, no esta vacio
    }
}