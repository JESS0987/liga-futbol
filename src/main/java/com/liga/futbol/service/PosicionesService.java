package com.liga.futbol.service;

import com.liga.futbol.dto.FilaPosicion;
import com.liga.futbol.model.Equipo;
import com.liga.futbol.model.Partido;
import com.liga.futbol.repository.EquipoRepository;
import com.liga.futbol.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PosicionesService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    public List<FilaPosicion> calcularTabla() {
        Map<String, FilaPosicion> tabla = new HashMap<>();

        // Inicializar todos los equipos
        for (Equipo equipo : equipoRepository.findAll()) {
            tabla.put(equipo.getId(), new FilaPosicion(equipo.getNombre()));
        }

        // Procesar partidos finalizados
        for (Partido partido : partidoRepository.findByEstado("FINALIZADO")) {
            String localId = partido.getEquipoLocal().getId();
            String visitanteId = partido.getEquipoVisitante().getId();

            FilaPosicion local = tabla.get(localId);
            FilaPosicion visitante = tabla.get(visitanteId);

            if (local == null || visitante == null) continue;

            int gl = partido.getGolesLocal();
            int gv = partido.getGolesVisitante();

            local.setPj(local.getPj() + 1);
            visitante.setPj(visitante.getPj() + 1);
            local.setGf(local.getGf() + gl);
            local.setGc(local.getGc() + gv);
            visitante.setGf(visitante.getGf() + gv);
            visitante.setGc(visitante.getGc() + gl);

            if (gl > gv) {
                local.setPg(local.getPg() + 1);
                visitante.setPp(visitante.getPp() + 1);
            } else if (gl < gv) {
                visitante.setPg(visitante.getPg() + 1);
                local.setPp(local.getPp() + 1);
            } else {
                local.setPe(local.getPe() + 1);
                visitante.setPe(visitante.getPe() + 1);
            }
        }

        // Calcular puntos y diferencia de goles
        List<FilaPosicion> resultado = new ArrayList<>(tabla.values());
        for (FilaPosicion fila : resultado) {
            fila.setPuntos(fila.getPg() * 3 + fila.getPe());
            fila.setDg(fila.getGf() - fila.getGc());
        }

        // Ordenar: puntos → diferencia goles → goles a favor
        resultado.sort(Comparator
                .comparingInt(FilaPosicion::getPuntos).reversed()
                .thenComparingInt(FilaPosicion::getDg).reversed()
                .thenComparingInt(FilaPosicion::getGf).reversed());

        return resultado;
    }
}