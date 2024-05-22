package com.example.miravereda.model;

import java.sql.Timestamp;

public class NewUsuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private long fechaNacimiento;
    private String contrasenya;
    private String domicilio;
    private String codigoPostal;

    public NewUsuario(int id, String nombre, String apellidos, String email, long fechanacimiento, String password, String domicilio, String codigopostal) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechanacimiento;
        this.contrasenya = password;
        this.domicilio = domicilio;
        this.codigoPostal = codigopostal;
    }

    public NewUsuario(String nombre, String apellidos, String email, long fechanacimiento, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechanacimiento;
        this.contrasenya = password;
    }

    public void setPassword(String password) {
        this.contrasenya = password;
    }
}
