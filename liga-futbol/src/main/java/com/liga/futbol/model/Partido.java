package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Document(collection = "partidos")
public class Partido {

    @Id
    private String id;

    @NotNull
    @DocumentReference
    private Equipo equipoLocal;

    @NotNull
    @DocumentReference
    private Equipo equipoVisitante;

    private LocalDateTime fecha;
    private String estado = "PROGRAMADO"; // PROGRAMADO, EN_CURSO, FINALIZADO, SUSPENDIDO

    private Integer golesLocal = 0;
    private Integer golesVisitante = 0;

    private String jornada;
    private String sede;
}