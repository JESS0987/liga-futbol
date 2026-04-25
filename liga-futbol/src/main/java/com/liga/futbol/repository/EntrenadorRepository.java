package com.liga.futbol.repository;

import com.liga.futbol.model.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
    boolean existsByNombre(String nombre);
}