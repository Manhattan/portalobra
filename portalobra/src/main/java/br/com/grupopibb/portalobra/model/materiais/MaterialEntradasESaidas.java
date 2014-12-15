/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.materiais;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author administrator
 */
public class MaterialEntradasESaidas implements Comparable<MaterialEntradasESaidas>, Serializable {

    public MaterialEntradasESaidas() {
    }

    public MaterialEntradasESaidas(Date data, String classe, String tipoMovimento, String tipoDocumento, String numeroDocumento, Long numero, String itemItem, Double quantidade) {
        this.data = data;
        this.classe = classe;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.numero = numero;
        this.itemItem = itemItem;
        this.quantidade = quantidade;
    }
    private Date data;
    private String classe;
    private String tipoMovimento;
    private String tipoDocumento;
    private String numeroDocumento;
    private Long numero;
    private String itemItem;
    private Double quantidade;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(MaterialEntradasESaidas o) {
        if (this.data.getTime() < o.getData().getTime()) {
            return -1;
        } else if (this.data.getTime() > o.getData().getTime()) {
            return 1;
        }
        return 0;
    }
}
