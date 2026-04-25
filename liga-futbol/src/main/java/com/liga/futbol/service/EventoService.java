package com.liga.futbol.service;

import com.liga.futbol.model.Evento;
import com.liga.futbol.repository.EventoRepository;
import com.liga.futbol.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscarPorPartido(String partidoId) {
        return eventoRepository.findByPartidoId(partidoId);
    }

    public List<Evento> buscarPorJugador(String jugadorId) {
        return eventoRepository.findByJugadorId(jugadorId);
    }

    public List<Evento> buscarGoleadores() {
        return eventoRepository.findByTipoAccion("GOL");
    }

    public Evento crear(Evento evento) {
        Evento guardado = eventoRepository.save(evento);

        // Suspender jugador si recibe tarjeta roja
        if ("TARJETA_ROJA".equals(evento.getTipoAccion())) {
            jugadorRepository.findById(evento.getJugador().getId()).ifPresent(j -> {
                j.setEstado("SUSPENDIDO");
                jugadorRepository.save(j);
            });
        }

        // Suspender jugador si acumula 2 tarjetas amarillas
        if ("TARJETA_AMARILLA".equals(evento.getTipoAccion())) {
            long amarillas = eventoRepository.countByJugadorIdAndTipoAccion(
                evento.getJugador().getId(), "TARJETA_AMARILLA");
            if (amarillas >= 2) {
                jugadorRepository.findById(evento.getJugador().getId()).ifPresent(j -> {
                    j.setEstado("SUSPENDIDO");
                    jugadorRepository.save(j);
                });
            }
        }

        return guardado;
    }

    public void eliminar(String id) {
        eventoRepository.deleteById(id);
    }
}