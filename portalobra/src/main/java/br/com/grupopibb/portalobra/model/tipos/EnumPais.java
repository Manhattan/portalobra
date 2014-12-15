/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumPais {
 
    BR("Brasil"),
    CA("Canadá"),
    ES("Espanha"),
    EU("Estados Unidos"),
    IT("Itália"),
    PT("Portugal");
    
    private String label;

    private EnumPais(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
