package com.liga.futbol.controller;

import com.liga.futbol.model.Equipo;
import com.liga.futbol.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "*")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(equipoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Equipo> crear(@Valid @RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.crear(equipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(@PathVariable String id,
                                              @Valid @RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.actualizar(id, equipo));
    }

    // @OneToOne — asignar entrenador a equipo
    @PatchMapping("/{id}/entrenador")
    public ResponseEntity<Equipo> asignarEntrenador(@PathVariable String id,
                                                     @RequestParam String entrenadorId) {
        return ResponseEntity.ok(equipoService.asignarEntrenador(id, entrenadorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}