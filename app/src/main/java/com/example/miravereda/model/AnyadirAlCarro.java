package com.example.miravereda.model;

public class AnyadirAlCarro {
    private Credenciales credenciales;
    private int idPelicula;

    public AnyadirAlCarro(Credenciales credenciales, int id) {
        this.credenciales = credenciales;
        this.idPelicula = id;
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public int getId() {
        return idPelicula;
    }
}
