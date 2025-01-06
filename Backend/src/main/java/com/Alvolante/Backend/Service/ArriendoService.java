package com.Alvolante.Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Alvolante.Backend.Entity.ArriendoEntity;
import com.Alvolante.Backend.Repository.ArriendoRepository;
import java.util.List;

@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    public ArriendoEntity crearArriendo(ArriendoEntity nuevoArriendo) {
        return arriendoRepository.save(nuevoArriendo);
    }

    public ArriendoEntity getArriendoById(Long id) {
        return arriendoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arriendo no encontrado con ID: " + id));
    }

    // Nuevo método para buscar por rutAsociado
    public List<ArriendoEntity> getArriendosByRutAsociado(String rutAsociado) {
        return arriendoRepository.findByRutAsociado(rutAsociado);
    }

    // Nuevo método para buscar por estadoPago
    public List<ArriendoEntity> getArriendosByEstadoPago(boolean estadoPago) {
        return arriendoRepository.findByEstadoPago(estadoPago);
    }
}