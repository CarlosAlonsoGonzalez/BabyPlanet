package com.example.demo.hijo;

import com.example.demo.usuario.Usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class HijoDto {

    private Long id ;   
    private String nombreHijo;
    private int edad;    
    //private Usuario padre;

    public HijoDto(String nombreHijo, int edad, Usuario padre) {
        this.nombreHijo = nombreHijo;
        this.edad = edad;
       // this.padre = padre;
    }

}
