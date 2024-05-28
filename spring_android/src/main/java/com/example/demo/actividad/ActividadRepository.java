package com.example.demo.actividad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
     @Query("SELECT a FROM Actividad a WHERE a.rango = :rango")
    List<Actividad> findByRango(@Param("rango") int rango);

    /*
     * List<Actividad> findByAreaDesarrollo(String area_desarrollo);
     * 
     * @Query("SELECT a FROM Actividad a WHERE a.rango =?1 AND a.area_desarrollo = ?2"
     * )
     * List<Actividad> findByRangoAndArea_desarrollo(int rango, String
     * area_desarrollo);
     */
}
