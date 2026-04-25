package com.liga.futbol.controller;

import com.liga.futbol.model.Jugador;
import com.liga.futbol.service.JugadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public List<Jugador> listar() {
        return jugadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(jugadorService.buscarPorId(id));
    }

    @GetMapping("/equipo/{equipoId}")
    public List<Jugador> buscarPorEquipo(@PathVariable String equipoId) {
        return jugadorService.buscarPorEquipo(equipoId);
    }

    @PostMapping
    public ResponseEntity<Jugador> crear(@Valid @RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.crear(jugador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(@PathVariable String id,
                                               @Valid @RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.actualizar(id, jugador));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Jugador> cambiarEstado(@PathVariable String id,
                                                  @RequestParam String estado) {
        return ResponseEntity.ok(jugadorService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        jugadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}