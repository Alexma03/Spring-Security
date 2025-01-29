package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEvento(Evento evento);
    int countByEvento(Evento evento);
}