package com.example.babycare.Proyecto.Consejo;

import com.example.babycare.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Consejo implements Serializable {
    int id;
    String nombre, descripcion;
    String rango;
    String tipo;
    int icono;

    public Consejo(int id, String nombre, String descripcion, String rango, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rango = rango;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Consejo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRango() {
        return rango;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIcono() {
        switch (tipo.toLowerCase()) {
            case "educación":
                icono = R.drawable.consejo_educacion;
                break;
            case "higiene":
                icono = R.drawable.consejo_higiene2;
                break;
            case "alimentación":
                icono = R.drawable.consejo_alimentacion2;
                break;
            case "emocional":
                icono = R.drawable.consejo_gestion_emociones2;
                break;
            case "salud":
                icono = R.drawable.consejo_salud2;
                break;
        }
        return icono;
    }

    public int getColorFondo() {
        switch (this.tipo.toLowerCase()) {
            case "educación":
                return R.color.educacion;
            case "higiene":
                return R.color.higiene;
            case "alimentación":
                return R.color.alimentacion;
            case "emocional":
                return R.color.emocional;
            case "salud":
                return R.color.salud;
            default:
                return R.color.Principal;
        }
    }

    public static ArrayList<Consejo> generador(ArrayList<Consejo> listaConsejos){
        ArrayList<Consejo> listadoApiConsejo = new ArrayList<>();

        // Si se proporciona una lista de consejos desde el ViewModel, la utilizamos
        if (listaConsejos != null && !listaConsejos.isEmpty()) {
            listadoApiConsejo.addAll(listaConsejos);
        }

        return listadoApiConsejo;
    }
}
