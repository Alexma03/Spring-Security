package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Reserva;
import com.alex.springsecurity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEvento(Evento evento);

    List<Reserva> findByUsuario(Usuario usuario);

    int countByEvento(Evento evento);

    void deleteByIdReserva(int idReserva);
}