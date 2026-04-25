package com.liga.futbol.controller;

import com.liga.futbol.model.Entrenador;
import com.liga.futbol.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(entrenadorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Entrenador> crear(@Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(entrenadorService.crear(entrenador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(@PathVariable String id,
                                                  @Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(entrenadorService.actualizar(id, entrenador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        entrenadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}