package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("")
    public String listarEventos() {
        return "redirect:/eventos";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());

        return "signup_form";
    }
}