package com.example.babycare.Proyecto.Perfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hijos {

    String nombreHijo;

    int edad;

    public String getNombreHijo() {
        return nombreHijo;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombreHijo(String nombreHijo) {
        this.nombreHijo = nombreHijo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nombreHijo\": " + nombreHijo + "," +
                "\"edad\": \"" + edad + "\"" +
                "}";
    }
}
