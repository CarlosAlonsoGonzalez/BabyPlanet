package com.example.babycare.Proyecto;

import com.example.babycare.R;

import java.util.ArrayList;

public class Actividad {
    int id;
    String nombre;
    String descripcion;
    int rango;
    String areaDesarrollo;
    int icono;//este esta fuera de la base de datos se pone sgun el areaDesarrollo

    public Actividad(int id, String nombre, String descripcion, int rango, String areaDesarrollo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public int getRango() {
        return rango;
    }

    public String getAreaDesarrollo() {
        return areaDesarrollo;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }


    /*public static ArrayList<Actividad> generador(){//TODO: API HERE NO SEEEEE
        ArrayList<Actividad> listadoApi = new ArrayList<Actividad>();

        listadoApi.add(new Actividad(4,"Comunicación positiva", "Habla con tu bebé regularmente, aunque no entienda todas las palabras. Usa un tono de voz suave y afectuoso. La comunicación positiva contribuye al desarrollo del lenguaje", 0,"sensorial"));

        //Ponemos el icono segun el area
        int iconoArea = 0;
        for (Actividad actividad : listadoApi) {//TODO MEJORABLE que no recorra el array sino que lo haga segun lo crea

            if (actividad.getAreaDesarrollo().equals("sensorial") ) {
                iconoArea = R.drawable.sonajero;//TODO aqui se pondria el icono correspondiente
            }else  if (actividad.getAreaDesarrollo().equals("motriz") ) {
                iconoArea = R.drawable.sonajero;//TODO aqui se pondria el icono correspondiente
            }else  if (actividad.getAreaDesarrollo().equals("cognitiva") ) {
                iconoArea = R.drawable.sonajero;//TODO aqui se pondria el icono correspondiente
            }else  if (actividad.getAreaDesarrollo().equals("sociafectiva") ) {
                iconoArea = R.drawable.sonajero;//TODO aqui se pondria el icono correspondiente
            } if (actividad.getAreaDesarrollo().equals("de lenguaje") ) {
                iconoArea = R.drawable.sonajero;//TODO aqui se pondria el icono correspondiente
            }

            actividad.setIcono(iconoArea);
        }
        return listadoApi;
    }*/

}
