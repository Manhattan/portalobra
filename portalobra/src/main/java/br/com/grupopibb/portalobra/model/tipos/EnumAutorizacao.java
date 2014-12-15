/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumAutorizacao {
    
 A("Autorizado"),
 R("Rejeitado");
    
    private String label;

    private EnumAutorizacao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
