package com.example.babycare.Proyecto.Inicio;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiInicio {
    private static ServicioApiInicio instancia;
    private static RepoInicio repo;

    private ServicioApiInicio() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoInicio.class);
    }
    public static RepoInicio getRepo(){
        return repo;
    }

    public static ServicioApiInicio getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiInicio();
        }
        return instancia;
    }
}
