/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumTipoDocumentoMaterialSaida {
    RM("RM"),
    AS_NFT("AS-NFT"),
    NF_DEV("NF-DEV"),
    IN_INVENTARIO("IN-INVENTARIO"),
    NF("NF"),
    NF_e("NF-e");
    
    private String label;
    
    private EnumTipoDocumentoMaterialSaida(String label){
        this.label = label;
    }
    
    private String getLabel(){
        return label;
    }
    
    public String getLabelValue(){
        return label;
    }
}
