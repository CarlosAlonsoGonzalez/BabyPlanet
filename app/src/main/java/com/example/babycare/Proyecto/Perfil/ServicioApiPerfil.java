package com.example.babycare.Proyecto.Perfil;

import com.example.babycare.Proyecto.UsuarioSingleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiPerfil {
    private static ServicioApiPerfil instancia;
    private static RepoPerfil repo;

    private ServicioApiPerfil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioSingleton.PORTATIL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoPerfil.class);
    }
    public static RepoPerfil getRepo(){
        return repo;
    }

    public static ServicioApiPerfil getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiPerfil();
        }
        return instancia;
    }
}
