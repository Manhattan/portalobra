/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumSistemaMonitor {
    
    POBRA("Portal Obra"), 
    PRH("Portal RH");
    
    private String label;
    
    private EnumSistemaMonitor(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
    
    
}
