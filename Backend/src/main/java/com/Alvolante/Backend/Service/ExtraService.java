package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ExtraEntity;
import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Repository.ExtraRepository;
import com.Alvolante.Backend.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ExtraService es una clase de servicio que maneja la lógica de negocio relacionada con los extras.
 */
@Service
public class ExtraService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ExtraRepository extraRepository;

    /**
     * Crea un nuevo extra.
     *
     * @param tipoExtra El tipo de extra.
     * @param maxCantidadExtra La cantidad máxima del extra.
     * @param cantidadSeleccionada La cantidad seleccionada del extra.
     * @param nombreExtra El nombre del extra.
     * @param descripcionExtra La descripción del extra.
     * @param costoExtra El costo del extra.
     * @param disponibilidadExtra La disponibilidad del extra.
     * @param fotoExtra La foto del extra.
     * @return Un código indicando el resultado de la operación.
     */
    public int createExtra(int tipoExtra, int maxCantidadExtra, int cantidadSeleccionada, String nombreExtra, String descripcionExtra, String costoExtra, boolean disponibilidadExtra, byte[] fotoExtra) {
        ExtraEntity extraEntity = new ExtraEntity(tipoExtra, maxCantidadExtra, cantidadSeleccionada, nombreExtra, descripcionExtra, costoExtra, disponibilidadExtra, fotoExtra);
        extraRepository.save(extraEntity);
        return 0; // Éxito
    }

    /**
     * Obtiene una reserva pendiente por el ID del usuario.
     *
     * @param userId El ID del usuario.
     * @return La reserva pendiente correspondiente al ID del usuario proporcionado.
     */
    public ReservaEntity obtenerReservaPendiente(Long userId) {
        return reservaRepository.findPendingReservationByUserId(userId);
    }

    /**
     * Obtiene un extra por su ID.
     *
     * @param id El ID del extra.
     * @return El extra correspondiente al ID proporcionado.
     */
    public ExtraEntity obtenerExtraPorId(Long id) {
        return extraRepository.findExtraById(id);
    }

    /**
     * Actualiza una reserva.
     *
     * @param reserva La reserva a actualizar.
     */
    public void actualizarReserva(ReservaEntity reserva) {
        reservaRepository.save(reserva);
    }
}
