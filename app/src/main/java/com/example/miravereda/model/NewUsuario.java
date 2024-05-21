package com.example.miravereda.model;

import java.sql.Timestamp;

public class NewUsuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private long fechanacimiento;
    private String password;
    private String domicilio;
    private String codigopostal;

    public NewUsuario(int id, String nombre, String apellidos, String email, long fechanacimiento, String password, String domicilio, String codigopostal) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechanacimiento = fechanacimiento;
        this.password = password;
        this.domicilio = domicilio;
        this.codigopostal = codigopostal;
    }

    public NewUsuario(String nombre, String apellidos, String email, long fechanacimiento, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechanacimiento = fechanacimiento;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
