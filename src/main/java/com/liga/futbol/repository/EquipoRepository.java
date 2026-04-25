package com.liga.futbol.repository;

import com.liga.futbol.model.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EquipoRepository extends MongoRepository<Equipo, String> {
    Optional<Equipo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}