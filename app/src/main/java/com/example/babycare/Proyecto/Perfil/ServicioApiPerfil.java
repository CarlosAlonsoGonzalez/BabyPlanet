package com.example.babycare.Proyecto.Perfil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiPerfil {
    private static ServicioApiPerfil instancia;
    private static RepoPerfil repo;

    private ServicioApiPerfil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
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
