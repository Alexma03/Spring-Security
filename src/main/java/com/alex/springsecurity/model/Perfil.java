package com.alex.springsecurity.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;


/**
 * The persistent class for the perfiles database table.
 */
@Entity
@Table(name = "perfiles")
public class Perfil implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private int idPerfil;

    private String nombre;

    public Perfil() {
    }

    public int getIdPerfil() {
        return this.idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Perfil [idPerfil=" + idPerfil + ", nombre=" + nombre + "]";
    }
}