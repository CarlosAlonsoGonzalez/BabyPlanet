package com.example.demo.usuario;
import java.util.List;

import com.example.demo.hijo.Hijo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode( onlyExplicitlyIncluded = true)

@NoArgsConstructor
@Entity
@Table(name ="usuario")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(length= 60, nullable = false,unique = true) 
    private String nombreUsuario;

    @Column(length= 60, nullable = false,unique = true) 
    private String email;

    @Column(length= 60, nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "padre")    
    @ToString.Exclude
    private List<Hijo> hijos;
    

    
}
