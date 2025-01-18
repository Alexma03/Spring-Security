package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.repository.TipoRepository;
import com.alex.springsecurity.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private TipoRepository tipoRepo;

    @GetMapping("")
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.findAll();
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/activos")
    public String listarEventosActivos(Model model) {
        List<Evento> eventos = eventoService.findByEstado("Activo");
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/destacados")
    public String listarEventosDestacados(Model model) {
        List<Evento> eventos = eventoService.findByDestacado("S");
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/cancelados")
    public String listarEventosCancelados(Model model) {
        List<Evento> eventos = eventoService.findByEstado("Cancelado");
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/terminados")
    public String listarEventosTerminados(Model model) {
        List<Evento> eventos = eventoService.findByEstado("Terminado");
        model.addAttribute("eventos", eventos);
        return "index";
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
        if (evento.getDestacado() == null) {
            evento.setDestacado("N");
        }
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
        evento.setEstado("Cancelado");
        eventoService.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.findById(id);
        model.addAttribute("evento", evento);
        return "detalleEvento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.deleteById(id);
        return "redirect:/eventos";
    }
}
