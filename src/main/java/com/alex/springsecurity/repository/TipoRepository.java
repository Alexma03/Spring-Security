package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, String> {
}
