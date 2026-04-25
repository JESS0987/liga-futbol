package com.liga.futbol.controller;

import com.liga.futbol.model.Competicion;
import com.liga.futbol.service.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/competiciones")
@CrossOrigin(origins = "*")
public class CompeticionController {

    @Autowired
    private CompeticionService competicionService;

    @GetMapping
    public List<Competicion> listar() {
        return competicionService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competicion> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(competicionService.buscarPorId(id));
    }

    @GetMapping("/equipo/{equipoId}")
    public List<Competicion> buscarPorEquipo(@PathVariable String equipoId) {
        return competicionService.buscarPorEquipo(equipoId);
    }

    @PostMapping
    public ResponseEntity<Competicion> crear(@RequestBody Competicion competicion) {
        return ResponseEntity.ok(competicionService.crear(competicion));
    }

    @PatchMapping("/{id}/agregar-equipo")
    public ResponseEntity<Competicion> agregarEquipo(@PathVariable String id,
                                                      @RequestParam String equipoId) {
        return ResponseEntity.ok(competicionService.agregarEquipo(id, equipoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        competicionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}