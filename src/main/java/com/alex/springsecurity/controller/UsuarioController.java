package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Usuario;
import com.alex.springsecurity.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepo;

    @GetMapping("")
    public String listUsers(Model model, HttpServletRequest request) {
        List<Usuario> listUsuarios = userRepo.findAll();
        model.addAttribute("listUsers", listUsuarios);
        model.addAttribute("request", request);
        return "usuario/usuarios";
    }

    @PostMapping("/procesar_registro")
    public String processRegister(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        userRepo.save(usuario);

        return "register_success";
    }
}

