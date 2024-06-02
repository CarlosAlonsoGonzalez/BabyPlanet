package com.example.demo.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Usuario,Long>{

    Usuario findByNombreUsuario(String nombreUsuario);
    List<Usuario> findAll();

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario login(String email,String password);
    
}
