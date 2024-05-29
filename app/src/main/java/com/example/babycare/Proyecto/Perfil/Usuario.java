package com.example.babycare.Proyecto.Perfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    String nombreUsuario;

    String email;

    String password;

    Hijos [] hijos;

    public Usuario(String nombreUsuario, String email, String password) {
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

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHijos(Hijos[] hijos) {
        this.hijos = hijos;
    }

    public static Usuario generadorPerfil(Usuario usuario){

        Usuario apiUsuario = new Usuario(usuario.nombreUsuario, usuario.email, usuario.password);

        if (usuario != null) {
            apiUsuario = usuario;
        }

        return apiUsuario;
    }

    @Override
    public String toString() {
        return "{" +
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
