package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Document(collection = "jugadores")
public class Jugador {

    @Id
    private String id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private String documento;
    private LocalDate fechaNacimiento;
    private Integer dorsal;
    private String estado = "ACTIVO"; // ACTIVO, SUSPENDIDO, LESIONADO

    @NotNull
    @DocumentReference
    private Equipo equipo;
}