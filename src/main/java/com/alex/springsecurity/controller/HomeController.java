package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Usuario;
import com.alex.springsecurity.repository.UserRepository;
import com.alex.springsecurity.service.EventoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.findAll();
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}