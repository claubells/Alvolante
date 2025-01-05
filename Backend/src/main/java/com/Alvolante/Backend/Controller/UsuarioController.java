package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Service.UsuarioService;
import com.Alvolante.Backend.dto.LoginDto;
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
    @Autowired
    private UsuarioService usuarioService;

    //Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioEntity nuevo) {

        try {
            UsuarioEntity user = userservice.register(nuevo.getRut(), nuevo.getName(), nuevo.getEmail(), nuevo.getPhone(), nuevo.getBirthdate(), nuevo.getPassword(), nuevo.getRole());
            return ResponseEntity.ok(user); //retorna al usuario si todo salio ok
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // imprime el error
        }
    }
    //Realizar login del usuario
    @PostMapping("/login")
    public int login(@RequestBody LoginDto loginDto) {
        return usuarioService.login(loginDto.getEmail(), loginDto.getPassword());
    }

    @GetMapping("/id")
    public UsuarioEntity getUsuarioById(@RequestParam("id") String id){
        System.out.printf("Id a buscar: " + id);
        return userservice.getUserById(Integer.parseInt(id));
    }

    @GetMapping("/idByEmail")
    public ResponseEntity<Long> getIdByEmail(@RequestParam String email) {
        try {
            Long id = userservice.getIdByEmail(email);
            return ResponseEntity.ok(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/userByEmail")
    public ResponseEntity<UsuarioEntity> getUserByEmail(@RequestParam String email) {
        try{
            UsuarioEntity user = userservice.getUserByEmail(email);
            System.out.println("\nse encontro Usuario:"+user+"\n");
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            System.out.println("\nhay un error\n");
            throw new RuntimeException("\nError al obtener el usuario\n");
        }
    }

}