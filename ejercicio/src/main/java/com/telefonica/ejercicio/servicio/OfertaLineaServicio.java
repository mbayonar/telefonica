package com.telefonica.ejercicio.servicio;

import com.telefonica.ejercicio.entidad.OfertaLinea;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface OfertaLineaServicio extends BaseServicio<OfertaLinea, Long> {

    public List<OfertaLinea> obtenerOfertasPorFecha(String fechaInicio, String fechaFin) throws Exception;

}
