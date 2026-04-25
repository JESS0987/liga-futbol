package com.liga.futbol.controller;

import com.liga.futbol.model.Partido;
import com.liga.futbol.service.PartidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "*")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public List<Partido> listar() {
        return partidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(partidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Partido> crear(@Valid @RequestBody Partido partido) {
        return ResponseEntity.ok(partidoService.crear(partido));
    }

    @PatchMapping("/{id}/resultado")
    public ResponseEntity<Partido> registrarResultado(@PathVariable String id,
                                                       @RequestParam Integer golesLocal,
                                                       @RequestParam Integer golesVisitante) {
        return ResponseEntity.ok(partidoService.registrarResultado(id, golesLocal, golesVisitante));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Partido> cambiarEstado(@PathVariable String id,
                                                  @RequestParam String estado) {
        return ResponseEntity.ok(partidoService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}