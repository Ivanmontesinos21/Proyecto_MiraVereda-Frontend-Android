package com.example.miravereda.model;

public class Credenciales {
    private String email;
    private String contrasenya;

    public Credenciales(String email, String contrasenya) {
        this.email = email;
        this.contrasenya =contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenya() {
        return contrasenya;
    }
}
