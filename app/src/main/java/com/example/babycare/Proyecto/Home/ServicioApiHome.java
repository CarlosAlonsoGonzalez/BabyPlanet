package com.example.babycare.Proyecto.Home;

import com.example.babycare.Proyecto.UsuarioSingleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiHome {
    private static ServicioApiHome instancia;
    private static RepoHome repo;

    private ServicioApiHome() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioSingleton.PORTATIL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoHome.class);
    }
    public static RepoHome getRepo(){
        return repo;
    }

    public static ServicioApiHome getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiHome();
        }
        return instancia;
    }
}
