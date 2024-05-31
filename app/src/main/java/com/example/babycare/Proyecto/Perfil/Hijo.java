package com.example.babycare.Proyecto.Perfil;

public class Hijo {
    int id;
    String nombreHijo;

    int edad;
    int usuarioId;

    public Hijo(int id, String nombreHijo, int edad, int usuarioId) {
        this.id = id;
        this.nombreHijo = nombreHijo;
        this.edad = edad;
        this.usuarioId = usuarioId;
    }

    public String getNombreHijo() {
        return nombreHijo;
    }

    public int getEdad() {
        return edad;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"nombreHijo\": " + nombreHijo + "," +
                "\"edad\": \"" + edad + "\"" +
                "\"usuarioId\": \"" + usuarioId + "\"" +
                "}";
    }
}
