package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepo;

    public UsuarioEntity register(String rut, String name, String email, String phone, Date fechaDeNacimiento, String password, String rol) {
        UsuarioEntity existenteEmail = userRepo.findByEmail(email);
        if(existenteEmail != null ) { // si el usuarioEmail es distinto de vacio
            throw new RuntimeException("El correo "+ email +" ya está registrado. Usa uno diferente.");  // es decir, existe retornamos null
        }

        UsuarioEntity user = new UsuarioEntity(rut, name, email, phone, fechaDeNacimiento, password, rol);
        userRepo.save(user);
        return user;
    }

    public int login(String email, String password) {
        UsuarioEntity user = userRepo.findByEmail(email);
        if(user != null) { // si el usuarioEmail o tur es distinto de vacio, existe, le pedimos la contraseña
            if (password.equals(user.getPassword())) { // Se le aplican diferentes roles, por lo que se retorna un valor distinto para cada uno
                // Cambiar el valor de retorno si es necesario
                if(user.getRol().equals("admin")){ 
                    return 1;
                }
                else if(user.getRol().equals("trabajador")){
                    return 2;
                }
                else if(user.getRol().equals("usuario")){ // El retorno de usuario sigue siendo el mismo, por lo que no deberia generar problemas
                    return 3;
                }
            }
        }
        return 0;
    }

    public UsuarioEntity getUserById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }



}
