package com.telefonica.ejercicio.servicio.impl;

import com.telefonica.ejercicio.entidad.OfertaLinea;
import com.telefonica.ejercicio.entidad.dto.OfertaLineaDTO;
import com.telefonica.ejercicio.enums.NombreEntidadEnum;
import com.telefonica.ejercicio.repositorio.OfertaLineaRepositorio;
import com.telefonica.ejercicio.servicio.OfertaLineaServicio;
import com.telefonica.ejercicio.util.RespuestaControlador;
import com.telefonica.ejercicio.util.RespuestaControladorServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class OfertaLineaServicioImpl extends BaseServicioImpl<OfertaLinea, Long> implements OfertaLineaServicio {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RespuestaControladorServicio respuestaControladorServicio;

    @Autowired
    private OfertaLineaRepositorio ofertaLineaRepositorio;

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    public OfertaLineaServicioImpl(OfertaLineaRepositorio ofertaLineaRepositorio) {
        super(ofertaLineaRepositorio);
    }

    @Override
    public RespuestaControlador crear(OfertaLinea ofertaLinea) {
        this.ofertaLineaRepositorio.crear(ofertaLinea);
        return this.respuestaControladorServicio.obtenerRespuestaDeExitoCrearConData(NombreEntidadEnum.OFERTA_LINEA.getValor(), ofertaLinea.getId());
    }

    @Override
    public RespuestaControlador actualizar(OfertaLinea ofertaLinea) {
        this.ofertaLineaRepositorio.actualizar(ofertaLinea);
        return respuestaControladorServicio.obtenerRespuestaDeExitoActualizar(NombreEntidadEnum.OFERTA_LINEA.getValor());
    }

    @Override
    public RespuestaControlador eliminar(Long ofertaLineaId) {
        RespuestaControlador respuesta;
        OfertaLinea ofertaLinea;

        ofertaLinea = this.ofertaLineaRepositorio.obtener(ofertaLineaId);
        ofertaLinea.setEstado(Boolean.FALSE);
        this.ofertaLineaRepositorio.actualizar(ofertaLinea);
        respuesta = respuestaControladorServicio.obtenerRespuestaDeExitoEliminar(NombreEntidadEnum.OFERTA_LINEA.getValor());

        return respuesta;
    }

    @Override
    public List<OfertaLineaDTO> obtenerOfertasPorFecha(String fechaInicio, String fechaFin) throws Exception {
        StringBuilder consultaSQL;
        List<OfertaLineaDTO> ofertaLineaLista;

        consultaSQL = new StringBuilder("SELECT cli.id as \"clienteId\", cli.apellido_paterno || ' ' || cli.apellido_materno || ', ' || cli.nombres as \"cliente\", cli.identificacion as \"identificacionCliente\",");
        consultaSQL.append(" lin.id as \"lineaMovilId\", lin.numero_telefonico as \"numeroTelefonico\", pla.descripcion as \"plan\", est.descripcion as \"estadoLinea\", tl.descripcion as \"tipoLinea\",");
        consultaSQL.append(" ofelin.id as \"ofertaLineaId\", ofe.descripcion as \"oferta\", ofelin.fecha_inicio \"fechaInicio\", ofelin.fecha_fin as \"fechaFin\"");
        consultaSQL.append(" FROM oferta_linea ofelin");
        consultaSQL.append(" INNER JOIN oferta ofe ON ofelin.id_oferta = ofe.id ");
        consultaSQL.append(" INNER JOIN linea_movil lin ON ofelin.id_linea_movil = lin.id");
        consultaSQL.append(" INNER JOIN cliente cli ON lin.id_cliente = cli.id");
        consultaSQL.append(" INNER JOIN plan pla ON lin.id_plan = pla.id");
        consultaSQL.append(" INNER JOIN estado_linea est ON lin.id_estado_linea = est.id");
        consultaSQL.append(" INNER JOIN tipo_linea tl ON lin.id_tipo_linea = tl.id");
        consultaSQL.append(" WHERE (SELECT COUNT(1) FROM linea_movil WHERE id_cliente = cli.id and id_estado_linea = 1) >= 3");
        consultaSQL.append(" AND (ofelin.fecha_inicio BETWEEN '");
        consultaSQL.append(fechaInicio);
        consultaSQL.append("' AND '");
        consultaSQL.append(fechaFin);
        consultaSQL.append("' OR ofelin.fecha_fin BETWEEN '");
        consultaSQL.append(fechaInicio);
        consultaSQL.append("' AND '");
        consultaSQL.append(fechaFin);
        consultaSQL.append("')");
        consultaSQL.append(" ORDER BY (ofelin.fecha_fin - current_date) ASC;");

        try {
            ofertaLineaLista = sessionFactory.getCurrentSession().createSQLQuery(consultaSQL.toString()).getResultList();
            return ofertaLineaLista;
        } catch (Exception e) {
            logger.error(e, e);
            throw new Exception("Ocurrió un error al listar Ofertas.");
        }
    }

}
