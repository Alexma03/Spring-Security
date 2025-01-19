package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("")
    public String listarPerfiles(Model model) {
        List<Perfil> perfiles = perfilService.findAll();
        model.addAttribute("perfiles", perfiles);
        return "perfil/perfiles";
    }

    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("perfil", new Perfil());
        return "perfil/creacionPerfil";
    }

    @PostMapping("/alta")
    public String crearPerfil(@ModelAttribute Perfil perfil) {
        perfilService.save(perfil);
        return "redirect:/perfiles";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Perfil perfil = perfilService.findById(id);
        model.addAttribute("perfil", perfil);
        return "perfil/edicionPerfil";
    }

    @PostMapping("/editar")
    public String editarPerfil(@ModelAttribute Perfil perfil) {
        perfilService.save(perfil);
        return "redirect:/perfiles";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPerfil(@PathVariable int id) {
        perfilService.deleteById(id);
        return "redirect:/perfiles";
    }
}