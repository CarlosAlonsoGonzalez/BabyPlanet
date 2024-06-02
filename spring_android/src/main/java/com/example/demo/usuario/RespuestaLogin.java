package com.example.demo.usuario;

public class RespuestaLogin {

    private boolean datosCorrectos;
    private Long userId;
    public boolean isDatosCorrectos() {
        return datosCorrectos;
    }
    public void setDatosCorrectos(boolean datosCorrectos) {
        this.datosCorrectos = datosCorrectos;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public RespuestaLogin(boolean datosCorrectos, Long userId) {
        this.datosCorrectos = datosCorrectos;
        this.userId = userId;
    }

}
