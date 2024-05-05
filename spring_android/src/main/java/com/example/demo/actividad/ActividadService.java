package com.example.demo.actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public void crearActividad(Actividad actividad) {
        actividadRepository.save(actividad);
    }

    public boolean borrarActividad(Integer id) {
        if (actividadRepository.findById(id).isPresent()) {
            actividadRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Actividad> obtenerActividad(Integer id) {
        return actividadRepository.findById(id);
    }

    public List<Actividad> obtenerTodas() {
        return actividadRepository.findAll();
    }

    public Optional<Actividad> actualizarActividad(Integer id, Actividad actividad) {
        Optional<Actividad> a = actividadRepository.findById(id);
        if (a.isPresent()) {
            a.get().setNombre(actividad.getNombre());
            a.get().setDescripcion(actividad.getDescripcion());
            a.get().setRango(actividad.getRango());
            a.get().setArea_desarrollo(actividad.getArea_desarrollo());
            a.get().setMateriales(actividad.getMateriales());
            actividadRepository.save(a.get());
        }
        return a;
    }
}
