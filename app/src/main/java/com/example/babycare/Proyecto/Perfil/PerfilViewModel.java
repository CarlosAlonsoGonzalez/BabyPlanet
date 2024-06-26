package com.example.babycare.Proyecto.Perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Usuario> perfil;
    private MutableLiveData<Boolean> successMessage = new MutableLiveData<>();
    public int idPerfil;

    public LiveData<Usuario> getPerfil(int id) {
        idPerfil = id;
        if (perfil == null) {
            perfil = new MutableLiveData<>();
            generarPerfil(idPerfil);
        }
        return perfil;
    }

    public LiveData<Boolean> getSuccessMessage() {
        return successMessage;
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
    public void modificarPerfil(Usuario usuario) {
        //new Thread(() -> {
            ServicioApiPerfil ser = ServicioApiPerfil.getInstancia();
            Call<Usuario> llamada = ser.getRepo().actualizarUsuario(usuario);
            llamada.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        successMessage.postValue(true);
                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                }
            });
        //}).start();
    }

    //este codigo lo hago porque si no el alert se acumula y te saltan 40 ventanas en vez de 1
    public void resetSuccessMessage() {
        successMessage.setValue(false);
    }
}

