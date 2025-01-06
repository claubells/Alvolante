package com.Alvolante.Backend.Controller;

import com.Alvolante.Backend.Entity.ReservaEntity;
import com.Alvolante.Backend.Entity.UsuarioEntity;
import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.UsuarioRepository;
import com.Alvolante.Backend.Repository.VehiculoRepository;
import com.Alvolante.Backend.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @PreAuthorize("hasRole('CLIENTE') or hasRole('ADMIN')")
    @PostMapping("/crear-reserva/{idUsuario}")
    public int createReserva(@RequestBody ReservaEntity nuevaReserva,
                             @PathVariable Long idUsuario,
                             @RequestParam Long idVehiculo) {
        try {
            System.out.println("\nCREANDO RESERVA\n");

            UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
            if( usuario == null ) {
                return -1; //no se pudo encontrar el usuario
            }
            System.out.println("\nusuario id: ");
            System.out.println(usuario.getIdUsuario());
            nuevaReserva.setUsuarioReserva(usuario);

            System.out.println("\nVEHICULO ");
            VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                    .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + idVehiculo));
            if( vehiculo == null ) {
                System.out.println("\nno se encontro el vehiculo de id: "+idVehiculo);
                return -2; //no se pudo encontrar el vehiculo
            }
            System.out.println("\nvehiculo id: ");
            System.out.println(vehiculo.getIdVehiculo());
            nuevaReserva.setVehiculoReserva(vehiculo);


            System.out.println("\nId Reserva: ");
            System.out.println(nuevaReserva.getIdReserva());
            System.out.println("\nFecha Inicio: ");
            System.out.println(nuevaReserva.getFechaInicioReserva());
            System.out.println("\nFecha Final: ");
            System.out.println(nuevaReserva.getFechaFinReserva());
            System.out.println("\nQuiere Extras?: ");
            System.out.println(nuevaReserva.isQuiereExtras());
            System.out.println("\nEstado de Reserva: ");
            System.out.println(nuevaReserva.getEstadoReserva());
            System.out.println("\nHora de Reserva: ");
            System.out.println(nuevaReserva.getHoraReserva());
            System.out.println("\nCosto reserva: ");
            System.out.println(nuevaReserva.getCostoReserva());
            System.out.println("\nUsuario: ");
            System.out.println("\n\tidUsuario: ");
            System.out.println(nuevaReserva.getUsuarioReserva().getIdUsuario());
            System.out.println("\nVehiculo: ");
            System.out.println("\n\tid Vehiculo: ");
            System.out.println(nuevaReserva.getVehiculoReserva().getIdVehiculo());

            int resultado = reservaService.createReserva(
                    nuevaReserva.getFechaInicioReserva(),
                    nuevaReserva.getFechaFinReserva(),
                    nuevaReserva.isQuiereExtras(),
                    nuevaReserva.getEstadoReserva(),
                    nuevaReserva.getHoraReserva(),
                    nuevaReserva.getCostoReserva(),
                    nuevaReserva.getUsuarioReserva(),
                    nuevaReserva.getVehiculoReserva(),
                    nuevaReserva.getExtrasReserva()
            );

            if(resultado == 1){
                return resultado; // No se puede hacer la reserva pq tiene una activa
            }
            if (resultado== 2){
                return resultado; //la fecha inicial es posterior a la final
            }
            if (resultado == 3){
                return resultado; // quiere reservar
            }
            if (resultado == 0){
                System.out.println("\nRESULTADO = 0 no quiere reserva\n");
                return resultado; //sin reserva
            }

            return -1; //reserva realizada

        } catch (Exception e) {
            return -2;
        }
    }

    @GetMapping("/reservaPorId/{id}")
    public ReservaEntity findReservaById(@PathVariable Long id){return reservaService.getReservaById(id);}


}