package com.example.miravereda.model;

import java.sql.Timestamp;
import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private long fechaNacimiento;
    private String domicilio;

    private String codigopostal;

    private Tarjeta tarjeta;

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }
}
