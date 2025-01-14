package com.alex.springsecurity.model;

import java.io.Serial;
import java.io.Serializable;

import java.util.Date;


public class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private String email;

    private String apellidos;

    private String direccion;

    private int enabled;

    private Date fechaRegistro;

    private String nombre;

    private String password;

    public Usuario(String username, String email, String nombre) {
        this.username = username;
        this.email = email;
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [username=" + username + ", apellidos=" + apellidos + ", direccion=" + direccion + ", enabled="
                + enabled + ", fechaRegistro=" + fechaRegistro + ", nombre=" + nombre + ", password=" + password
                + "]";
    }

}