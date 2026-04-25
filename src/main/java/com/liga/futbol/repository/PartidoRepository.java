package com.liga.futbol.repository;

import com.liga.futbol.model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartidoRepository extends MongoRepository<Partido, String> {
    List<Partido> findByEstado(String estado);
    List<Partido> findByEquipoLocalIdOrEquipoVisitanteId(String localId, String visitanteId);
}