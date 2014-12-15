/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumPrazoInicio {
    
    F("Faturamento"),
    E("Entrega"),
    P("Pedido");
    
    private String label;
    
    private EnumPrazoInicio(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
    
}
