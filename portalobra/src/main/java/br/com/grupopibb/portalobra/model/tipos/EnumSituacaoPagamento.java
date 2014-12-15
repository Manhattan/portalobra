/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumSituacaoPagamento {
    A("Aberto"),
    L("Liquidado"),
    a("Aberto"),
    l("Liquidado");
    
    private String label;
    
    private EnumSituacaoPagamento(String label){
        this.label = label;
    }
        
    /** Retorna a descrição do Enum.
     */
    public String getLabel(){
        return label;
    }
    
}
