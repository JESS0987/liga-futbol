package com.liga.futbol.service;

import com.liga.futbol.model.Entrenador;
import com.liga.futbol.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> listarTodos() {
        return entrenadorRepository.findAll();
    }

    public Entrenador buscarPorId(String id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado: " + id));
    }

    public Entrenador crear(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador actualizar(String id, Entrenador entrenador) {
        Entrenador existente = buscarPorId(id);
        existente.setNombre(entrenador.getNombre());
        existente.setApellido(entrenador.getApellido());
        existente.setNacionalidad(entrenador.getNacionalidad());
        existente.setEdad(entrenador.getEdad());
        return entrenadorRepository.save(existente);
    }

    public void eliminar(String id) {
        buscarPorId(id);
        entrenadorRepository.deleteById(id);
    }
}