package com.telefonica.ejercicio.enums;

/**
 *
 * @author Marcos
 */
public enum EstadoLineaEnum {

    ACTIVO(1L, "ACTIVO"),
    CANCELADO(2L, "CANCELADO");

    private Long id;
    private String valor;

    private EstadoLineaEnum(Long id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
