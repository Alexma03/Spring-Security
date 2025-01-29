package com.alex.springsecurity.service;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Reserva;
import com.alex.springsecurity.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findByEvento(Evento evento) {
        return reservaRepository.findByEvento(evento);
    }

    public int countByEvento(Evento evento) {
        return reservaRepository.countByEvento(evento);
    }

    public void save(Reserva reserva) {
        reservaRepository.save(reserva);
    }
}