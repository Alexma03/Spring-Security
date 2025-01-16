package com.alex.springsecurity.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;


/**
 * The persistent class for the tipos database table.
 */
@Entity
@Table(name = "tipos")
public class Tipo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO")
    private int idTipo;

    private String descripcion;

    private String nombre;

    public Tipo() {
    }

    public int getIdTipo() {
        return this.idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}