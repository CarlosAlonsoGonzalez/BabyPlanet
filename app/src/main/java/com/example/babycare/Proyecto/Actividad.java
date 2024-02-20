package com.example.babycare.Proyecto;

import android.graphics.Color;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.babycare.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Actividad implements Serializable {
    int id;
    String nombre;
    String descripcion;
    ArrayList<Material> materiales; //no se como implementar estoo :(
    int rango;
    String areaDesarrollo;
    int icono;//este esta fuera de la base de datos se pone sgun el areaDesarrollo

    public Actividad(int id, String nombre, String descripcion, ArrayList<Material> listaMateriales,int rango, String areaDesarrollo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.materiales=listaMateriales;
        this.rango = rango;
        this.areaDesarrollo = areaDesarrollo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMaterialesToString() {
        String listadoMateriales="";
        for (Material unMaterial: materiales) {
            listadoMateriales+="- "+unMaterial.getNombre()+"\n";
        }
        return listadoMateriales;
    }

    public String getRangoString() {
        //Aqui esta como un string a pesar de ser un int porque lo hacemos con el enum ya que en la base de datos son int pero para el usurio es string
        return Rango.obtenerDescripcionPorCodigo(rango);
    }

    public String getAreaDesarrollo() {
        return areaDesarrollo;
    }

    public int getIcono() {
        switch (areaDesarrollo.toLowerCase()) {
            case "sensorial":
                icono = R.drawable.area_sensorial;
                break;
            case "motriz":
                icono = R.drawable.area_motora;
                break;
            case "cognitiva":
                icono = R.drawable.area_cognitiva;
                break;
            case "socioafectiva":
                icono = R.drawable.area_socioafectiva;
                break;
            case "del lenguaje":
                icono = R.drawable.area_del_lenguaje;
                break;
        }
        return icono;
    }


    static ArrayList<Actividad> listadoApi;
    public static ArrayList<Actividad> generador(ViewModelStoreOwner owner){//TODO: API HERE NO SEEEEE

        ActividadViewModel vm = new ViewModelProvider(owner).get(ActividadViewModel.class);
        vm.getActividades().observe((LifecycleOwner) owner, listadoActividades -> { //no se que esta pasando ya lo digo
            listadoApi= new ArrayList<>(listadoActividades);
        });

        return listadoApi;
    }

}
