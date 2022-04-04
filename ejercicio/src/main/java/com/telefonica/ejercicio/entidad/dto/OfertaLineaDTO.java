package com.telefonica.ejercicio.entidad.dto;

import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * @author Marcos
 */
@Data
public class OfertaLineaDTO {

    private Long clienteId;
    private String cliente;
    private String identificacionCliente;
    private Long lineaMovilId;
    private String numeroTelefonico;
    private String plan;
    private String estadoLinea;
    private String tipoLinea;
    private Long ofertaLineaId;
    private String oferta;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;

}
