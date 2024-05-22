package com.example.miravereda.model;

import com.example.miravereda.API.Conversor;

import java.io.Serializable;
import java.util.List;

public class ContenidoAudiovisual implements Serializable {
    private int id;
    private String tipo;
    private String titulo;
    private String descripcion;
    private String genero;
    private int duracion;
    private long fechaEstreno;
    private String nombreDirector;
    private double valoracionMedia;
    private int idTarifa;
    private String imagenUrl;
    private int precio;
    private int precioConTarifa;
    private String versionIdioma;
    private List<Actor> actores;

    public ContenidoAudiovisual fromJson(String json) {
        ContenidoAudiovisual ca = Conversor.getConversor().fromJson(json, ContenidoAudiovisual.class);
        if(ca.tipo.equals("pelicula"))
            return Conversor.getConversor().fromJson(json, Pelicula.class);
        else if(ca.tipo.equals("capitulo"))
            return Conversor.getConversor().fromJson(json, Capitulo.class);
        return ca;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getValoracionMedia() {
        return valoracionMedia;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public long getFechaEstreno() {
        return fechaEstreno;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public int getPrecio() {
        return precio;
    }

    public int getPrecioConTarifa() {
        return precioConTarifa;
    }

    public String getVersionIdioma() {
        return versionIdioma;
    }

    public List<Actor> getActores() {
        return actores;
    }

    @Override
    public String toString() {
        return "ContenidoAudiovisual{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", genero='" + genero + '\'' +
                ", duracion=" + duracion +
                ", fechaEstreno=" + fechaEstreno +
                ", nombreDirector='" + nombreDirector + '\'' +
                ", valoracionMedia=" + valoracionMedia +
                ", idTarifa=" + idTarifa +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", precio=" + precio +
                ", precioConTarifa=" + precioConTarifa +
                ", versionIdioma='" + versionIdioma + '\'' +
                ", actores=" + actores +
                '}';
    }
}
