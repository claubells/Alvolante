package com.Alvolante.Backend.Controller;



import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Config.JwtUtil;
import com.Alvolante.Backend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService userservice;

    @Autowired
    private JwtUtil jwtUtil;



    @GetMapping("/id")
    public UsuarioEntity getUsuarioById(@RequestParam("id") String id){
        System.out.printf("Id a buscar: " + id);
        return userservice.getUserById(Integer.parseInt(id));
    }




}
