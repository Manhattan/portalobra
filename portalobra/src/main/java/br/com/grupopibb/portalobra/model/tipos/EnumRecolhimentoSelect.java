/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumRecolhimentoSelect {
    
    APRESENTADO("A"),
    RETIDO("R");
    private String label;

    private EnumRecolhimentoSelect(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EnumRecolhimentoSelect getForLabel(String lab) {
        EnumRecolhimentoSelect enumm;
        for (EnumRecolhimentoSelect e : EnumRecolhimentoSelect.values()) {
            if (e.getLabel().equals(lab)) {
                enumm = e;
                return enumm;
            }
        }
        return null;
    }
    
}
