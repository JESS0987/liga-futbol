package com.liga.futbol.service;

import com.liga.futbol.model.Jugador;
import com.liga.futbol.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }

    public Jugador buscarPorId(String id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado: " + id));
    }

    public List<Jugador> buscarPorEquipo(String equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }

    public Jugador crear(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public Jugador actualizar(String id, Jugador jugador) {
        Jugador existente = buscarPorId(id);
        existente.setNombre(jugador.getNombre());
        existente.setApellido(jugador.getApellido());
        existente.setDocumento(jugador.getDocumento());
        existente.setFechaNacimiento(jugador.getFechaNacimiento());
        existente.setDorsal(jugador.getDorsal());
        existente.setEquipo(jugador.getEquipo());
        return jugadorRepository.save(existente);
    }

    public Jugador cambiarEstado(String id, String estado) {
        Jugador jugador = buscarPorId(id);
        jugador.setEstado(estado);
        return jugadorRepository.save(jugador);
    }

    public void eliminar(String id) {
        buscarPorId(id);
        jugadorRepository.deleteById(id);
    }
}