package com.company;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String contrasena;
    private int idUsuario;
    private static int id = 0;

    public Usuario() {
        this.idUsuario = ++id;
    }

    public Usuario(String nombre, String apellido, String dni, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.usuario = usuario;
        this.contrasena = contrasena;
        setId(id++);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Usuario.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + idUsuario + ", ");
        sb.append("Nombre: " + nombre + ", ");
        sb.append("Apellido: " + apellido + ", ");
        sb.append("Dni: " + dni + ", ");
        return sb.toString();
    }

}
