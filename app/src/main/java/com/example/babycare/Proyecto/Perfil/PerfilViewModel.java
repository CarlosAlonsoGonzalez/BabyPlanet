package com.example.babycare.Proyecto.Perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Usuario> perfil;
    public int idPerfil;

    public LiveData<Usuario> getPerfil(int id) {
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
            Call<Usuario> llamada = ser.getRepo().getUsuarioPorId(id);
            llamada.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Usuario c = response.body();
                        perfil.postValue(c);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    System.out.println("Error en la llamada: " + t.getMessage());
                }
            });
        }).start();
    }

    public void actualizarPerfil(Map<String, Object> actualizaciones) {
        ServicioApiPerfil ser = ServicioApiPerfil.getInstancia();
        Call<Usuario> llamada = ser.getRepo().actualizarUsuario(actualizaciones);
        llamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario c = response.body();
                    // Actualiza el perfil en LiveData si la respuesta es exitosa
                    perfil.postValue(c);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println("Error en la llamada: " + t.getMessage());
            }
        });
    }
}

