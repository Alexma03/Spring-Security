package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll() {
        List<Evento> eventos = eventoRepository.findAll();
        actualizarEventosTerminados(eventos);
        return eventos;
    }

    public Evento findById(int id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento != null) {
            actualizarEventoTerminado(evento);
        }
        return evento;
    }

    public void save(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteById(int id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> findByEstado(String estado) {
        List<Evento> eventos = eventoRepository.findByEstado(estado);
        actualizarEventosTerminados(eventos);
        return eventos;
    }

    public List<Evento> findByDestacado(String destacado) {
        List<Evento> eventos = eventoRepository.findByDestacado(destacado);
        actualizarEventosTerminados(eventos);
        return eventos;
    }

    private void actualizarEventosTerminados(List<Evento> eventos) {
        Date hoy = new Date();
        for (Evento evento : eventos) {
            if (evento.getFechaFin().before(hoy) && !"Terminado".equals(evento.getEstado())) {
                evento.setEstado("Terminado");
                eventoRepository.save(evento);
            }
        }
    }

    private void actualizarEventoTerminado(Evento evento) {
        Date hoy = new Date();
        if (evento.getFechaFin().before(hoy) && !"Terminado".equals(evento.getEstado())) {
            evento.setEstado("Terminado");
            eventoRepository.save(evento);
        }
    }
}