package com.Alvolante.Backend.Controller;



import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService userservice;

    //Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioEntity nuevo) {
        try {
            UsuarioEntity user = userservice.register(nuevo.getRut(), nuevo.getName(), nuevo.getEmail(), nuevo.getPhone(), nuevo.getFechaDeNacimiento(), nuevo.getPassword(), nuevo.getRol());
            return ResponseEntity.ok(user); //retorna al usuario si todo salio ok
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // imprime el error
        }
    }
    //Realizar login del usuario
    @PostMapping("/login")
    public int login(@RequestBody UsuarioEntity x) {
        return userservice.login(x.getEmail(), x.getPassword());
    }

    @GetMapping("/id")
    public UsuarioEntity getUsuarioById(@RequestParam("id") String id){
        System.out.printf("Id a buscar: " + id);
        return userservice.getUserById(Integer.parseInt(id));
    }


}
