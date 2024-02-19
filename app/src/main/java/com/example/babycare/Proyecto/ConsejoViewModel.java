package com.example.babycare.Proyecto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ConsejoViewModel extends ViewModel {
    public static final double DELAY = 500;

    private MutableLiveData<ArrayList<Consejo>> consejos;

    public LiveData<ArrayList<Consejo>> getConsejos() {
        if(consejos == null){
            consejos = new MutableLiveData<ArrayList<Consejo>>();
        }
        return consejos;
    }

    public void generoConsejos() {
        new Thread(() -> {
            try{
                Thread.sleep((long)((Math.random() * DELAY) + DELAY));
                ArrayList<Consejo> listaConsejos = Consejo.generador();
                consejos.postValue(listaConsejos);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        ).start();
    }
}

