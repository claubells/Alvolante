package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Alvolante.Backend.Entity.ArriendoEntity;
import com.Alvolante.Backend.Repository.ArriendoRepository;
import java.util.List;

/**
 * ArriendoService es una clase de servicio que maneja la lógica de negocio relacionada con los arriendos.
 */
@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;
    /**
     * Crea un nuevo arriendo.
     *
     * @param nuevoArriendo Los detalles del nuevo arriendo.
     * @return El arriendo creado.
     * @throws RuntimeException Si ocurre un error durante la validación o el guardado del arriendo.
     */
    public ArriendoEntity createArriendo(ArriendoEntity nuevoArriendo) {
        System.out.println("Ejecutando lógica de negocio en ArriendoService");

        // Validar que el usuario existe
        if (!usuarioRepository.existsById(nuevoArriendo.getIdTrabajador())) {
            throw new RuntimeException("Usuario no encontrado con ID: " + nuevoArriendo.getIdTrabajador());
        }

        // Validar que el vehículo existe
        if (!vehiculoRepository.existsById(nuevoArriendo.getIdVehiculo())) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + nuevoArriendo.getIdVehiculo());
        }

        // Validar las fechas
        if (nuevoArriendo.getFechaInicioArriendo().after(nuevoArriendo.getFechaFinArriendo())) {
            throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha final.");
        }

        // Validar que el vehículo está disponible
        VehiculoEntity vehiculo = vehiculoRepository.findById(nuevoArriendo.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + nuevoArriendo.getIdVehiculo()));

        if (!vehiculo.isDisponibilidad()) {
            throw new RuntimeException("El vehículo no está disponible para reservar.");
        }

        // Guardar el arriendo:
        ArriendoEntity arriendoGuardado = arriendoRepository.save(nuevoArriendo);

        System.out.println("Arriendo guardado con éxito en ArriendoService: " + arriendoGuardado);

        return arriendoGuardado;
    }

    /**
     * Obtiene un arriendo por su ID.
     *
     * @param id El ID del arriendo.
     * @return El arriendo correspondiente al ID proporcionado.
     * @throws RuntimeException Si no se encuentra el arriendo.
     */
    public ArriendoEntity getArriendoById(Long id) {
        return arriendoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arriendo no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de arriendos por el RUT asociado.
     *
     * @param rutAsociado El RUT asociado al arriendo.
     * @return Una lista de arriendos con el RUT asociado proporcionado.
     */
    public List<ArriendoEntity> getArriendosByRutAsociado(String rutAsociado) {
        return arriendoRepository.findByRutAsociado(rutAsociado);
    }

    /**
     * Obtiene una lista de arriendos por el estado de pago.
     *
     * @param estadoPago El estado de pago del arriendo (true = pagado, false = pendiente).
     * @return Una lista de arriendos con el estado de pago proporcionado.
     */
    public List<ArriendoEntity> getArriendosByEstadoPago(boolean estadoPago) {
        return arriendoRepository.findByEstadoPago(estadoPago);
    }
}
