/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumEspecieTitulosAPagar {
    
    BL("Boleto Bancário"),
    TI("Título de Pagamento"),
    DP("Duplicata"),
    FF("Fundo Fixo"),
    NP("Nota Promissória"),
    RC("Recibo"),
    PM("Parcela de Mútuo"),
    CA("Carteira"),
    BP("Boleto Protestável"),
    CH("Cheque"),
    DB("Débito em Conta");
    
    private String label;
    
    private EnumEspecieTitulosAPagar(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
}
