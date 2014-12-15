/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *Conhecimento de Transporte Rodoviário
 *Relacionado ao pagamento ou não de frete
 * 
 * @author tone.lima
 */
public enum EnumCTR {
    
    CIF("Cost, Insurance and Freight"), //NÃO PAGA O FRETE
    FOB("Free On Board"), //PAGA O FRETE
    C("CIF"),
    F("FOB");               
    
    private String label;
    
    private EnumCTR(String label){
        this.label = label;
    }
    
    public String getLabel(){
    return label;
}
    
}
