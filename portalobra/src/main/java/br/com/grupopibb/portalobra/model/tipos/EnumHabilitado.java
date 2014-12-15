/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 * Enum utilizado como tipo ordinal no JPA. 
 * 
 * 'N' representa o valor 0. 
 * 'S' representa o valor 1. 
 * 
 * A ordem dos elementos não pode ser alterada.
 *
 * @author administrator
 */
public enum EnumHabilitado {

    N("Nao"),
    S("Sim");
    private String label;

    private EnumHabilitado(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
