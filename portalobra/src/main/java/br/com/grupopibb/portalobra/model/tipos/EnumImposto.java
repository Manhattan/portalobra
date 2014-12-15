/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumImposto {

    INSS("INSS"),
    ISS("ISS"),
    IRRF("IRRF"),
    PIS("PIS"),
    COFINS("COFINS"),
    CSLL("CSLL"),
    SEST_SENAT("SEST/SENAT");
    private String label;

    private EnumImposto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EnumImposto getForLabel(String lab) {
        EnumImposto enumm;
        for (EnumImposto e : EnumImposto.values()) {
            if (e.getLabel().equals(lab)) {
                enumm = e;
                return enumm;
            }
        }
        return null;
    }
}
