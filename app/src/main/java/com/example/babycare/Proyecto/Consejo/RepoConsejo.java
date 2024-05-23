package com.example.babycare.Proyecto.Consejo;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Consejo.Consejo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoConsejo {
    @GET("/consejo/obtenerTodos")
    Call<List<Consejo>> getConsejos();
    @GET("/consejo/obtenerConsejosEdad/{edadHijo}")
    Call<List<Consejo>> getConsejosPorEdad(@Path("edadHijo") int edad);
}
