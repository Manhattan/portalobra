/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumSistemaOrigemEstoque {
    
 SISUP("Suprimentos"),
 SIMAT("Materiais");
    
    private String label;

    private EnumSistemaOrigemEstoque(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
