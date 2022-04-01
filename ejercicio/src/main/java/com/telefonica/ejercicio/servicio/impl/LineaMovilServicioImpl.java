/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telefonica.ejercicio.servicio.impl;

import com.telefonica.ejercicio.entidad.LineaMovil;
import com.telefonica.ejercicio.enums.EstadoLineaEnum;
import com.telefonica.ejercicio.enums.NombreEntidadEnum;
import com.telefonica.ejercicio.repositorio.LineaMovilRepositorio;
import com.telefonica.ejercicio.servicio.LineaMovilServicio;
import com.telefonica.ejercicio.util.Criterio;
import com.telefonica.ejercicio.util.RespuestaControlador;
import com.telefonica.ejercicio.util.RespuestaControladorServicio;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class LineaMovilServicioImpl extends BaseServicioImpl<LineaMovil, Long> implements LineaMovilServicio {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RespuestaControladorServicio respuestaControladorServicio;

    @Autowired
    private LineaMovilRepositorio lineaMovilRepositorio;

    @Autowired
    public LineaMovilServicioImpl(LineaMovilRepositorio lineaMovilRepositorio) {
        super(lineaMovilRepositorio);
    }

    @Override
    public RespuestaControlador crear(LineaMovil lineaMovil) {
        this.lineaMovilRepositorio.crear(lineaMovil);
        return this.respuestaControladorServicio.obtenerRespuestaDeExitoCrearConData(NombreEntidadEnum.LINEA_MOVIL.getValor(), lineaMovil.getId());
    }

    @Override
    public RespuestaControlador actualizar(LineaMovil lineaMovil) {
        this.lineaMovilRepositorio.actualizar(lineaMovil);
        return respuestaControladorServicio.obtenerRespuestaDeExitoActualizar(NombreEntidadEnum.LINEA_MOVIL.getValor());
    }

    @Override
    public RespuestaControlador eliminar(Long lineaMovilId) {
        RespuestaControlador respuesta;
        LineaMovil lineaMovil;

        lineaMovil = this.lineaMovilRepositorio.obtener(lineaMovilId);
        lineaMovil.setEstado(Boolean.FALSE);
        this.lineaMovilRepositorio.actualizar(lineaMovil);
        respuesta = respuestaControladorServicio.obtenerRespuestaDeExitoEliminar(NombreEntidadEnum.LINEA_MOVIL.getValor());

        return respuesta;
    }

    @Override
    public List<LineaMovil> obtenerLineasCliente(String identificacion, Long tipoDocumentoId) {
        Criterio filtro = Criterio.forClass(LineaMovil.class);

        filtro.createCriteria("ofertaLineaLista", "ofelin",JoinType.LEFT_OUTER_JOIN);
        filtro.createCriteria("ofelin.oferta", "ofe", JoinType.LEFT_OUTER_JOIN);
        filtro.createCriteria("cliente", "cli");
        filtro.createAlias("plan", "pla");
        filtro.createAlias("estadoLinea", "est");
        filtro.createAlias("tipoLinea", "tl");
        filtro.createCriteria("cli.tipoDocumento", "tdoc");

        filtro.add(Restrictions.eq("cli.identificacion", identificacion));
        filtro.add(Restrictions.eq("tdoc.id", tipoDocumentoId));
        filtro.add(Restrictions.eq("estado", Boolean.TRUE));
        filtro.add(Restrictions.eq("est.id", EstadoLineaEnum.ACTIVO.getId()));

        return this.lineaMovilRepositorio.buscarPorCriteria(filtro);
    }

}