package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Tipo;
import com.alex.springsecurity.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public List<Tipo> findAll() {
        return tipoRepository.findAll();
    }

    public Tipo findById(int id) {
        return tipoRepository.findById(id).orElse(null);
    }

    public void save(Tipo tipo) {
        tipoRepository.save(tipo);
    }

    public void deleteById(int id) {
        tipoRepository.deleteById(id);
    }
}