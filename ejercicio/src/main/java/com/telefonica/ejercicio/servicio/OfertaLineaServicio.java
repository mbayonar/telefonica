package com.telefonica.ejercicio.servicio;

import com.telefonica.ejercicio.entidad.OfertaLinea;
import com.telefonica.ejercicio.entidad.dto.OfertaLineaDTO;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface OfertaLineaServicio extends BaseServicio<OfertaLinea, Long> {

    public List<OfertaLineaDTO> obtenerOfertasPorFecha(String fechaInicio, String fechaFin) throws Exception;

}
