package com.kinal.ejemplocine.Model;

/**
 * Created by Godinez Miranda on 20/07/2015.
 */
public class Pelicula {
    public int id;
    public String titulo;
    public String sinopsis;
    public String trailer_url;
    public String image;
    public String rated;
    public String genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", trailer_url='" + trailer_url + '\'' +
                ", image='" + image + '\'' +
                ", rated='" + rated + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
