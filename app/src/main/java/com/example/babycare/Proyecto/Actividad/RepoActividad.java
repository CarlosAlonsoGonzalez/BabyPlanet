package com.example.babycare.Proyecto.Actividad;

import com.example.babycare.Proyecto.Actividad.Actividad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoActividad {
    @GET("/actividad/obtenerTodas")
    Call<List<Actividad>> getActividades();

}
