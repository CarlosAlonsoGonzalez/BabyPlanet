package com.example.babycare.Proyecto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface RepoConsejo {

    @GET("/consejo/obtenerConsejo/{id}")
    Call<Consejo> getConsejo(@Path("id") int id);
}
