package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.BoletaEntity;
import com.Alvolante.Backend.Repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * BoletaService es una clase de servicio que maneja la lógica de negocio relacionada con las boletas.
 */
@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepo;

    /**
     * Genera una nueva boleta.
     *
     * @param idBoleta El ID de la boleta.
     * @param nombreEmisor El nombre del emisor.
     * @param rutEmisor El RUT del emisor.
     * @param direccionEmisor La dirección del emisor.
     * @param nombreCliente El nombre del cliente.
     * @param rutCliente El RUT del cliente.
     * @param fechaEmision La fecha de emisión.
     * @param horaEmision La hora de emisión.
     * @param subtotal El subtotal de la boleta.
     * @param iva El IVA de la boleta.
     * @param total El total de la boleta.
     * @param formaPago La forma de pago.
     * @return La boleta generada.
     */
    public BoletaEntity generarBoleta(long idBoleta, String nombreEmisor, String rutEmisor, String direccionEmisor, String nombreCliente, String rutCliente, LocalDate fechaEmision, LocalTime horaEmision, double subtotal, double iva, double total, String formaPago) {

        BoletaEntity nuevaBoleta = new BoletaEntity(idBoleta, nombreEmisor, rutEmisor, direccionEmisor, nombreCliente, rutCliente, fechaEmision, horaEmision, subtotal, iva, total, formaPago);

        boletaRepo.save(nuevaBoleta);

        return nuevaBoleta;
    }

    /**
     * Obtiene una boleta por el nombre del cliente.
     *
     * @param nombre El nombre del cliente.
     * @return La boleta correspondiente al nombre del cliente proporcionado.
     */
    public BoletaEntity getBoletaByNombreCliente(String nombre) {
        Optional<BoletaEntity> boleta = Optional.ofNullable(boletaRepo.findByNombreCliente(nombre));
        if (boleta.isPresent()) {
            return boleta.get();
        } else {
            return null;
        }
    }

    /**
     * Obtiene todas las boletas.
     *
     * @return Una lista de todas las boletas.
     */
    public List<BoletaEntity> getAllBoletas() {
        return boletaRepo.findAll();
    }
}
