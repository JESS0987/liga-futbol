package com.liga.futbol.controller;

import com.liga.futbol.model.Evento;
import com.liga.futbol.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> listar() {
        return eventoService.listarTodos();
    }

    @GetMapping("/partido/{partidoId}")
    public List<Evento> buscarPorPartido(@PathVariable String partidoId) {
        return eventoService.buscarPorPartido(partidoId);
    }

    @GetMapping("/jugador/{jugadorId}")
    public List<Evento> buscarPorJugador(@PathVariable String jugadorId) {
        return eventoService.buscarPorJugador(jugadorId);
    }

    @GetMapping("/goleadores")
    public List<Evento> goleadores() {
        return eventoService.buscarGoleadores();
    }

    @PostMapping
    public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.crear(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}