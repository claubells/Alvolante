package com.Alvolante.Backend.Service;

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

    /**
     * Crea un nuevo arriendo.
     *
     * @param nuevoArriendo Los detalles del nuevo arriendo.
     * @return El arriendo creado.
     */
    public ArriendoEntity crearArriendo(ArriendoEntity nuevoArriendo) {
        return arriendoRepository.save(nuevoArriendo);
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
