package com.telefonica.ejercicio.controlador;

import com.telefonica.ejercicio.entidad.OfertaLinea;
import com.telefonica.ejercicio.enums.NombreEntidadEnum;
import com.telefonica.ejercicio.servicio.OfertaLineaServicio;
import com.telefonica.ejercicio.util.RespuestaControlador;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcos
 */
@RestController
@RequestMapping("/ofertaLinea")
public class OfertaLineaControlador extends BaseControladorImpl<OfertaLinea, Long> implements BaseControlador<OfertaLinea, Long> {

    private final OfertaLineaServicio ofertaLineaServicio;

    @Autowired
    public OfertaLineaControlador(OfertaLineaServicio ofertaLineaServicio) {
        super(ofertaLineaServicio, NombreEntidadEnum.OFERTA_LINEA.getValor());
        this.ofertaLineaServicio = ofertaLineaServicio;
    }
    
    @GetMapping("obtenerOfertasPorFecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<RespuestaControlador> obtenerOfertasPorFecha(@PathVariable("fechaInicio") String fechaInicio, @PathVariable("fechaFin") String fechaFin) {
        RespuestaControlador respuestaControlador;
        try {
            respuestaControlador = RespuestaControlador.obtenerRespuestaExitoConData(this.ofertaLineaServicio.obtenerOfertasPorFecha(fechaInicio, fechaFin));
        } catch (Exception exception) {
            logger.error(exception, exception);
            respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeErrorListar(nombreEntidad);
        }
        return new ResponseEntity<>(respuestaControlador, HttpStatus.OK);
    }
}
