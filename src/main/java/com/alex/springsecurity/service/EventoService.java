package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Evento findById(int id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void save(Evento evento) {
        eventoRepository.save(evento);
    }

    public List<Evento> findByEstado(String estado) {
        return eventoRepository.findByEstado(estado);
    }

    public List<Evento> findByDestacado(String destacado) {
        return eventoRepository.findByDestacado(destacado);
    }
}