package com.example.babycare.Proyecto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadViewModel extends ViewModel {
    public static final double DELAY = 50;

    private MutableLiveData<ArrayList<Actividad>> actividades;

    public LiveData<ArrayList<Actividad>> getActividades() {
        if (actividades == null) {
            actividades = new MutableLiveData<ArrayList<Actividad>>();
        }
        return actividades;
    }

    public void generarActividades() {
        new Thread(() -> {
            try {
                Thread.sleep((long) ((Math.random() * DELAY) + DELAY));

                ServicioApiActividades ser = ServicioApiActividades.getInstancia();
                Call<List<Actividad>> llamada = ser.getRepo().getActividades();
                llamada.enqueue(new Callback<List<Actividad>>() {
                    @Override
                    public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                        List<Actividad> listaActividades = response.body();
                        actividades.postValue(new ArrayList<Actividad>(listaActividades));
                    }

                    @Override
                    public void onFailure(Call<List<Actividad>> call, Throwable t) {
                        String nuncaToast = "Debug";
                    }
                });

                //llamar al array de la API ArrayList<Actividad> listaActividades = ; haces call here
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}