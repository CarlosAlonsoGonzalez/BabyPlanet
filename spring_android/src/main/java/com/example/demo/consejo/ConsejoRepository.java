package com.example.demo.consejo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsejoRepository extends JpaRepository<Consejo, Integer> {
    @Query("SELECT c FROM Consejo c WHERE c.rango = :rango")
    List<Consejo> findByRango(@Param("rango") int rango);
    @Query("SELECT c FROM Consejo c WHERE c.tipo = :tipo")
    List<Consejo> findByTipo(@Param("tipo") String tipo);
}
