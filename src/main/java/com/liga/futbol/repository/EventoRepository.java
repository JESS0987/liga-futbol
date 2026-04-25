package com.liga.futbol.repository;

import com.liga.futbol.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    List<Evento> findByPartidoId(String partidoId);
    List<Evento> findByJugadorId(String jugadorId);
    List<Evento> findByTipoAccion(String tipoAccion);
    long countByJugadorIdAndTipoAccion(String jugadorId, String tipoAccion);
}