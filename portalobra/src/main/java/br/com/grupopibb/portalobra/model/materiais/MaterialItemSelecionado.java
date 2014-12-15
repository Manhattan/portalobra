/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.materiais;

import br.com.grupopibb.portalobra.model.insumo.Insumo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
public class MaterialItemSelecionado implements Serializable {

    public MaterialItemSelecionado() {
    }

    public MaterialItemSelecionado(Integer itemNum, Insumo insumo, Double quantidade, Double valor, Date data, String observacao, Double estoqueAtual) {
        this.itemNum = itemNum;
        this.insumo = insumo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.data = data;
        this.observacao = observacao;
        this.estoqueAtual = estoqueAtual;
    }
    private Integer itemNum;
    private Insumo insumo;
    private Double quantidade;
    private Double valor;
    private Date data;
    private String observacao;
    private Double estoqueAtual;

    public String getItemItem() {
        String itemFinal = "000" + String.valueOf(itemNum);
        return StringUtils.substring(itemFinal, (itemFinal.length() - 3));
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }
}
