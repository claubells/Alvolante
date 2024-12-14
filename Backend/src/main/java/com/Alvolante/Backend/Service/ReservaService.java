package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Repository.ExtraRepository;
import com.Alvolante.Backend.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExtraService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ExtraService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public boolean tieneReservaActiva(Long userId){
        //buscamos las reservas activas segun el id en repository
        if(reservaRepository.findActiveReservationByUserId(userId).isEmpty()){
            return true; // si tiene reservas
        }
        return false; // no tiene reservas
    }

    public boolean quiereReserva(Long userId){
        // vemos si quiere una reserva o no
        if(reservaRepository.findActiveReservationByUserId(userId).isEmpty()){
            return false;
        }
        return true; //si quiere reserva
    }
}