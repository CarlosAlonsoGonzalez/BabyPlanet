package com.example.babycare.Proyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoActividad {
    @GET("/actividad/obtenerTodas")
    Call<List<Actividad>> getActividades();

}
