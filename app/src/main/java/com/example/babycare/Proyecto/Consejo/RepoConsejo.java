package com.example.babycare.Proyecto.Consejo;

import com.example.babycare.Proyecto.Consejo.Consejo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoConsejo {

    @GET("/consejo/obtenerTodos")
    Call<List<Consejo>> getConsejo();
}
