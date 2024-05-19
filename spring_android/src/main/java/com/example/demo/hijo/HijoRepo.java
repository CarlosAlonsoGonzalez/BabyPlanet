package com.example.demo.hijo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HijoRepo extends JpaRepository<Hijo,Long>{
       
        @Query("select h from Hijo h where h.id in :ids")
        List<Hijo> findManyById(@Param("ids")List<Long>ids);    
}
