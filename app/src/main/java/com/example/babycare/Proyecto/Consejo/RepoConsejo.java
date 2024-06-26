package com.example.babycare.Proyecto.Consejo;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoConsejo {
    @GET("/consejo/obtenerTodos")
    Call<List<Consejo>> getConsejos();
    @GET("/consejo/obtenerPorRango/{rango}")
    Call<List<Consejo>> getConsejosPorRango(@Path("rango") int rango);
    @GET("/consejo/obtenerPorTipo/{tipo}")
    Call<List<Consejo>> getConsejosPorTipo(@Path("tipo") String tipo);
    @GET("/consejo/obtenerPorTipoYRango/{rango}/{tipo}")
    Call<List<Consejo>> getConsejosPorRangoYTipo(@Path("rango") int rango, @Path("tipo") String tipo);
}
