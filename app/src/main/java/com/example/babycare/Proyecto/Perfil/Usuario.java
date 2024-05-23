package com.example.babycare.Proyecto.Perfil;

import java.io.Serializable;

public class Usuario implements Serializable {
    String nombreUsuario;
    String email;
    String password;
    Hijos [] hijos;

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

    public static Usuario generadorPerfil(Usuario usuario){

        Usuario apiUsuario = new Usuario();

        if (usuario != null) {
            apiUsuario = usuario;
        }

        return apiUsuario;
    }
}
