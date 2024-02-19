package com.example.babycare.Proyecto;

import com.example.babycare.R;

import java.util.ArrayList;

public class Consejos {

    int id;
    String nombre, descripcion;
    String rango;
    int icono;

    public Consejos(int id, String nombre, String descripcion, String rango) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rango = rango;
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

    public Consejos(String descripcion) {
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

    public static ArrayList<Consejos> generador(){//TODO: API HERE YO TAMPOCO SEEEEE
        ArrayList<Consejos> listadoApiConsejo = new ArrayList<Consejos>();

        listadoApiConsejo.add(new Consejos(23, "hola", "como mola", "2-3"));

        int iconoArea = 0;
        for (Consejos consejo : listadoApiConsejo) {//TODO MEJORABLE que no recorra el array sino que lo haga segun lo crea

            if (consejo.getRango().equals("sensorial") ) {
                iconoArea = R.drawable.sonajero;//TODO cambiarlo para que tenga sentido con los consejos
            }else  if (consejo.getRango().equals("motriz") ) {
                iconoArea = R.drawable.sonajero;//TODO cambiarlo para que tenga sentido con los consejos
            }else  if (consejo.getRango().equals("cognitiva") ) {
                iconoArea = R.drawable.sonajero;//TODO cambiarlo para que tenga sentido con los consejos
            }else  if (consejo.getRango().equals("sociafectiva") ) {
                iconoArea = R.drawable.sonajero;//TODO cambiarlo para que tenga sentido con los consejos
            } if (consejo.getRango().equals("de lenguaje") ) {
                iconoArea = R.drawable.sonajero;//TODO cambiarlo para que tenga sentido con los consejos
            }

            consejo.setIcono(iconoArea);
        }

        return listadoApiConsejo;
    }
}
