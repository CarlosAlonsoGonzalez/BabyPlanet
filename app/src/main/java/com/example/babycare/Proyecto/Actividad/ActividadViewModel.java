package com.example.babycare.Proyecto.Actividad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Actividad>> actividades;
    //String areaDesarrollo;
    //int rango;

    public LiveData<ArrayList<Actividad>> getActividades(String areaDesarrollo, int rango) {

        if (actividades == null) {
            actividades = new MutableLiveData<ArrayList<Actividad>>();

            if(rango!=0 && areaDesarrollo==null){
                generarActividadesPorRango(rango);
            } else if (rango==0 && areaDesarrollo!=null) {
                generarActividadesPorAreaDesarrollo(areaDesarrollo);
            } else {
                generarActividadesPorAreaDesarrlloYRango(areaDesarrollo,rango);
            }
        }
        return actividades;
    }

    public void generarActividadesPorRango(int rango) {
        new Thread(() -> {

            ServicioApiActividades ser = ServicioApiActividades.getInstancia();

            Call<List<Actividad>> llamada = ser.getRepo().getActividadesPorRango(rango);
            llamada.enqueue(new Callback<List<Actividad>>() {
                @Override
                public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Actividad> listaActividades = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Actividad> actividadesProcesadas = Actividad.generador(listaActividades);
                        actividades.postValue(actividadesProcesadas);
                    }
                }

                @Override
                public void onFailure(Call<List<Actividad>> call, Throwable t) {
                    System.out.println("Error en la llamada de filtro Actividad(rango: "+rango+")\n"+ t.getMessage());
                }
            });

        }
        ).start();
    }

    public void generarActividadesPorAreaDesarrollo(String areaDesarrollo) {
        new Thread(() -> {

            ServicioApiActividades ser = ServicioApiActividades.getInstancia();

            Call<List<Actividad>> llamada = ser.getRepo().getActividadesPorAreaDesarrollo(areaDesarrollo);
            llamada.enqueue(new Callback<List<Actividad>>() {
                @Override
                public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Actividad> listaActividades = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Actividad> actividadesProcesadas = Actividad.generador(listaActividades);
                        actividades.postValue(actividadesProcesadas);
                    }
                }

                @Override
                public void onFailure(Call<List<Actividad>> call, Throwable t) {
                    System.out.println("Error en la llamada de filtro Actividad(areaDesarrollo:"+areaDesarrollo+")\n"+ t.getMessage());;
                }
            });

        }
        ).start();
    }

    public void generarActividadesPorAreaDesarrlloYRango(String areaDesarrollo, int rango) {
        new Thread(() -> {

            ServicioApiActividades ser = ServicioApiActividades.getInstancia();

            Call<List<Actividad>> llamada = ser.getRepo().getActividadesPorRangoYAreaDesarrollo(rango,areaDesarrollo);
            llamada.enqueue(new Callback<List<Actividad>>() {
                @Override
                public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Actividad> listaActividades = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Actividad> actividadesProcesadas = Actividad.generador(listaActividades);
                        actividades.postValue(actividadesProcesadas);
                    }
                }

                @Override
                public void onFailure(Call<List<Actividad>> call, Throwable t) {
                    System.out.println("Error en la llamada de filtro Actividad(rango: "+rango+", areaDesarrollo:"+areaDesarrollo+")\n"+ t.getMessage());
                }
            });

        }
        ).start();
    }
}