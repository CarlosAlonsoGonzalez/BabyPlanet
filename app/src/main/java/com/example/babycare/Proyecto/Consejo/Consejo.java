package com.example.babycare.Proyecto.Consejo;

import android.graphics.Color;

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
        switch (tipo.toLowerCase()) {
            case "educación":
                icono = R.drawable.consejo_educacion;
                break;
            case "higiene":
                icono = R.drawable.consejo_higiene;
                break;
            case "alimentación":
                icono = R.drawable.consejo_alimentacion;
                break;
            case "emoción":
                icono = R.drawable.consejo_gestion_emociones;
                break;
            case "salud":
                icono = R.drawable.consejo_salud;
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
            case "emoción":
                return R.color.emocional;
            case "salud":
                return R.color.salud;
            default:
                return R.color.Principal;
        }
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

        return listadoApiConsejo;
    }
}
