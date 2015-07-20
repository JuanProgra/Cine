package com.kinal.ejemplocine.Interface;

import com.kinal.ejemplocine.Model.Pelicula;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Godinez Miranda on 20/07/2015.
 */
public interface PeliculaService {
    @GET("/pelicula")
    void getPelicula(Callback<List<Pelicula>> callback);

}
