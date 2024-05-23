package com.example.demo.consejo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsejoService {

   @Autowired
   private ConsejoRepository consejoRepository;

   public void crearConsejo(Consejo consejo) {
      consejoRepository.save(consejo);
   }

   public boolean borrarConsejo(Integer id) {
      if (consejoRepository.findById(id).isPresent()) {
         consejoRepository.deleteById(id);
         return true;
      } else {
         return false;
      }
   }

   public Optional<Consejo> obtenerConsejo(Integer id) {
      return consejoRepository.findById(id);
   }

   public List<Consejo> obtenerTodos() {
      return consejoRepository.findAll();
   }

   public List<Consejo> obtenerConsejosPorRango(int rango) {
      return consejoRepository.findByRango(rango);
   }

   /*
    * public List<Consejo> obtenerConsejosPorTipo(String tipo) {
    * return consejoRepository.findByTipo(tipo);
    * }
    * 
    * public List<Consejo> obtenerConsejosPorRangoYTipo(int rango, String tipo) {
    * return consejoRepository.findByRangoAndTipo(rango, tipo);
    * }
    */
}
