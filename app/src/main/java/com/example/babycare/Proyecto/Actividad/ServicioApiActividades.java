package com.example.babycare.Proyecto.Actividad;

import com.example.babycare.Proyecto.UsuarioSingleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiActividades {
    private static ServicioApiActividades instancia;
    private static RepoActividad repo;

    private ServicioApiActividades() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioSingleton.PORTATIL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoActividad.class);
    }
    public static RepoActividad getRepo(){
        return repo;
    }

    public static ServicioApiActividades getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiActividades();
        }
        return instancia;
    }
}
