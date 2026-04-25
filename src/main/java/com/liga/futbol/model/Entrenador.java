package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Data
@Document(collection = "entrenadores")
public class Entrenador {

    @Id
    private String id;

    @NotBlank
    private String nombre;

    private String apellido;
    private String nacionalidad;
    private int edad;
}