package com.example.miravereda.model;

public class OperacionUsuarioPelicula {
    private String email;
    private String contrasenya;
    private int idPelicula;

    public OperacionUsuarioPelicula(String email, String contrasenya, int idPelicula) {
        this.email = email;
        this.contrasenya = contrasenya;
        this.idPelicula = idPelicula;
    }
}
