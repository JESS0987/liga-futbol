package com.liga.futbol.service;

import com.liga.futbol.model.Competicion;
import com.liga.futbol.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepository;

    public List<Competicion> listarTodos() {
        return competicionRepository.findAll();
    }

    public Competicion buscarPorId(String id) {
        return competicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada: " + id));
    }

    public List<Competicion> buscarPorEquipo(String equipoId) {
        return competicionRepository.findByEquipoIdsContaining(equipoId);
    }

    public Competicion crear(Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    public Competicion agregarEquipo(String id, String equipoId) {
        Competicion competicion = buscarPorId(id);
        if (!competicion.getEquipoIds().contains(equipoId)) {
            competicion.getEquipoIds().add(equipoId);
        }
        return competicionRepository.save(competicion);
    }

    public void eliminar(String id) {
        buscarPorId(id);
        competicionRepository.deleteById(id);
    }
}