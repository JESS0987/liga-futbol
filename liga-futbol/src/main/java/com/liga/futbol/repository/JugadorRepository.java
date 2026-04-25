package com.liga.futbol.repository;

import com.liga.futbol.model.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends MongoRepository<Jugador, String> {
    List<Jugador> findByEquipoId(String equipoId);
    List<Jugador> findByEstado(String estado);
}