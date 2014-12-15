/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos.log_followup;

/**
 *
 * @author administrator
 */
public enum EnumTabLogFollowup {

    MAT_ENT("Materiais de Entrada"),
    MAT_SAI("Materiais de Saida"),
    SOLIC("Solicitacao");
    
    private String label;

    private EnumTabLogFollowup(String label) {
        this.label = label;
    }

    /**
     * Retorna a descrição do Enum.
     */
    public String getLabel() {
        return label;
    }
}
