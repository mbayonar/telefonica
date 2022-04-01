package com.telefonica.ejercicio.servicio.impl;

import com.telefonica.ejercicio.entidad.OfertaLinea;
import com.telefonica.ejercicio.enums.NombreEntidadEnum;
import com.telefonica.ejercicio.repositorio.OfertaLineaRepositorio;
import com.telefonica.ejercicio.servicio.OfertaLineaServicio;
import com.telefonica.ejercicio.util.RespuestaControlador;
import com.telefonica.ejercicio.util.RespuestaControladorServicio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Restrictions.gt;
import static org.hibernate.criterion.Restrictions.lt;
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
    public List<OfertaLinea> obtenerOfertasPorFecha(String fechaInicio, String fechaFin) throws Exception {
        RespuestaControlador respuesta;
        StringBuilder consultaSQL;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Query query;
        List<OfertaLinea> ofertaLineaLista;

        System.out.println("\n\n");
        System.out.println("FECHA INICIO: " + (fechaInicio));
        System.out.println("FECHA FIN: " + (fechaFin));
        System.out.println("\n\n");

//        consultaSQL = new StringBuilder("SELECT ofelin.*, (ofelin.fecha_fin - current_date) as vencimiento");
        consultaSQL = new StringBuilder("SELECT ofelin.*");
        consultaSQL.append(" FROM oferta_linea ofelin");
        consultaSQL.append(" INNER JOIN oferta ofe ON ofelin.id_oferta = ofe.id ");
        consultaSQL.append(" INNER JOIN linea_movil lin ON ofelin.id_linea_movil = lin.id");
        consultaSQL.append(" INNER JOIN cliente cli ON lin.id_cliente = cli.id");
        consultaSQL.append(" INNER JOIN plan pla ON lin.id_plan = pla.id");
        consultaSQL.append(" INNER JOIN estado_linea est ON lin.id_estado_linea = est.id");
        consultaSQL.append(" WHERE (SELECT COUNT(1) FROM linea_movil WHERE id_cliente = cli.id and id_estado_linea = 1) >= 3");
//        consultaSQL.append(" AND (ofelin.fecha_inicio BETWEEN ? AND ?");
//        consultaSQL.append(" OR ofelin.fecha_fin BETWEEN ? AND ?)");
        consultaSQL.append(" AND (ofelin.fecha_inicio BETWEEN '");
        consultaSQL.append(fechaInicio);
        consultaSQL.append("' AND '");
        consultaSQL.append(fechaFin);
        consultaSQL.append("' OR ofelin.fecha_fin BETWEEN '");
        consultaSQL.append(fechaInicio);
        consultaSQL.append("' AND '");
        consultaSQL.append(fechaFin);
        consultaSQL.append("');");
//        consultaSQL.append(" ORDER BY vencimiento ASC;");

        try {
//            connection = DriverManager.getConnection(this.url, this.username, this.password);
//            preparedStatement = connection.prepareStatement(consultaSQL.toString());
//            resultSet = preparedStatement.executeQuery();

            ofertaLineaLista = sessionFactory.getCurrentSession().createSQLQuery(consultaSQL.toString()).getResultList();
            System.out.println("\n\n ARREGLO \n\n");
            ofertaLineaLista.toString();
            System.out.println("\n\n");
            
                    //            query.setParameter(0, fechaInicio, StandardBasicTypes.STRING);
                    //            query.setParameter(1, fechaFin, StandardBasicTypes.STRING);
                    //            query.setParameter(2, fechaInicio, StandardBasicTypes.STRING);
                    //            query.setParameter(3, fechaFin, StandardBasicTypes.STRING);
            
            return ofertaLineaLista;
        } catch (Exception e) {
            logger.error(e, e);
            throw new Exception("Ocurrió un error al listar Ofertas.");
        }
    }

}
