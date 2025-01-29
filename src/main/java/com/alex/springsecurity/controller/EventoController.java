package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.repository.TipoRepository;
import com.alex.springsecurity.service.EventoService;
import com.alex.springsecurity.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private TipoRepository tipoRepo;

    @GetMapping("")
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.findAll();
        Map<Integer, Integer> reservasRestantes = new HashMap<>();
        for (Evento evento : eventos) {
            int numReservas = reservaService.countByEvento(evento);
            int reservasRestantesEvento = evento.getAforoMaximo() - numReservas;
            reservasRestantes.put(evento.getIdEvento(), reservasRestantesEvento);
        }
        model.addAttribute("eventos", eventos);
        model.addAttribute("reservasRestantes", reservasRestantes);
        return "index";
    }

    @GetMapping("/activos")
    public String listarEventosActivos(Model model) {
        List<Evento> eventos = eventoService.findByEstado("ACTIVO");
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/destacados")
    public String listarEventosDestacados(Model model) {
        List<Evento> eventos = eventoService.findByDestacado("S");
        model.addAttribute("eventos", eventos);
        return "eventos/eventosDestacados";
    }

    @GetMapping("/cancelados")
    public String listarEventosCancelados(Model model) {
        List<Evento> eventos = eventoService.findByEstado("CANCELADO");
        model.addAttribute("eventos", eventos);
        return "eventos/eventosCancelados";
    }

    @GetMapping("/terminados")
    public String listarEventosTerminados(Model model) {
        List<Evento> eventos = eventoService.findByEstado("TERMINADO");
        model.addAttribute("eventos", eventos);
        return "eventos/eventosTerminados";
    }

    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("tipos", tipoRepo.findAll());
        return "eventos/altaEventos";
    }

    @PostMapping("/alta")
    public String guardarEvento(Evento evento) {
        evento.setEstado("ACTIVO");
        eventoService.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Evento evento = eventoService.findById(id);
        model.addAttribute("evento", evento);
        model.addAttribute("tipos", tipoRepo.findAll());
        return "eventos/editarEventos";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEvento(@PathVariable int id, Evento evento) {
        evento.setIdEvento(id);
        eventoService.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelarEvento(@PathVariable int id) {
        Evento evento = eventoService.findById(id);
        evento.setEstado("CANCELADO");
        if ("S".equals(evento.getDestacado())) {
            evento.setDestacado("");
        }
        eventoService.save(evento);
        return "redirect:/eventos";
    }

    // Activar evento
    @GetMapping("/activar/{id}")
    public String activarEvento(@PathVariable int id) {
        Evento evento = eventoService.findById(id);
        evento.setEstado("ACTIVO");
        eventoService.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.findById(id);
        int reservas = reservaService.countByEvento(evento);
        int maxReservasDisponibles = evento.getAforoMaximo() - reservas;
        model.addAttribute("evento", evento);
        model.addAttribute("reservas", reservas);
        model.addAttribute("maxReservasDisponibles", maxReservasDisponibles);
        return "eventos/detalleEvento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.deleteById(id);
        return "redirect:/eventos";
    }
}