package com.example.demo.hijo;

import com.example.demo.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name ="hijos")
public class Hijo {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(length= 60, nullable = false,unique = false) 
    private String nombreHijo;

    @Column(length= 60, nullable = false,unique = false)
    private int edad;
    
    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonBackReference
    @JsonIgnore
    private Usuario padre;



}
