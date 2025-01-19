package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Tipo;
import com.alex.springsecurity.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping("")
    public String listarTipos(Model model) {
        List<Tipo> tipos = tipoService.findAll();
        model.addAttribute("tipos", tipos);
        return "tipo/tipos";
    }

    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("tipo", new Tipo());
        return "tipo/creacionTipo";
    }

    @PostMapping("/alta")
    public String crearTipo(@ModelAttribute Tipo tipo) {
        tipoService.save(tipo);
        return "redirect:/tipos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Tipo tipo = tipoService.findById(id);
        model.addAttribute("tipo", tipo);
        return "tipo/edicionTipo";
    }

    @PostMapping("/editar")
    public String editarTipo(@ModelAttribute Tipo tipo) {
        tipoService.save(tipo);
        return "redirect:/tipos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTipo(@PathVariable int id) {
        tipoService.deleteById(id);
        return "redirect:/tipos";
    }
}