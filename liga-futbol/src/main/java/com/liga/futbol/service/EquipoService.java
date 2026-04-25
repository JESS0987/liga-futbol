package com.liga.futbol.service;

import com.liga.futbol.model.Equipo;
import com.liga.futbol.model.Entrenador;
import com.liga.futbol.repository.EquipoRepository;
import com.liga.futbol.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Equipo> listarTodos() {
        return equipoRepository.findAll();
    }

    public Equipo buscarPorId(String id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado: " + id));
    }

    public Equipo crear(Equipo equipo) {
        if (equipoRepository.existsByNombre(equipo.getNombre())) {
            throw new RuntimeException("Ya existe un equipo con ese nombre");
        }
        return equipoRepository.save(equipo);
    }

    public Equipo actualizar(String id, Equipo equipo) {
        Equipo existente = buscarPorId(id);
        existente.setNombre(equipo.getNombre());
        existente.setLogoUrl(equipo.getLogoUrl());
        existente.setDelegado(equipo.getDelegado());
        existente.setColores(equipo.getColores());
        return equipoRepository.save(existente);
    }

    // @OneToOne — asignar entrenador a equipo
    public Equipo asignarEntrenador(String equipoId, String entrenadorId) {
        Equipo equipo = buscarPorId(equipoId);
        Entrenador entrenador = entrenadorRepository.findById(entrenadorId)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado: " + entrenadorId));
        equipo.setEntrenador(entrenador);
        return equipoRepository.save(equipo);
    }

    public void eliminar(String id) {
        buscarPorId(id);
        equipoRepository.deleteById(id);
    }
}