package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.usuarioEntity;
import com.Alvolante.Backend.Repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class usuarioService {
    @Autowired
    private usuarioRepository userRepo;

    public usuarioEntity register(String rut, String name, String email, String phone, Date fechaDeNacimiento, String password, String rol) {
        usuarioEntity existenteEmail = userRepo.findByEmail(email);
        usuarioEntity existenteRut = userRepo.findByRut(rut);
        if(existenteEmail != null ) { // si el usuarioEmail es distinto de vacio
            throw new RuntimeException("El correo "+ email +" ya está registrado. Usa uno diferente.");  // es decir, existe retornamos null
        }
        if (existenteRut != null) {
            throw new RuntimeException("El RUT "+ rut +" ya está registrado. Por favor intente nuevamente.");
        }
        usuarioEntity user = new usuarioEntity(rut, name, email, phone, fechaDeNacimiento, password, rol);
        userRepo.save(user);
        return user;
    }

    public int login(String email, String password) {
        usuarioEntity user = userRepo.findByEmail(email);
        if(user != null) { // si el usuarioEmail o tur es distinto de vacio, existe, le pedimos la contraseña
            if (password.equals(user.getPassword())) {
                if(user.getRol().equals("usuario")){
                    return 3;
                }
            }
        }
        return 0;
    }

    public usuarioEntity getUserById(long id) {
        return userRepo.findById(id).get();
    }



}
