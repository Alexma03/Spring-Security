package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Evento;
import com.alex.springsecurity.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByEstado(String estado);

    List<Evento> findByDestacado(String destacado);

    List<Evento> findByTipo(Tipo tipo);
}
