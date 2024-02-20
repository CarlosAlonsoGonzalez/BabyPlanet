package com.example.babycare.Proyecto;

import com.example.babycare.R;

import java.util.ArrayList;

public class Consejo {

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Consejo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public int getIcono() {
        return icono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static ArrayList<Consejo> generador(ArrayList<Consejo> listaConsejos){
        ArrayList<Consejo> listadoApiConsejo = new ArrayList<Consejo>();

        // Si se proporciona una lista de consejos desde el ViewModel, la utilizamos
        if (listaConsejos != null && !listaConsejos.isEmpty()) {
            listadoApiConsejo.addAll(listaConsejos);
        }

        int iconoArea = 0;
        for (Consejo consejo : listadoApiConsejo) {
            // Asignar un icono según el rango del consejo
            if (consejo.getRango().equals("sensorial")) {
                iconoArea = R.drawable.sonajero;
            } else if (consejo.getRango().equals("motriz")) {
                iconoArea = R.drawable.sonajero;
            } else if (consejo.getRango().equals("cognitiva")) {
                iconoArea = R.drawable.sonajero;
            } else if (consejo.getRango().equals("sociafectiva")) {
                iconoArea = R.drawable.sonajero;
            } else if (consejo.getRango().equals("de lenguaje")) {
                iconoArea = R.drawable.sonajero;
            }
            consejo.setIcono(iconoArea);
        }

        return listadoApiConsejo;
    }
}
