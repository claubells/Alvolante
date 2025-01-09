package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Service.ExtraService;
import com.Alvolante.Backend.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ExtraController es un controlador que maneja las solicitudes HTTP relacionadas con los extras de las reservas.
 */
@RestController
@RequestMapping("/api/extras")
public class ExtraController {

    @Autowired
    private ExtraService extraService;

    @Autowired
    private ReservaService reservaService;

    /**
     * Endpoint para crear un nuevo extra.
     *
     * @param nuevoExtra Los detalles del nuevo extra.
     * @return Un código indicando el resultado de la operación.
     */
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

            if (resultado == 1) {
                System.out.println("Resultado enviado al frontend: " + resultado);
                return 1; // Ya existe este extra
            }

            return 0; // Éxito
        } catch (Exception e) {
            return -2;
        }
    }

    /**
     * Endpoint para agregar extras a una reserva existente.
     *
     * @param idUsuario El ID del usuario.
     * @param idReserva El ID de la reserva.
     * @param extrasParaLaReserva La lista de extras a añadir a la reserva.
     * @return Un código indicando el resultado de la operación.
     */
    @PostMapping("/agregar-extra-a-reserva")
    public int agregarExtra(@RequestParam Long idUsuario, @RequestParam Long idReserva, @RequestBody List<ExtraEntity> extrasParaLaReserva) {

        // Aquí se pregunta el estado de la reserva,
        // si es 0 quiere extra, sino no quiere
        if (reservaService.estadoDeReserva(idReserva) == 1 || reservaService.estadoDeReserva(idReserva) == 2) {
            return -3; // No quiere extras, porque está activa o inactiva
        }

        try {
            // Preguntar cuáles extras quiere
            ReservaEntity reservaPendiente = extraService.obtenerReservaPendiente(idUsuario);

            if (reservaPendiente == null) {
                return -1; // No se encontró una reserva pendiente
            }

            for (ExtraEntity extra : extrasParaLaReserva) {
                ExtraEntity extraActual = extraService.obtenerExtraPorId(extra.getIdExtra());

                if (extraActual == null) {
                    return -2; // No se encontró el extra a añadir
                }

                extraActual.setCantidadSeleccionada(extra.getCantidadSeleccionada());
                reservaPendiente.getExtrasReserva().add(extraActual);
            }
            extraService.actualizarReserva(reservaPendiente);

            // extraService.actualizarEstadoReserva(reservaPendiente);

            return 0; // Todo se añadió correctamente

        } catch (Exception e) {
            return -3;
        }
    }

}
