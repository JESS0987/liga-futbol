package com.liga.futbol.controller;

import com.liga.futbol.dto.FilaPosicion;
import com.liga.futbol.service.PosicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posiciones")
@CrossOrigin(origins = "*")
public class PosicionesController {

    @Autowired
    private PosicionesService posicionesService;

    @GetMapping
    public List<FilaPosicion> tabla() {
        return posicionesService.calcularTabla();
    }
}