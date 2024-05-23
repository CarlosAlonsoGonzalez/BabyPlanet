package com.example.babycare.Proyecto.Actividad;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Consejo.Consejo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoActividad {
    @GET("/actividad/obtenerTodas")
    Call<List<Actividad>> getActividades();
    @GET("/actividad/obtenerActividadesEdad/{edadHijo}")
    Call<List<Actividad>> getActividadesPorEdad(@Path("edadHijo") int edad);


}
