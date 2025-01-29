package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Reserva;
import com.alex.springsecurity.model.Usuario;
import com.alex.springsecurity.service.EventoService;
import com.alex.springsecurity.service.ReservaService;
import com.alex.springsecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/reservar/{id}")
    public String reservarEvento(@PathVariable int id, @RequestParam int cantidad, Authentication authentication) {
        Evento evento = eventoService.findById(id);
        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        int totalReservas = reservaService.countByEvento(evento);
        if (totalReservas + cantidad > 10) {
            return "redirect:/eventos/detalle/" + id + "?error=exceeded";
        }

        Reserva reserva = new Reserva();
        reserva.setEvento(evento);
        reserva.setUsuario(usuario);
        reserva.setCantidad(cantidad);
        reserva.setPrecioVenta(evento.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        reservaService.save(reserva);

        return "redirect:/";
    }
}
