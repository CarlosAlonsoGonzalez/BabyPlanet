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
     @Query("SELECT a FROM Actividad a WHERE a.area_desarrollo = :area_desarrollo")
     List<Actividad> findByArea(@Param("area_desarrollo") String area_desarrollo);
     @Query("SELECT a FROM Actividad a WHERE a.rango = :rango AND a.area_desarrollo = :area_desarrollo")
     List<Actividad> findByAreaYRango(@Param("rango") int rango, @Param("area_desarrollo") String area_desarrollo);
}
