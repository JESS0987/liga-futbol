package com.liga.futbol.service;

import com.liga.futbol.model.Partido;
import com.liga.futbol.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    public List<Partido> listarTodos() {
        return partidoRepository.findAll();
    }

    public Partido buscarPorId(String id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado: " + id));
    }

    public Partido crear(Partido partido) {
        return partidoRepository.save(partido);
    }

    public Partido registrarResultado(String id, Integer golesLocal, Integer golesVisitante) {
        Partido partido = buscarPorId(id);
        partido.setGolesLocal(golesLocal);
        partido.setGolesVisitante(golesVisitante);
        partido.setEstado("FINALIZADO");
        return partidoRepository.save(partido);
    }

    public Partido cambiarEstado(String id, String estado) {
        Partido partido = buscarPorId(id);
        partido.setEstado(estado);
        return partidoRepository.save(partido);
    }

    public void eliminar(String id) {
        buscarPorId(id);
        partidoRepository.deleteById(id);
    }
}