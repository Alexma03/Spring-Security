package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Usuario;
import com.alex.springsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}