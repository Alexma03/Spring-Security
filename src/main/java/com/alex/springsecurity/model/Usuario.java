package com.alex.springsecurity.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String nombre;

    private String apellidos;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    private String direccion;

    //bi-directional many-to-many association to Perfile
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfiles"
            , joinColumns = {
            @JoinColumn(name = "USERNAME")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "ID_PERFIL")
    }
    )
    private List<Perfil> perfiles;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private int enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}