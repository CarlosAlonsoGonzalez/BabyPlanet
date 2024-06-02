package com.example.babycare.Proyecto.Actividad;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Consejo.Consejo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoActividad {
    @GET("/actividad/obtenerTodas")
    Call<List<Actividad>> getActividades();
    @GET("/actividad/obtenerPorRango/{rango}")
    Call<List<Actividad>> getActividadesPorRango(@Path("rango") int rango);
    @GET("/actividad/obtenerPorArea/{area_desarrollo}/")
    Call<List<Actividad>> getActividadesPorAreaDesarrollo(@Path("area_desarrollo") String areaDesarrollo);
    @GET("/actividad/obtenerPorAreaYRango/{rango}/{area_desarrollo}/")
    Call<List<Actividad>> getActividadesPorRangoYAreaDesarrollo(@Path("rango") int rango, @Path("area_desarrollo") String areaDesarrollo);


}
