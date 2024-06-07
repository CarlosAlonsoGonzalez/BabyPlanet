package com.example.babycare.Proyecto.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.babycare.Proyecto.Perfil.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioViewModel extends ViewModel {
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

    public void generarPerfil(int id) {
        new Thread(() -> {
            ServicioApiInicio ser = ServicioApiInicio.getInstancia();
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

    //cosas login
    private MutableLiveData<RespuestaLogin> respuestaLogin = new MutableLiveData<>();

    public LiveData<RespuestaLogin> getRespuestaLogin() {
        if(respuestaLogin==null){
            respuestaLogin = new MutableLiveData<>();
        }
        return respuestaLogin;
    }

    public void crearUsuario(Usuario usuario) {
        new Thread(() -> {
            ServicioApiInicio ser = ServicioApiInicio.getInstancia();
            Call<Usuario> llamada = ser.getRepo().crearUsuario(usuario);
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
        }).start();
    }
    public void login(String email, String password) {
        new Thread(() -> {
            ServicioApiInicio ser = ServicioApiInicio.getInstancia();
            Call<RespuestaLogin> llamada = ser.getRepo().login(email, password);
            llamada.enqueue(new Callback<RespuestaLogin>() {
                @Override
                public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                    if (response.isSuccessful()) {
                        respuestaLogin.postValue(response.body());
                    } else {
                        respuestaLogin.postValue(new RespuestaLogin(false, null)); // Asume un constructor para manejar fallos
                    }
                }
                @Override
                public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                    respuestaLogin.postValue(new RespuestaLogin(false, null)); // Asume un constructor para manejar fallos
                }
            });
        }).start();
    }
    public LiveData<Boolean> getSuccessMessage() {
        return successMessage;
    }
    public void resetSuccessMessage() {
        successMessage.setValue(false);
    }
}
