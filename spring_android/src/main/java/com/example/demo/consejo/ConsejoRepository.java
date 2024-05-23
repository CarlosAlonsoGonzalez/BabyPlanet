package com.example.demo.consejo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsejoRepository extends JpaRepository<Consejo, Integer> {
    List<Consejo> findByRango(int rango);

    /*
     * List<Consejo> findByTipo(String areaDesarrollo);
     * 
     * @Query("SELECT a FROM Actividad a WHERE a.rango =?1 AND a.tipo = ?2")
     * List<Consejo> findByRangoAndTipo(int rango, String tipo);
     */
}
