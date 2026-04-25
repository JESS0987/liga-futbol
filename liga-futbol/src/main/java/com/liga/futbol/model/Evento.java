package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import jakarta.validation.constraints.NotNull;

@Data
@Document(collection = "eventos")
public class Evento {

    @Id
    private String id;

    @NotNull
    private String tipoAccion; // GOL, TARJETA_AMARILLA, TARJETA_ROJA, AUTOGOL

    private Integer minuto;

    @NotNull
    @DocumentReference
    private Partido partido;

    @NotNull
    @DocumentReference
    private Jugador jugador;
}