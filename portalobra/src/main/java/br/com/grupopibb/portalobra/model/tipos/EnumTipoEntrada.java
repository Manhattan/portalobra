/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumTipoEntrada {
    
EP(""),
ED(""),
RV("Distrato de vendas imobiliarias"),
SM("Suprimento de materiais e Servicos"),
FF("Fundo fixo"),
OE("Outras Entradas"),
EB("Emprestimos bancarios");
    
    private String label;
    
    private EnumTipoEntrada(String label){
        this.label = label;
    }
    
    private String getLabel(){
        return label;
    }
    
}
