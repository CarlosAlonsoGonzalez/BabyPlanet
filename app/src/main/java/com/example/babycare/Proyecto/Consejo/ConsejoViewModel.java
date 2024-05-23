package com.example.babycare.Proyecto.Consejo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsejoViewModel extends ViewModel {
    public static final double DELAY = 500;

    public MutableLiveData<ArrayList<Consejo>> consejos;

    public LiveData<ArrayList<Consejo>> getConsejos() {
        if(consejos == null){
            consejos = new MutableLiveData<ArrayList<Consejo>>();
            generoConsejos();
        }
        return consejos;
    }

    public void generoConsejos() {
        new Thread(() -> {
                ServicioApiConsejos ser = ServicioApiConsejos.getInstancia();

            //TODO debe coger la edad del hijo
            //Call<List<Consejo>> llamada = ser.getRepo().getConsejosPorEdad(1);
            Call<List<Consejo>> llamada = ser.getRepo().getConsejos();
                llamada.enqueue(new Callback<List<Consejo>>() {
                    @Override
                    public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Consejo> listaConsejos = new ArrayList<>(response.body());
                            // Imprimir los datos recibidos para verificar si son válidos
                            for (Consejo consejo : listaConsejos) {
                                Log.d("ConsejoViewModel", "Consejo: " + consejo.getNombre() + ", Descripción: " + consejo.getDescripcion());
                            }

                            // Utiliza el método generador() de Consejo para procesar los consejos
                            ArrayList<Consejo> consejosProcesados = Consejo.generador(listaConsejos);
                            consejos.postValue(consejosProcesados);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Consejo>> call, Throwable t) {
                        System.out.println("Error en la llamada: " + t.getMessage());
                    }
                });
        }).start();
    }
}

