package com.telefonica.ejercicio.controlador;

import com.telefonica.ejercicio.entidad.LineaMovil;
import com.telefonica.ejercicio.enums.NombreEntidadEnum;
import com.telefonica.ejercicio.servicio.LineaMovilServicio;
import com.telefonica.ejercicio.util.RespuestaControlador;
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
@RequestMapping("/lineaMovil")
public class LineaMovilControlador extends BaseControladorImpl<LineaMovil, Long> implements BaseControlador<LineaMovil, Long> {

    private final LineaMovilServicio lineaMovilServicio;

    @Autowired
    public LineaMovilControlador(LineaMovilServicio lineaMovilServicio) {
        super(lineaMovilServicio, NombreEntidadEnum.LINEA_MOVIL.getValor());
        this.lineaMovilServicio = lineaMovilServicio;
    }

    @GetMapping("obtenerLineasCliente/{identificacion}/{tipoDocumentoId}")
    public ResponseEntity<RespuestaControlador> obtenerLineasCliente(@PathVariable("identificacion") String identificacion, @PathVariable("tipoDocumentoId") Long tipoDocumentoId) {
        RespuestaControlador respuestaControlador;
        try {
            respuestaControlador = RespuestaControlador.obtenerRespuestaExitoConData(this.lineaMovilServicio.obtenerLineasCliente(identificacion, tipoDocumentoId));
        } catch (Exception exception) {
            logger.error(exception, exception);
            respuestaControlador = respuestaControladorServicio.obtenerRespuestaDeErrorListar(nombreEntidad);
        }
        return new ResponseEntity<>(respuestaControlador, HttpStatus.OK);
    }
}

