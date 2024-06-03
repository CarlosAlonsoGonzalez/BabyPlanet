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

    public LiveData<ArrayList<Consejo>> getConsejos(String tipo, int rango) {
        if(consejos == null){
            consejos = new MutableLiveData<ArrayList<Consejo>>();
            if(rango!=0 && tipo==null){
                generarConsejosPorRango(rango);
            } else if (rango==0 && tipo!=null) {
                generarConsejosPorTipo(tipo);
            } else {
                generarConsejosPorTipoYRango(tipo, rango);
            }
        }
        return consejos;
    }

    public void generarConsejosPorRango(int rango) {
        new Thread(() -> {
            ServicioApiConsejos ser = ServicioApiConsejos.getInstancia();

            Call<List<Consejo>> llamada = ser.getRepo().getConsejosPorRango(rango);

                llamada.enqueue(new Callback<List<Consejo>>() {
                    @Override
                    public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Consejo> listaConsejos = new ArrayList<>(response.body());

                            // Utiliza el método generador() de Consejo para procesar los consejos
                            ArrayList<Consejo> consejosProcesados = Consejo.generador(listaConsejos);
                            consejos.postValue(consejosProcesados);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Consejo>> call, Throwable t) {
                        System.out.println("Error en la llamada de filtro Consejo(rango: "+rango+")\n"+ t.getMessage());
                    }
                });
        }).start();
    }

    public void generarConsejosPorTipo(String tipo) {
        new Thread(() -> {
            ServicioApiConsejos ser = ServicioApiConsejos.getInstancia();

            Call<List<Consejo>> llamada = ser.getRepo().getConsejosPorTipo(tipo);

            llamada.enqueue(new Callback<List<Consejo>>() {
                @Override
                public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Consejo> listaConsejos = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Consejo> consejosProcesados = Consejo.generador(listaConsejos);
                        consejos.postValue(consejosProcesados);
                    }
                }

                @Override
                public void onFailure(Call<List<Consejo>> call, Throwable t) {
                    System.out.println("Error en la llamada de filtro Consejo(tipo: "+tipo+")\n"+ t.getMessage());
                }
            });
        }).start();
    }

    public void generarConsejosPorTipoYRango(String tipo, int rango) {
        new Thread(() -> {
            ServicioApiConsejos ser = ServicioApiConsejos.getInstancia();

            Call<List<Consejo>> llamada = ser.getRepo().getConsejosPorRangoYTipo(rango,tipo);
            //Call<List<Actividad>> llamada = ser.getRepo().getActividades();

            llamada.enqueue(new Callback<List<Consejo>>() {
                @Override
                public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Consejo> listaConsejos = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Consejo> consejosProcesados = Consejo.generador(listaConsejos);
                        consejos.postValue(consejosProcesados);
                    }
                }

                @Override
                public void onFailure(Call<List<Consejo>> call, Throwable t) {
                    System.out.println("Error en la llamada de filtro Consejo(tipo: "+tipo+", rango: "+rango+")\n"+ t.getMessage());
                }
            });
        }).start();
    }
}

