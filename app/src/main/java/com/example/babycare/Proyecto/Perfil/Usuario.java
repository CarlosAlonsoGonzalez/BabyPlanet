package com.example.babycare.Proyecto.Perfil;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    long id;
    String nombreUsuario;

    String email;

    String password;

    List<Hijo> hijos;

    public Usuario(long id, String nombreUsuario, String email, String password, List<Hijo> hijos) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.hijos = hijos;
    }
    public Usuario(String nombreUsuario, String email, String password, List<Hijo> hijos){

        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.hijos = hijos;
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

    public List<Hijo> getHijos() {
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
        if (hijos == null || hijos.size() == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < hijos.size(); i++) {
            sb.append(hijos.get(i).toString());
            if (i < hijos.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
