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

    public static ArrayList<Consejo> generador(){//TODO: API HERE YO TAMPOCO SEEEEE
        ArrayList<Consejo> listadoApiConsejo = new ArrayList<Consejo>();

        listadoApiConsejo.add(new Consejo(23, "hola", "como mola", "2-3", "Educacion"));

        int iconoArea = 0;
        for (Consejo consejo : listadoApiConsejo) {//TODO MEJORABLE que no recorra el array sino que lo haga segun lo crea

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
