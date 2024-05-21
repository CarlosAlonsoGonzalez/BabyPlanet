package com.example.babycare.Proyecto.Perfil;

import java.io.Serializable;

public class Perfil implements Serializable {
    public String nombreUsuario;
    public String email;
    public String password;
    public Hijos [] hijos;

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

    public static Perfil generadorPerfil(Perfil perfil){

        Perfil apiPerfil = new Perfil();

        if (perfil!= null) {
            apiPerfil = perfil;
        }

        return apiPerfil;
    }
}
