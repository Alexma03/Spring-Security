package com.alex.springsecurity.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the reservas database table.
 */
@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private int idReserva;

    private int cantidad;

    private String observaciones;

    @Column(name = "PRECIO_VENTA")
    private BigDecimal precioVenta;

    //uni-directional many-to-one association to Evento
    @ManyToOne
    @JoinColumn(name = "ID_EVENTO")
    private Evento evento;

    //uni-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private User usuario;

    public Reserva() {
    }

    public int getIdReserva() {
        return this.idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Evento getEvento() {
        return this.evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}