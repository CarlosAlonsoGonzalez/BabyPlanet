package com.example.babycare.Proyecto.Perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Perfil> perfil;
    public int idPerfil;

    public LiveData<Perfil> getPerfil(int id) {
        idPerfil = id;
        if (perfil == null) {
            perfil = new MutableLiveData<>();
            generarPerfil(idPerfil);
        }
        return perfil;
    }

    public void generarPerfil(int id) {
        new Thread(() -> {
            ServicioApiPerfil ser = ServicioApiPerfil.getInstancia();
            Call<Perfil> llamada = ser.getRepo().getPerfilPorId(id);
            llamada.enqueue(new Callback<Perfil>() {
                @Override
                public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                    if (response.isSuccessful()) {
                        Perfil c = response.body();
                        perfil.postValue(c);
                    }
                }

                @Override
                public void onFailure(Call<Perfil> call, Throwable t) {
                    System.out.println("Error en la llamada: " + t.getMessage());
                }
            });
        }).start();
    }

    public void actualizarPerfil(int id, Map<String, Object> actualizaciones) {
        ServicioApiPerfil ser = ServicioApiPerfil.getInstancia();
        Call<Perfil> llamada = ser.getRepo().actualizarPerfil(id, actualizaciones);
        llamada.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                if (response.isSuccessful()) {
                    Perfil c = response.body();
                    // Actualiza el perfil en LiveData si la respuesta es exitosa
                    perfil.postValue(c);
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                System.out.println("Error en la llamada: " + t.getMessage());
            }
        });
    }
}

