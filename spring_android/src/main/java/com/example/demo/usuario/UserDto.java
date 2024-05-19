package com.example.demo.usuario;

import java.util.List;

import com.example.demo.hijo.HijoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

public class UserDto {

    @EqualsAndHashCode.Include
    private Long id ;
    
    @NotBlank
    @Size(max=25)
    private String nombreUsuario;

    @NotBlank
    @Size(max=25)
    private String email;
    @NotBlank
    @Size(max=25)
    private String password;
    
    private List<HijoDto> hijos;

    public UserDto (String nombreUsuario, String password){

        this.nombreUsuario = nombreUsuario;
        this.password = password;

    }
}
