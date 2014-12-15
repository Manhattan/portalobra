/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumTipoDocumentoMaterialEntrada {

    AE("AE"),
    NF("NF-"),
    NFF("NFF"),
    REC("REC"),
    CFIS("CFIS");
    
    
    private String label;

    private EnumTipoDocumentoMaterialEntrada(String label) {
        this.label = label;
    }

    private String getLabel() {
        return label;
    }
    
    public String getLabelValue(){
        return label;
    }
}
