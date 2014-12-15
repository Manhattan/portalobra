/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;

/**
 *
 * @author tone.lima
 */
public enum EnumEspecieCentroCusto {

    /**
     * Aumoxarifado Central
     */
    DM("Aumoxarifado Central"),
    /**
     * Diretoria
     */
    DR("Diretoria"),
    /**
     * Escritório
     */
    ES("Escritório"),
    /**
     * Imóveis de Investimento
     */
    II("Imóveis de Investimento"),
    /**
     * Imóveis de Terceiros
     */
    IM("Imóveis de Terceiros"),
    /**
     * Lojas
     */
    LJ("Lojas"),
    /**
     * Loteamento
     */
    LO("Loteamento"),
    /**
     * Manutenção
     */
    MN("Manutenção"),
    /**
     * Obra Contratada ou Administração
     */
    OC("Obra Contratada ou Administração"),
    /**
     * Operações Financeiras
     */
    OF("Operações Financeiras"),
    /**
     * Obra Própria
     */
    OP("Obra Própria"),
    /**
     * Projeto
     */
    PJ("Projeto"),
    /**
     * Shopping
     */
    SH("Shopping"),
    /**
     * Terreno
     */
    TE("Terreno"),
    /**
     * Estacionamento
     */
    VE("Estacionamento");
    /**
     * Label do Enum.
     */
    private String label;

    private EnumEspecieCentroCusto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
