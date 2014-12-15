/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumEntradaSaida {

    E("Entrada"),
    S("Saida");
    
    private String label;

    private EnumEntradaSaida(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
