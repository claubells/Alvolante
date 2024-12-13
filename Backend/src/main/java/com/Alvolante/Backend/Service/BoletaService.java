package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.BoletaEntity;
import com.Alvolante.Backend.Repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepo;

    public BoletaEntity generarBoleta(long idBoleta, String nombreEmisor, String rutEmisor, String direccionEmisor, String nombreCliente, String rutCliente, LocalDate fechaEmision, LocalTime horaEmision, double subtotal, double iva, double total, String formaPago) {

        BoletaEntity nuevaBoleta = new BoletaEntity(idBoleta, nombreEmisor, rutEmisor,direccionEmisor,nombreCliente,rutCliente,fechaEmision,horaEmision,subtotal,iva,total,formaPago);


        return nuevaBoleta;
    }
}
