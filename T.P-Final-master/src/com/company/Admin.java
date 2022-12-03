package com.company;

import java.io.Serializable;

public class Admin extends Usuario implements Serializable {

    private String cargo;

    public Admin() {
        setId(getId());
    }

    public Admin(String nombre, String apellido, String dni, String usuario, String contrasena, String cargo) {
        super(nombre, apellido, dni, usuario, contrasena);
        setId(getId());
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Cargo: " + cargo);
        return sb.toString();
    }

}
