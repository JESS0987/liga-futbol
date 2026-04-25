package com.liga.futbol.dto;

import lombok.Data;

@Data
public class FilaPosicion {

    private String nombreEquipo;
    private int pj = 0; // partidos jugados
    private int pg = 0; // partidos ganados
    private int pe = 0; // partidos empatados
    private int pp = 0; // partidos perdidos
    private int gf = 0; // goles a favor
    private int gc = 0; // goles en contra
    private int dg = 0; // diferencia de goles
    private int puntos = 0;

    public FilaPosicion(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}