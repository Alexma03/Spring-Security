package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>(List.of(
        new Usuario("alex", "alexpruebas@gmail", "Alex"),
        new Usuario("pepe", "pepepruebas@gmail", "Pepe"),
        new Usuario("juan", "juanpruebas@gmail", "Juan")));

    @GetMapping("/usuarios")
    public List<Usuario> usuarios() {
        return usuarios;
    }

    @PostMapping("/add-usuario")
    public Usuario crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

}