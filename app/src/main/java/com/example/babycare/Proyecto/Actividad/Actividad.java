package com.example.babycare.Proyecto.Actividad;

import com.example.babycare.Proyecto.Rango;
import com.example.babycare.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Actividad implements Serializable {
    int id;
    String nombre;
    String descripcion;
    String materiales;
    int rango;
    String area_desarrollo;
    int icono;//este esta fuera de la base de datos se pone sgun el areaDesarrollo

    public Actividad(int id, String nombre, String descripcion,int rango, String area_desarrollo, String listaMateriales) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.materiales=listaMateriales;
        this.rango = rango;
        this.area_desarrollo = area_desarrollo;
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
        String [] listadoMateriales = this.materiales.split("[,]");

        return "- "+String.join("\n- ", listadoMateriales);
    }

    public String getRangoString() {
        //Aqui esta como un string a pesar de ser un int porque lo hacemos con el enum ya que en la base de datos son int pero para el usurio es string
        return Rango.obtenerDescripcionPorCodigo(rango);
    }

    public String getArea_desarrollo() {
        return area_desarrollo;
    }

    public int getIcono() {
        switch (area_desarrollo.toLowerCase()) {
            case "sensorial":
                icono = R.drawable.area_sensorial;
                break;
            case "motora":
                icono = R.drawable.area_motora;
                break;
            case "cognitiva":
                icono = R.drawable.area_cognitiva;
                break;
            case "socio-afectiva":
                icono = R.drawable.area_socioafectiva;
                break;
            case "lenguaje":
                icono = R.drawable.area_del_lenguaje;
                break;
        }
        return icono;
    }


    public static ArrayList<Actividad> generador(ArrayList<Actividad> listadoActividades){

        ArrayList<Actividad> listadoApiActividades= new ArrayList<Actividad>();

        // Si se proporciona una lista de consejos desde el ViewModel, la utilizamos
        if (listadoActividades != null && !listadoActividades.isEmpty()) {
            listadoApiActividades.addAll(listadoActividades);
        }

        return listadoApiActividades;
    }

}
