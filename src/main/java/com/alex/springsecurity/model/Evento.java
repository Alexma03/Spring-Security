package com.alex.springsecurity.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the eventos database table.
 */
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private int idEvento;

    @Column(name = "AFORO_MAXIMO")
    private int aforoMaximo;

    private String descripcion;

    private String destacado;

    private String direccion;

    private int duracion;

    private String estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Column(name = "MINIMO_ASISTENCIA")
    private int minimoAsistencia;

    private String nombre;

    private BigDecimal precio;

    @Column(name = "UNIDAD_DURACION")
    private String unidadDuracion;

    //uni-directional many-to-one association to Tipo
    @ManyToOne
    @JoinColumn(name = "ID_TIPO")
    private Tipo tipo;

    public Evento() {
    }

    public int getIdEvento() {
        return this.idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getAforoMaximo() {
        return this.aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestacado() {
        return this.destacado;
    }

    public void setDestacado(String destacado) {
        this.destacado = destacado;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getMinimoAsistencia() {
        return this.minimoAsistencia;
    }

    public void setMinimoAsistencia(int minimoAsistencia) {
        this.minimoAsistencia = minimoAsistencia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getUnidadDuracion() {
        return this.unidadDuracion;
    }

    public void setUnidadDuracion(String unidadDuracion) {
        this.unidadDuracion = unidadDuracion;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}