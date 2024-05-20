package com.example.miravereda.model;

import com.example.miravereda.API.Conversor;

import java.util.List;

public class ContenidoAudiovisual {
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
}
