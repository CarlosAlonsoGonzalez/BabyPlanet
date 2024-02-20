package com.example.babycare.Proyecto;

import android.util.Log;

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

    public LiveData<ArrayList<Actividad>> getActividades() {
        if (actividades == null) {
            actividades = new MutableLiveData<ArrayList<Actividad>>();
            generarActividades();
        }
        return actividades;
    }

    public void generarActividades() {
        new Thread(() -> {

            ServicioApiActividades ser = ServicioApiActividades.getInstancia();
            Call<List<Actividad>> llamada = ser.getRepo().getActividades();
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
                    System.out.println("Error en la llamada: " + t.getMessage());
                }
            });

        }
        ).start();
    }
}