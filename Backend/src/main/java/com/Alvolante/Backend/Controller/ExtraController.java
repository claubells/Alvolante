package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Service.ExtraService;
import com.Alvolante.Backend.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/extras")
public class ExtraController {
    @Autowired
    private ExtraService extraService;
    @Autowired
    private ReservaService reservaService;


    @PostMapping("/crear-extra")
    public int createExtra(@RequestBody ExtraEntity nuevoExtra) {
        try {
            int resultado = extraService.createExtra(
                    nuevoExtra.getTipoExtra(),
                    nuevoExtra.getMaxCantidadExtra(),
                    nuevoExtra.getCantidadSeleccionada(),
                    nuevoExtra.getNombreExtra(),
                    nuevoExtra.getDescripcionExtra(),
                    nuevoExtra.getCostoExtra(),
                    nuevoExtra.isDisponibilidadExtra(),
                    nuevoExtra.getFotoExtra()
            );

            if(resultado == 1){
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 1; // ya existe este extra
            }



            return 0; //exito
        } catch (Exception e) {
            return -2;
        }
    }



    @PostMapping("/agregar-extra-a-reserva")
    public int agregarExtra(@RequestParam Long idUsuario, @RequestParam Long idReserva, @RequestBody List<ExtraEntity> extrasParaLaReserva) {

        // aqui se pregunta el estado de la reserva,
        // si es 0 quiere extra, sino no quiere
        if (reservaService.estadoDeReserva(idReserva) == 1 || reservaService.estadoDeReserva(idReserva) == 2) {
            return -3;//no quiere extras, pq esta activo o inactivo
        }


        try {
            //preguntar cuales extra quiere
            ReservaEntity reservaPendiente = extraService.obtenerReservaPendiente(idUsuario);

            if (reservaPendiente == null) {
                return -1; // No se encontró una reserva pendiente
            }

            for(ExtraEntity extra : extrasParaLaReserva) {
                ExtraEntity extraActual = extraService.obtenerExtraPorId(extra.getIdExtra());

                if(extraActual == null) {
                    return -2; //No se encontró el extra a añadir
                }

                extraActual.setCantidadSeleccionada(extra.getCantidadSeleccionada());
                reservaPendiente.getExtrasReserva().add(extraActual);
            }
            extraService.actualizarReserva(reservaPendiente);

            //extraService.actualizarEstadoReserva(reservaPendiente);

            return 0; // todo se añadio correctamente

        } catch (Exception e) {
            return -3;
        }

    }

}