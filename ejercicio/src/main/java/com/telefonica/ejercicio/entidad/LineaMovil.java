package com.telefonica.ejercicio.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "linea_movil")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@SelectBeforeUpdate
@Data
public class LineaMovil extends AuditoriaEntidad {

    @Column(name = "numero_telefonico", nullable = false)
    private String numeroTelefonico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_linea", nullable = false)
    private EstadoLinea estadoLinea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_linea", nullable = false)
    private TipoLinea tipoLinea;

    @JsonIgnoreProperties("lineaMovil")
    @OneToMany(mappedBy = "lineaMovil", fetch = FetchType.LAZY)
    private List<OfertaLinea> ofertaLineaLista;
}
