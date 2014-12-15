/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumPrazoFora {
    
    N("Sem Deslocamento"),
    S("Fora a Semana"),
    D("Fora a Dezena"),
    Q("Fora a Quinzena"),
    M("Fora o Mes");
    
    private String label;
    
    private EnumPrazoFora(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
    
}
