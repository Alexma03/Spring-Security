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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/misReservas")
    public String listarReservas(Authentication authentication, Model model) {
        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        List<Reserva> reservas = reservaService.findByUsuario(usuario);
        model.addAttribute("reservas", reservas);
        return "reservas/misReservas";
    }

    @GetMapping("/cancelar/{reserva_id}")
    public String cancelarReserva(@PathVariable int reserva_id) {
        reservaService.deleteById(reserva_id);
        return "redirect:/reservas/misReservas";
    }

    @PostMapping("/reservar/{user_id}")
    public String reservarEvento(@PathVariable int user_id, @RequestParam int cantidad, Authentication authentication) {
        Evento evento = eventoService.findById(user_id);
        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        int totalReservas = reservaService.countByEvento(evento);
        if (totalReservas + cantidad > 10) {
            return "redirect:/eventos/detalle/" + user_id + "?error=exceeded";
        }

        for (int i = 0; i < cantidad; i++) {
            Reserva reserva = new Reserva();
            reserva.setEvento(evento);
            reserva.setUsuario(usuario);
            reserva.setCantidad(1);
            reserva.setPrecioVenta(evento.getPrecio());
            reservaService.save(reserva);
        }

        return "redirect:/";
    }
}
