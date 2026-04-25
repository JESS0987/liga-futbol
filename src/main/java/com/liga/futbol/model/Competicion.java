package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Document(collection = "competiciones")
public class Competicion {

    @Id
    private String id;

    @NotBlank
    private String nombre;

    private String pais;
    private String temporada;

    // @ManyToMany — Una competición tiene muchos equipos
    private List<String> equipoIds;
}