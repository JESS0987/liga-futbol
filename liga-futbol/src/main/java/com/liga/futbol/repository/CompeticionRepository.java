package com.liga.futbol.repository;

import com.liga.futbol.model.Competicion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompeticionRepository extends MongoRepository<Competicion, String> {
    List<Competicion> findByEquipoIdsContaining(String equipoId);
}