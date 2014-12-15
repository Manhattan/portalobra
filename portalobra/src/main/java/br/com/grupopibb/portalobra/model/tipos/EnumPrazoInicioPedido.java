/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author administrator
 */
public enum EnumPrazoInicioPedido {

    E("contados a partir da data de Entrega."),
    F("contados a partir da data do Faturamento."),
    P("contados a partir da data do Pedido.");
    private String label;

    private EnumPrazoInicioPedido(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
