/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos.log_followup;

/**
 *
 * @author administrator
 */
public enum EnumOpeLogFollowup {

    I("Insert"),
    U("Update"),
    D("Delete");
    
    private String label;

    private EnumOpeLogFollowup(String label) {
        this.label = label;
    }

    /**
     * Retorna a descrição do Enum.
     */
    public String getLabel() {
        return label;
    }
}
