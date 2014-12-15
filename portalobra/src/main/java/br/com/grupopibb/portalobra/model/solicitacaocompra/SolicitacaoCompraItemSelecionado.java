/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.solicitacaocompra;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
public class SolicitacaoCompraItemSelecionado implements EntityInterface<SolicitacaoCompraItemSelecionado>{
    
    private String especComplemento;
    private Double quantidade;
    private Date dataEntrega;
    private String observacao;
    private Insumo insumo;
    private Integer itemNum;

    public SolicitacaoCompraItemSelecionado(){}
    
    public SolicitacaoCompraItemSelecionado(Integer itemNum, String especComplemento, Double quantidade, Date dataEntrega, String observacao, Insumo insumo){
        this.itemNum = itemNum;
        this.especComplemento = especComplemento;
        this.quantidade = quantidade;
        this.dataEntrega = dataEntrega;
        this.observacao = observacao;
        this.insumo = insumo;
    }
    

    public String getItemItem() {
        String itemFinal = "000" + String.valueOf(itemNum);
        return StringUtils.substring(itemFinal, (itemFinal.length() - 3));
    }

    public String getEspecComplemento() {
        return especComplemento;
    }

    public void setEspecComplemento(String especComplemento) {
        this.especComplemento = especComplemento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }
    
    

    @Override
    public Serializable getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMarcado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(SolicitacaoCompraItemSelecionado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.itemNum);
        hash = 41 * hash + Objects.hashCode(this.especComplemento);
        hash = 41 * hash + Objects.hashCode(this.quantidade);
        hash = 41 * hash + Objects.hashCode(this.insumo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SolicitacaoCompraItemSelecionado other = (SolicitacaoCompraItemSelecionado) obj;
        if (!Objects.equals(this.itemNum, other.itemNum)) {
            return false;
        }
        if (!Objects.equals(this.especComplemento, other.especComplemento)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.dataEntrega, other.dataEntrega)) {
            return false;
        }
        if (!Objects.equals(this.insumo, other.insumo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
