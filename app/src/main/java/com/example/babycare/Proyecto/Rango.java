package com.example.babycare.Proyecto;

public enum Rango {
    RANGO_0_6(1, "0-6 meses"),
    RANGO_6_12(2, "6-12 meses"),
    RANGO_12_18(3, "12-18 meses"),
    RANGO_18_24(4, "18-24 meses"),
    RANGO_24_30(5, "24-30 meses"),
    RANGO_30_36(6, "30-36 meses");

    private final int codigo;
    private final String descripcion;

    Rango(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public static String obtenerDescripcionPorCodigo(int codigo) {
        for (Rango rango : Rango.values()) {
            if (rango.codigo == codigo) {
                return rango.descripcion;
            }
        }
        return "Rango inválido";
    }

    public static int obtenerDecripcionPorCodigo(String descripcion) {
        for (Rango rango : Rango.values()) {
            if (rango.descripcion.equals(descripcion)) {
                return rango.codigo;
            }
        }
        return -1; // Código inválido
    }
}
