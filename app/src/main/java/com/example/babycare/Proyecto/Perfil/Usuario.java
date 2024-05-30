package com.example.babycare.Proyecto.Perfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    long id;
    String nombreUsuario;

    String email;

    String password;

    Hijos [] hijos;

    public Usuario(long id, String nombreUsuario, String email, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Hijos[] getHijos() {
        return hijos;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"nombreUsuario\": \"" + nombreUsuario + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"password\": \"" + password + "\"," +
                "\"hijos\": " + hijosToString() +
                "}";
    }

    private String hijosToString() {
        if (hijos == null || hijos.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < hijos.length; i++) {
            sb.append(hijos[i].toString());
            if (i < hijos.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
