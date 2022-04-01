package com.telefonica.ejercicio.entidad;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "oferta_linea")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@SelectBeforeUpdate
@Data
public class OfertaLinea extends AuditoriaEntidad {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linea_movil", nullable = false)
    private LineaMovil lineaMovil;

    @Column(name = "fecha_inicio", nullable = false)
    private Timestamp fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Timestamp fechaFin;
}
