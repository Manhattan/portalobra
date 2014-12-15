/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumNatureza {
    
    F("Pessoa Física"),
    J("Pessoa Jurídica");
    
    private String label;
    
    private EnumNatureza(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
    
}
