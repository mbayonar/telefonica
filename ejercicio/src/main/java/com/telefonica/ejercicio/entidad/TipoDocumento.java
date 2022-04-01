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
@Table(name = "tipo_documento")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@SelectBeforeUpdate
//@Getter
//@Setter
//@AllArgsConstructor
@Data
public class TipoDocumento extends AuditoriaEntidad {

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;

}
