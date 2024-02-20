package com.example.babycare.Proyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoConsejo {

    @GET("/consejo/obtenerTodos")
    Call<List<Consejo>> getConsejo();
}
