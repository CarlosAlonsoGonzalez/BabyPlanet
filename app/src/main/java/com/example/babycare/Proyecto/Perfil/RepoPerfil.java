package com.example.babycare.Proyecto.Perfil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface RepoPerfil {
    @GET("/usuario/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int id);

    @PATCH("/usuario/{id}")
    Call<Usuario> actualizarUsuario(@Path("id") int id, @Body Map<String, Object> actualizaciones);
}
