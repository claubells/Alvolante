package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Repository.ExtraRepository;
import com.Alvolante.Backend.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ExtraRepository extraRepository;


    public int createExtra(int tipoExtra, int maxCantidadExtra, int cantidadSeleccionada, String nombreExtra, String descripcionExtra, String costoExtra, boolean disponibilidadExtra, byte[] fotoExtra) {
        ExtraEntity extraEntity = new ExtraEntity(tipoExtra, maxCantidadExtra, cantidadSeleccionada, nombreExtra, descripcionExtra, costoExtra, disponibilidadExtra, fotoExtra);
        extraRepository.save(extraEntity);
        return 0; //exito
    }

    /*
    public boolean quiereReservar(Long userId){
        // vemos si quiere una reserva o no
        if(reservaRepository.findActiveReservationByUserId(userId)){
            return false;
        }
        return true; //si quiere reserva
    }*/

    public ReservaEntity obtenerReservaPendiente(Long userId){
        return reservaRepository.findPendingReservationByUserId(userId);
    }

    public ExtraEntity obtenerExtraPorId(Long id){
        return extraRepository.findExtraById(id);
    }

    public void actualizarReserva(ReservaEntity reserva){
        reservaRepository.save(reserva);
    }


}