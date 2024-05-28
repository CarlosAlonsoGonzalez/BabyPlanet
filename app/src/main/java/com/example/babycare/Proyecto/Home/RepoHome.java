package com.example.babycare.Proyecto.Home;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Consejo.Consejo;
import com.example.babycare.Proyecto.Perfil.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoHome {
    //Unused
    @GET("/actividad/obtenerActividad/{id}")
    Call<Actividad> getActividad(@Path("id") int id);
    @GET("/consejo/obtenerConsejo/{id}")
    Call<Consejo> getConsejo(@Path("id") int id);

    //Coger todos
    @GET("/actividad/obtenerTodas")
    Call<List<Actividad>> getActividades();
    @GET("/consejo/obtenerTodos")
    Call<List<Consejo>> getConsejos();


    //Datos usuario (nombre, hijos, edad)
    @GET("/usuario/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int id);

    //Lista actividades filtrada
    @GET("/actividad/obtenerPorRango/{rango}")
    Call<List<Actividad>> getActividadesPorEdad(@Path("rango") int rangoHijo);
    @GET("/consejo/obtenerPorRango/{rango}")
    Call<List<Consejo>> getConsejosPorEdad(@Path("rango") int rangoHijo);



}
