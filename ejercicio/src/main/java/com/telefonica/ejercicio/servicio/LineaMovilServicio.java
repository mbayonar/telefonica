package com.telefonica.ejercicio.servicio;

import com.telefonica.ejercicio.entidad.LineaMovil;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface LineaMovilServicio extends BaseServicio<LineaMovil, Long> {

    public List<LineaMovil> obtenerLineasCliente(String identificacion, Long tipoDocumentoId);

}