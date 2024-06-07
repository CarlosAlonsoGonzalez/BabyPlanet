package com.example.babycare.Proyecto.Inicio;

import com.example.babycare.Proyecto.Perfil.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RepoInicio {
    @GET("/user/obtenerUsuario/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int id);
    @GET("/user/login/{email}/{password}")
    Call<RespuestaLogin> login(@Path("email") String email, @Path("password") String password);
    @POST("/user/crear")
    Call<Usuario> crearUsuario(@Body Usuario usuario);
}
