package com.example.demo.actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/actividad")

@RequiredArgsConstructor
public class ActividadController {

    @Autowired
    private final ActividadService actividadService;

    @PostMapping("/crear")
    public ResponseEntity<String> insertarActividad(@RequestBody Actividad actividad){
        actividadService.crearActividad(actividad);
        return ResponseEntity.ok("Se ha creado la actividad correctamente");
    }

    @PutMapping("borrarActividad/{id}")
    public ResponseEntity<String> borrarActividad(@PathVariable String id){
        if(actividadService.borrarActividad(Integer.parseInt(id)) == true){
            return ResponseEntity.ok("Se ha borrado la actividad correctamente");
        }else{
            return ResponseEntity.status(201).body("No se ha podido borrar porque no existe");// el .ok es 200, pero vamos a darle otro codigo de error hecho por nosotros
        }
    }

    @GetMapping("obtenerActividad/{id}")
    public ResponseEntity<Optional<Actividad>> obtenerActividad(@PathVariable String id){
        return ResponseEntity.ok(actividadService.obtenerActividad(Integer.parseInt(id)));
    }

    @GetMapping("obtenerTodas")
    public ResponseEntity<List<Actividad>> obtenerTodas(){
        return ResponseEntity.ok(actividadService.obtenerTodas());
    }

    @PostMapping("actualizarActividad/{id}")
    public ResponseEntity<Optional<Actividad>> actualizarActividad(@PathVariable String id, @RequestBody Actividad actividad){
        return ResponseEntity.ok(actividadService.actualizarActividad(Integer.parseInt(id), actividad));
    }
    
}
