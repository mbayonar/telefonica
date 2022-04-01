package com.telefonica.ejercicio.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "oferta")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@SelectBeforeUpdate
@Data
public class Oferta extends AuditoriaEntidad {

    @Column(name = "codigo_oferta", nullable = false)
    private String codigoOferta;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}