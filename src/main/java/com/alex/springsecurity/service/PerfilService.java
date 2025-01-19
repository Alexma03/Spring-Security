package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    public Perfil findById(int id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    public void deleteById(int id) {
        perfilRepository.deleteById(id);
    }
}