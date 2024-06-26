package com.example.babycare.Proyecto.Consejo;

import com.example.babycare.Proyecto.UsuarioSingleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiConsejos {
    private static ServicioApiConsejos instancia;
    private static RepoConsejo repo;

    private ServicioApiConsejos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioSingleton.PORTATIL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoConsejo.class);
    }
    public static RepoConsejo getRepo(){
        return repo;
    }

    public static ServicioApiConsejos getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiConsejos();
        }
        return instancia;
    }
}
