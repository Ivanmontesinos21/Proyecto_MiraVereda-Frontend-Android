package com.example.miravereda;

public class VistaPelicula {
    private int id;
    private int imagen;
    private String titulo;
    private String autor;
    private double precio;
    private float nota;
    private float notaMedia;

    public VistaPelicula(int id, int imagen, String titulo, String autor, double precio, float nota, float notaMedia) {
        this.id=id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.nota = nota;
        this.notaMedia = notaMedia;
    }

    public int getId() {
        return id;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecio() {
        return precio;
    }

    public float getNota() {
        return nota;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }
}

