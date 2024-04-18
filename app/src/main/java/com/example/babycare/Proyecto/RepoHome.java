package com.example.babycare.Proyecto;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Consejo.Consejo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoHome {
    @GET("/actividad/obtenerActividad/{id}")
    Call<Actividad> getActividad(@Path("id") int id);

    @GET("/consejo/obtenerConsejo/{id}")
    Call<Consejo> getConsejo(@Path("id") int id);

}
