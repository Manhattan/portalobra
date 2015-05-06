/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.solicitacaocompra;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.insumo.InsumoSub;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Solicitacao_de_Compra_Itens")
@NamedQueries({
    @NamedQuery(name = "SolicitacaoCompraItem.find",
            query = " SELECT DISTINCT sci FROM SolicitacaoCompraItem sci "
            + " WHERE (sci.solicitacao = :solicNumero) "
            + " AND (sci.numero = :solicItemNumero) ")
})
public class SolicitacaoCompraItem implements EntityInterface<SolicitacaoCompraItem> {

    public SolicitacaoCompraItem() {
    }

    public SolicitacaoCompraItem(SolicitacaoCompra solicitacao, Integer numero) {
        this.solicitacao = solicitacao;
        this.numero = numero;
        if (solicitacao != null) {
            this.solicitacaoNumero = solicitacao.getNumero();
        }
    }

    public SolicitacaoCompraItem(SolicitacaoCompra solicitacao, Integer numero, String itemItem, InsumoSub insumoSub, Double quantidade, Date dataEntrega,
            EnumsGeral situacao, String especificacaoComplemento, String observacao) {
        this.solicitacao = solicitacao;
        this.numero = numero;
        this.itemItem = itemItem;
        this.insumoSub = insumoSub;
        this.quantidade = quantidade;
        this.dataEntrega = dataEntrega;
        this.situacao = situacao;
        this.especificacaoComplemento = especificacaoComplemento;
        this.observacao = observacao;
        if (solicitacao != null) {
            this.solicitacaoNumero = solicitacao.getNumero();
        }
    }
    /*
     */
    @ManyToOne(targetEntity = SolicitacaoCompra.class)
    @JoinColumn(name = "Solic_Numero", nullable = false)
    private SolicitacaoCompra solicitacao;
    /*
     */
    @Id
    @Column(name = "Solic_Numero", nullable = false, insertable = false, updatable = false)
    private Long solicitacaoNumero;
    /*
     */
    @Id
    @NotNull
    @Column(name = "SolicItem_Numero", nullable = false)
    private Integer numero;
    /*
     */
    @Column(name = "SolicItem_Item")
    private String itemItem;
    /*
     */
    @OneToOne(targetEntity = InsumoSub.class, fetch = FetchType.EAGER)
    @JoinColumns(value = {
        @JoinColumn(name = "Insumo_Cod", referencedColumnName = "Insumo_Cod"),
        @JoinColumn(name = "SubInsumo_Cod", referencedColumnName = "SubInsumo_Cod"),})
    private InsumoSub insumoSub;
    /*
     */
    @Column(name = "SolicItem_Quantidade", nullable = false)
    private Double quantidade;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "SolicItem_DataEntrega")
    private Date dataEntrega;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "SolicItem_Situacao")
    private EnumsGeral situacao;
    /*
     */
    @Size(max = 250)
    @Column(name = "SolicItem_EspecificacaoCpto")
    private String especificacaoComplemento;
    /*
     */
    @Column(name = "Instrucao_Cod")
    private String instrucao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Instrucao_Data")
    private Date instrucaoData;
    /*
     */
    @Size(max = 30)
    @Column(name = "Usuario_Instrucao")
    private String usuarioInstrucao;
    /*
     */
    @Column(name = "Proj_Cod")
    private int projetoCodigo;
    /*
     */
    @Column(name = "Orc_Cod")
    private int orcamentoCodigo;
    /*
     */
    @Size(max = 384)
    @Column(name = "SolicItem_Obs")
    private String observacao;
    /*
     */
    @Size(max = 250)
    @Column(name = "Instrucao_Motivo")
    private String instrucaoMotivo;
    /*
     */
    @OneToMany(targetEntity = SolicitacaoCompraItemOrcPlan.class,
            mappedBy = "solicitacaoCompraItem",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<SolicitacaoCompraItemOrcPlan> itensPlanoOrcamento;
    /*
     */
    @Transient
    private boolean marcado = false;
    /*
     */
    @Transient
    private TreeNode gradeOrcamento;

    public String getQuantidadeFmt() {
        return NumberUtils.formatCurrencyNoSymbol(quantidade);
    }

    public String getSituacaoAutorizacao() {
        if (instrucao == null) {
            return "";
        }
        switch (instrucao) {
            case "A":
                return "Autorizado";
            case "R":
                return "Recusado";
        }
        return "";
    }

    public String getIdCompleto() {
        return solicitacao.getIdSistema().toString() + "/" + itemItem;
    }

    public String getEspecificacaoCompleta() {
        if (insumoSub != null && insumoSub.getEspecificacao() != null) {
            return insumoSub.getEspecificacao() + " " + especificacaoComplemento;
        }
        return "";
    }

    @Override
    public Serializable getId() {
        return numero;
    }

    @Override
    public String getLabel() {
        return String.valueOf(solicitacao.getNumero()) + "/" + String.valueOf(numero);
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public TreeNode getGradeOrcamento() {
        return gradeOrcamento;
    }

    public void setGradeOrcamento(TreeNode gradeOrcamento) {
        this.gradeOrcamento = gradeOrcamento;
    }

    @Override
    public int compareTo(SolicitacaoCompraItem o) {
        return (this.getSolicitacaoNumero().toString() + this.getNumero().toString()).compareTo(o.getSolicitacaoNumero().toString() + o.getNumero().toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.solicitacaoNumero);
        hash = 29 * hash + Objects.hashCode(this.numero);
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
        final SolicitacaoCompraItem other = (SolicitacaoCompraItem) obj;
        if (!Objects.equals(this.solicitacaoNumero, other.solicitacaoNumero)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    public SolicitacaoCompra getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoCompra solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Long getSolicitacaoNumero() {
        return solicitacaoNumero;
    }

    public void setSolicitacaoNumero(Long solicitacaoNumero) {
        this.solicitacaoNumero = solicitacaoNumero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public InsumoSub getInsumoSub() {
        return insumoSub;
    }

    public void setInsumoSub(InsumoSub insumoSub) {
        this.insumoSub = insumoSub;
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

    public EnumsGeral getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumsGeral situacao) {
        this.situacao = situacao;
    }

    public String getEspecificacaoComplemento() {
        return especificacaoComplemento;
    }

    public void setEspecificacaoComplemento(String especificacaoComplemento) {
        this.especificacaoComplemento = especificacaoComplemento;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public Date getInstrucaoData() {
        return instrucaoData;
    }

    public void setInstrucaoData(Date instrucaoData) {
        this.instrucaoData = instrucaoData;
    }

    public String getUsuarioInstrucao() {
        return usuarioInstrucao;
    }

    public void setUsuarioInstrucao(String usuarioInstrucao) {
        this.usuarioInstrucao = usuarioInstrucao;
    }

    public int getProjetoCodigo() {
        return projetoCodigo;
    }

    public void setProjetoCodigo(int projetoCodigo) {
        this.projetoCodigo = projetoCodigo;
    }

    public int getOrcamentoCodigo() {
        return orcamentoCodigo;
    }

    public void setOrcamentoCodigo(int orcamentoCodigo) {
        this.orcamentoCodigo = orcamentoCodigo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getInstrucaoMotivo() {
        return instrucaoMotivo;
    }

    public void setInstrucaoMotivo(String instrucaoMotivo) {
        this.instrucaoMotivo = instrucaoMotivo;
    }

    public List<SolicitacaoCompraItemOrcPlan> getItensPlanoOrcamento() {
        return itensPlanoOrcamento;
    }

    public void setItensPlanoOrcamento(List<SolicitacaoCompraItemOrcPlan> itensPlanoOrcamento) {
        this.itensPlanoOrcamento = itensPlanoOrcamento;
    }
}
