package com.telefonica.ejercicio.enums;

/**
 *
 * @author Marcos
 */
public enum NombreEntidadEnum {
    
    CLIENTE("Cliente"),
    ESTADO_LINEA("Estado de línea"),
    LINEA_MOVIL("Línea móvil"),
    OFERTA("Oferta"),
    OFERTA_LINEA("Oferta línea"),
    PLAN("Plan"),
    TIPO_DOCUMENTO("Tipo de documento"),
    TIPO_LINEA("Tipo de línea");
    
    private String valor;

    private NombreEntidadEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
