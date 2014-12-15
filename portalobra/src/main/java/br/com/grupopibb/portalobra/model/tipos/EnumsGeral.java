/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumsGeral {

    C("Centro de Negócio"),
    F("Filial"),
    A("Alternativo"),
    N("Não"),
    S("Sim"),
    R(""),
    AB("Em Aberto"),
    AR("Em AR"),
    PE("Em Pedido"),
    CO("Em Coleta");
    
    private String label;

    private EnumsGeral(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
