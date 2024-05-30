package com.example.demo.consejo;

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
@RequestMapping("/consejo")

@RequiredArgsConstructor
public class ConsejoController {
    @Autowired
    private final ConsejoService consejoService;

    @PostMapping("/crear")
    public ResponseEntity<String> insertarConsejo(@RequestBody Consejo consejo) {
        consejoService.crearConsejo(consejo);
        return ResponseEntity.ok("Se ha creado el consejo correctamente");
    }

    @PutMapping("borrarConsejo/{id}")
    public ResponseEntity<String> borrarConsejo(@PathVariable String id) {
        if (consejoService.borrarConsejo(Integer.parseInt(id)) == true) {
            return ResponseEntity.ok("Se ha borrado el consejo correctamente");
        } else {
            return ResponseEntity.status(201).body("No se ha podido borrar el consejo");
        }
    }

    @GetMapping("obtenerConsejo/{id}")
    public ResponseEntity<Optional<Consejo>> obtenerConsejo(@PathVariable String id) {
        return ResponseEntity.ok(consejoService.obtenerConsejo(Integer.parseInt(id)));
    }

    @GetMapping("obtenerTodos")
    public ResponseEntity<List<Consejo>> obtenerTodos() {
        return ResponseEntity.ok(consejoService.obtenerTodos());
    }

    @GetMapping("obtenerPorRango/{rango}")
    public List<Consejo> obtenerConsejosPorRango(@PathVariable int rango) {
        return consejoService.obtenerConsejosPorRango(rango);
    }
    @GetMapping("obtenerPorTipo/{tipo}")
    public List<Consejo> obtenerConsejosPorTipo(@PathVariable String tipo) {
        return consejoService.obtenerConsejosPorTipo(tipo);
    }

}
