package com.example.demo.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Usuario,Long>{

    Usuario findByNombreUsuario(String nombreUsuario);
    List<Usuario> findAll();
    
}
