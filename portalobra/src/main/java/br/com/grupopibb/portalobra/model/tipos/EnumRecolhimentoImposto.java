/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumRecolhimentoImposto {

    A("Apresentado"),
    R("Retido"),
    N("");
    private String label;

    private EnumRecolhimentoImposto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EnumRecolhimentoImposto getForLabel(String lab) {
        EnumRecolhimentoImposto enumm;
        for (EnumRecolhimentoImposto e : EnumRecolhimentoImposto.values()) {
            if (e.getLabel().equals(lab)) {
                enumm = e;
                return enumm;
            }
        }
        return EnumRecolhimentoImposto.N;
    }
}
