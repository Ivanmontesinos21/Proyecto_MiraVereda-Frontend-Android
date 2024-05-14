package com.example.miravereda;

import java.util.Date;

public class Cuenta {
    private String nombre;

    private String username;

    private String apellidos;

    private Date fechanacimiento;

    private String email;
    private String contrasenya;

    private String codigopostal;

    private String numeroTarjeta;

    private String domicilio;


    public Cuenta(String username,String nombre, String apellidos, Date fechanacimiento, String email, String contrasenya) {
        this.nombre = nombre;
        this.username=username;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Cuenta(String username,String nombre, String apellidos, Date fechanacimiento, String email, String contrasenya, String codigopostal, String numeroTarjeta, String domicilio) {
        this.nombre = nombre;
        this.username=username;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.email = email;
        this.contrasenya = contrasenya;
        this.codigopostal = codigopostal;
        this.numeroTarjeta = numeroTarjeta;
        this.domicilio = domicilio;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}
