package com.liga.futbol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Document(collection = "equipos")
public class Equipo {

    @Id
    private String id;

    @NotBlank
    private String nombre;

    private String logoUrl;
    private Delegado delegado;
    private Colores colores;

    // @OneToOne — Un equipo tiene UN solo entrenador
    @DocumentReference
    private Entrenador entrenador;

    // @OneToMany — Un equipo tiene MUCHOS jugadores
    @DocumentReference
    private List<Jugador> jugadores;

    // @ManyToMany — Un equipo participa en MUCHAS competiciones
    @DocumentReference
    private List<Competicion> competiciones;

    @Data
    public static class Delegado {
        private String nombre;
        private String telefono;
        private String email;
    }

    @Data
    public static class Colores {
        private String principal;
        private String alternativo;
    }
}