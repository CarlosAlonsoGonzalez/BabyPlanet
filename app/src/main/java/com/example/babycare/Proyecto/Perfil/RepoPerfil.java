package com.example.babycare.Proyecto.Perfil;

import com.example.babycare.Proyecto.Inicio.RespuestaLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RepoPerfil {
    @GET("/user/obtenerUsuario/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int id);

    @POST("/user/actualizar")
    Call<Usuario> actualizarUsuario(@Body Usuario usuario);

    @GET("/user/login/{email}/{password}")
    Call<RespuestaLogin> login(@Path("email") String email, @Path("password") String password);
    @POST("/user/crear")
    Call<Usuario> crearUsuario(@Body Usuario usuario);
}
