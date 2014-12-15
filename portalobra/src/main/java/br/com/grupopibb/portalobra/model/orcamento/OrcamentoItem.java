/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.orcamento;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
/*@Entity
 @Table(name = "#ItemOrcPlanT")
 @NamedQuery(name = "OrcamentoItem.find",
 query = "SELECT DISTINCT i FROM OrcamentoItem i")*/
public class OrcamentoItem implements Serializable, Comparable<OrcamentoItem> {
 
    public OrcamentoItem() {
    }

    public OrcamentoItem(OrcamentoItem orcamentoItem) {
        this.tipoItem = orcamentoItem.getTipoItem();
        this.keyField = orcamentoItem.getKeyField();
        this.codigo = orcamentoItem.getCodigo();
        this.nivel = orcamentoItem.getNivel();
        this.codigoSuperior = orcamentoItem.getCodigoSuperior();
        this.codigoPlanoOrc = orcamentoItem.getCodigoPlanoOrc();
        this.codigoPlanoItemOrc = orcamentoItem.getCodigoPlanoItemOrc();
        this.codigoPlanoSuperiorOrc = orcamentoItem.getCodigoPlanoSuperiorOrc();
        this.codigoProjetoOrc = orcamentoItem.getCodigoProjetoOrc();
        this.itemServicoOrc = orcamentoItem.getItemServicoOrc();
        this.insumoCod = orcamentoItem.getInsumoCod();
        this.discriminacao = orcamentoItem.getDiscriminacao();
        this.codigoUnidade = orcamentoItem.getCodigoUnidade();
        this.ordemOrc = orcamentoItem.getOrdemOrc();
        this.ordemCentralSeq = orcamentoItem.getOrdemCentralSeq();
        this.classeOrc = orcamentoItem.getClasseOrc();
        this.codigoPlano = orcamentoItem.getCodigoPlano();
        this.codigoPlanoItem = orcamentoItem.getCodigoPlanoItem();
        this.codigoProjeto = orcamentoItem.getCodigoProjeto();
        this.codigoOrcamento = orcamentoItem.getCodigoOrcamento();
        this.codigoPO = orcamentoItem.getCodigoPO();
        this.codigoItemOrc = orcamentoItem.getCodigoItemOrc();
        this.quantidadeOrc = orcamentoItem.getQuantidadeOrc();
        this.valorOrc = orcamentoItem.getValorOrc();
        this.quantidadeSolicGeral = orcamentoItem.getQuantidadeSolicGeral();
        this.valorSolic = orcamentoItem.getValorSolic();
        this.valorSolicAtual = orcamentoItem.getValorSolicAtual();
        this.valorSolicSO = orcamentoItem.getValorSolicSO();
        this.quantidadeSaldoGeral = orcamentoItem.getQuantidadeSaldoGeral();
        this.valorSaldoSolic = orcamentoItem.getValorSaldoSolic();
        this.orcadoAjuste = orcamentoItem.getOrcadoAjuste();
        this.quantidadeSaldoAjustado = orcamentoItem.getQuantidadeSaldoAjustado();
        this.quantidadeSaldoAjustadoOriginal = orcamentoItem.getQuantidadeSaldoAjustadoOriginal();
    }
    
    

    public OrcamentoItem(Integer keyField, Long insumoCod, String discriminacao, String codigoUnidade) {
        this.keyField = keyField;
        this.insumoCod = insumoCod;
        this.discriminacao = discriminacao;
        this.codigoUnidade = codigoUnidade;
    }

    public OrcamentoItem(Integer keyField, Long insumoCod, String discriminacao, String codigoUnidade, Integer codigoPlanoItem, String ordemCentralSeq, String classeOrc) {
        this.keyField = keyField;
        this.insumoCod = insumoCod;
        this.discriminacao = discriminacao;
        this.codigoUnidade = codigoUnidade;
        this.codigoPlanoItem = codigoPlanoItem;
        this.ordemCentralSeq = ordemCentralSeq;
        this.classeOrc = classeOrc;
    }

    public OrcamentoItem(String tipoItem, Integer keyField, Integer codigo, Integer nivel, Integer codigoSuperior, Integer codigoPlanoOrc, Integer codigoPlanoItemOrc, Integer codigoPlanoSuperiorOrc, Integer codigoProjetoOrc, String itemServicoOrc, Long insumoCod, String discriminacao, String codigoUnidade, String ordemOrc, String ordemCentralSeq, String classeOrc, Integer codigoPlano, Integer codigoPlanoItem, Integer codigoProjeto, Integer codigoOrcamento, Integer codigoPO, Integer codigoItemOrc, Double quantidadeOrc, Double valorOrc, Double quantidadeSolicGeral, Double valorSolic, Double valorSolicAtual, Double valorSolicSO, Double quantidadeSaldoGeral, Double valorSaldoSolic, Double orcadoAjuste, Double quantidadeSaldoAjustado) {
        this.tipoItem = tipoItem;
        this.keyField = keyField;
        this.codigo = codigo;
        this.nivel = nivel;
        this.codigoSuperior = codigoSuperior;
        this.codigoPlanoOrc = codigoPlanoOrc;
        this.codigoPlanoItemOrc = codigoPlanoItemOrc;
        this.codigoPlanoSuperiorOrc = codigoPlanoSuperiorOrc;
        this.codigoProjetoOrc = codigoProjetoOrc;
        this.itemServicoOrc = itemServicoOrc;
        this.insumoCod = insumoCod;
        this.discriminacao = discriminacao;
        this.codigoUnidade = codigoUnidade;
        this.ordemOrc = ordemOrc;
        this.ordemCentralSeq = ordemCentralSeq;
        this.classeOrc = classeOrc;
        this.codigoPlano = codigoPlano;
        this.codigoPlanoItem = codigoPlanoItem;
        this.codigoProjeto = codigoProjeto;
        this.codigoOrcamento = codigoOrcamento;
        this.codigoPO = codigoPO;
        this.codigoItemOrc = codigoItemOrc;
        this.quantidadeOrc = quantidadeOrc;
        this.valorOrc = valorOrc;
        this.quantidadeSolicGeral = quantidadeSolicGeral;
        this.valorSolic = valorSolic;
        this.valorSolicAtual = valorSolicAtual;
        this.valorSolicSO = valorSolicSO;
        this.quantidadeSaldoGeral = quantidadeSaldoGeral;
        this.valorSaldoSolic = valorSaldoSolic;
        this.orcadoAjuste = orcadoAjuste;
        this.quantidadeSaldoAjustado = quantidadeSaldoAjustado;
        this.quantidadeSaldoAjustadoOriginal = quantidadeSaldoAjustado;
    }
    // @Column(name = "ItemOrcPlanT_Tipo")
    private String tipoItem;
    /*
     */
    // @Id
    // @Column(name = "ItemOrcPlanT_KeyField")
    private Integer keyField;
    /*
     */
    // @Column(name = "ItemOrcPlanT_Cod")
    private Integer codigo;
    /*
     */
    // @Column(name = "ItemOrcPlanT_Nivel")
    private Integer nivel;
    /*
     */
    // @Column(name = "ItemOrcPlanTSuperior_Cod")
    private Integer codigoSuperior;
    /*
     */
    // @Column(name = "OrcPlan_Cod")
    private Integer codigoPlanoOrc;
    /*
     */
    // @Column(name = "ItemOrcPlan_Cod")
    private Integer codigoPlanoItemOrc;
    /*
     */
    // @Column(name = "ItemOrcPlanSuperior_Cod")
    private Integer codigoPlanoSuperiorOrc;
    /*
     */
    // @Column(name = "CompProj_Cod")
    private Integer codigoProjetoOrc;
    /*
     */
    // @Column(name = "ItemOrcPlanT_ItemServico")
    private String itemServicoOrc;
    /*
     */
    // @Column(name = "Insumo_Cod")
    private Long insumoCod;
    /*
     */
    // @Column(name = "ItemOrcPlanT_Discriminacao")
    private String discriminacao;
    /*
     */
    // @Column(name = "Unid_Cod")
    private String codigoUnidade;
    /*
     */
    // @Column(name = "ItemOrcPlanT_Ordem")
    private String ordemOrc;
    /*
     */
    // @Column(name = "ItemOrcPlanTOrdemCentral_Seq")
    private String ordemCentralSeq;
    /*
     */
    // @Column(name = "ItemOrcPlanT_Classe")
    private String classeOrc;
    /*
     */
    // @Column(name = "Plan_Cod")
    private Integer codigoPlano;
    /*
     */
    // @Column(name = "PlanItem_Cod")
    private Integer codigoPlanoItem;
    /*
     */
    // @Column(name = "Proj_Cod")
    private Integer codigoProjeto;
    /*
     */
    // @Column(name = "Orc_Cod")
    private Integer codigoOrcamento;
    /*
     */
    // @Column(name = "PO_Cod")
    private Integer codigoPO;
    /*
     */
    // @Column(name = "ItemOrc_Cod")
    private Integer codigoItemOrc;
    /*
     */
    // @Column(name = "ItemOrcPlanT_OCQtd")
    private Double quantidadeOrc;
    // @Column(name = "ItemOrcPlanT_OCVlr")
    private Double valorOrc;
    // @Column(name = "ItemOrcPlanT_SOQtd")
    private Double quantidadeSolicGeral;
    // @Column(name = "ItemOrcPlanT_SOVlr")
    private Double valorSolic;
    // @Column(name = "ItemOrcPlanT_SOQtdSoli")
    private Double valorSolicAtual;
    // @Column(name = "ItemOrcPlanT_SOVlrSolic")
    private Double valorSolicSO;
    // @Column(name = "ItemOrcPlanT_SOQtdSaldo")
    private Double quantidadeSaldoGeral;
    // @Column(name = "ItemOrcPlanT_SOVlrSaldo")
    private Double valorSaldoSolic;
    // @Column(name = "PIOPIns_AjusteQtd")
    private Double orcadoAjuste;
    // @Column(name = "ItemOrcPlanT_QtdSaldoAj")
    private Double quantidadeSaldoAjustado;
    /* CRIADA PARA GUARDAR O SALDO ORIGINAL A SER UTILIZADO NO TRATAMENTO DA SOLICITAÇÃO DE COMPRA */
    private Double quantidadeSaldoAjustadoOriginal;

    public String getBackgroundColor() {
        switch (getClasse()) {
            case "PL":
                return "#B4E5CF"; //VERDE MAIS ESCURO
            case "IT":
                return "#D1F9DD"; //VERDE MAIS CLARO
            default:
                return "white"; //BRANCO
        }
    }
    
    public void setBackgroundColor(String backgroundColor){
        
    }

    /**
     * Determina o tamanho do recuo da esquerda de acordo com o tamanho do
     * sequencial.
     *
     * @return inteiro com o tamanho do recuo.
     */
    public int getRecuoEsquerda() {
        //return getOrdemCentralSeq().length();
        if (getClasse().equals("PL")) { 
            return 0;
        }
        return getOrdemPai().length() * 2;
    }

    /**
     * O código sequencial do nível superior, sendo o objeto atual subnível de
     * seu superior.
     *
     * @return a ordem sequencial do objeto pai.
     */
    public String getOrdemPai() {
        String pai = StringUtils.replace(ordemCentralSeq, " ", "");
        if (pai.length() > 4) {
            pai = StringUtils.left(pai, pai.length() - 4);
        }
        return pai;
    }

    /**
     *
     * @return a classe do item sem os espaços em branco.
     */
    public String getClasse() {
        return StringUtils.replace(classeOrc, " ", "");
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Integer getKeyField() {
        return keyField;
    }

    public void setKeyField(Integer keyField) {
        this.keyField = keyField;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getCodigoSuperior() {
        return codigoSuperior;
    }

    public void setCodigoSuperior(Integer codigoSuperior) {
        this.codigoSuperior = codigoSuperior;
    }

    public Integer getCodigoPlanoOrc() {
        return codigoPlanoOrc;
    }

    public void setCodigoPlanoOrc(Integer codigoPlanoOrc) {
        this.codigoPlanoOrc = codigoPlanoOrc;
    }

    public Integer getCodigoPlanoItemOrc() {
        return codigoPlanoItemOrc;
    }

    public void setCodigoPlanoItemOrc(Integer codigoPlanoItemOrc) {
        this.codigoPlanoItemOrc = codigoPlanoItemOrc;
    }

    public Integer getCodigoPlanoSuperiorOrc() {
        return codigoPlanoSuperiorOrc;
    }

    public void setCodigoPlanoSuperiorOrc(Integer codigoPlanoSuperiorOrc) {
        this.codigoPlanoSuperiorOrc = codigoPlanoSuperiorOrc;
    }

    public Integer getCodigoProjetoOrc() {
        return codigoProjetoOrc;
    }

    public void setCodigoProjetoOrc(Integer codigoProjetoOrc) {
        this.codigoProjetoOrc = codigoProjetoOrc;
    }

    public String getItemServicoOrc() {
        return itemServicoOrc;
    }

    public void setItemServicoOrc(String itemServicoOrc) {
        this.itemServicoOrc = itemServicoOrc;
    }

    public Long getInsumoCod() {
        return insumoCod;
    }

    public void setInsumoCod(Long insumoCod) {
        this.insumoCod = insumoCod;
    }

    public String getDiscriminacao() {
        return discriminacao;
    }

    public void setDiscriminacao(String discriminacao) {
        this.discriminacao = discriminacao;
    }

    public String getCodigoUnidade() {
        return codigoUnidade;
    }

    public void setCodigoUnidade(String codigoUnidade) {
        this.codigoUnidade = codigoUnidade;
    }

    public String getOrdemOrc() {
        return ordemOrc;
    }

    public void setOrdemOrc(String ordemOrc) {
        this.ordemOrc = ordemOrc;
    }

    /**
     * O código que determina a ordem do elemento como subnível do nível
     * superior. Ex.: 0100280001 (este é subnível de 010028)
     *
     * @return sequencial atual.
     */
    public String getOrdemCentralSeq() {
        return ordemCentralSeq;
    }

    public void setOrdemCentralSeq(String ordemCentralSeq) {
        this.ordemCentralSeq = ordemCentralSeq;
    }

    public String getClasseOrc() {
        return classeOrc;
    }

    public void setClasseOrc(String classeOrc) {
        this.classeOrc = classeOrc;
    }

    public Integer getCodigoPlano() {
        return codigoPlano;
    }

    public void setCodigoPlano(Integer codigoPlano) {
        this.codigoPlano = codigoPlano;
    }

    public Integer getCodigoPlanoItem() {
        return codigoPlanoItem;
    }

    public void setCodigoPlanoItem(Integer codigoPlanoItem) {
        this.codigoPlanoItem = codigoPlanoItem;
    }

    public Integer getCodigoProjeto() {
        return codigoProjeto;
    }

    public void setCodigoProjeto(Integer codigoProjeto) {
        this.codigoProjeto = codigoProjeto;
    }

    public Integer getCodigoOrcamento() {
        return codigoOrcamento;
    }

    public void setCodigoOrcamento(Integer codigoOrcamento) {
        this.codigoOrcamento = codigoOrcamento;
    }

    public Integer getCodigoPO() {
        return codigoPO;
    }

    public void setCodigoPO(Integer codigoPO) {
        this.codigoPO = codigoPO;
    }

    public Integer getCodigoItemOrc() {
        return codigoItemOrc;
    }

    public void setCodigoItemOrc(Integer codigoItemOrc) {
        this.codigoItemOrc = codigoItemOrc;
    }

    public Double getQuantidadeOrc() {
        return quantidadeOrc;
    }

    public void setQuantidadeOrc(Double quantidadeOrc) {
        this.quantidadeOrc = quantidadeOrc;
    }

    public Double getValorOrc() {
        return valorOrc;
    }

    public void setValorOrc(Double valorOrc) {
        this.valorOrc = valorOrc;
    }

    public Double getQuantidadeSolicGeral() {
        return quantidadeSolicGeral;
    }

    public void setQuantidadeSolicGeral(Double quantidadeSolicGeral) {
        this.quantidadeSolicGeral = quantidadeSolicGeral;
    }

    public Double getValorSolic() {
        return valorSolic;
    }

    public void setValorSolic(Double valorSolic) {
        this.valorSolic = valorSolic;
    }

    public Double getValorSolicAtual() {
        return valorSolicAtual;
    }

    public void setValorSolicAtual(Double valorSolicAtual) {
        this.valorSolicAtual = valorSolicAtual;
    }

    public Double getValorSolicSO() {
        return valorSolicSO;
    }

    public void setValorSolicSO(Double valorSolicSO) {
        this.valorSolicSO = valorSolicSO;
    }

    public Double getQuantidadeSaldoGeral() {
        return quantidadeSaldoGeral;
    }

    public void setQuantidadeSaldoGeral(Double quantidadeSaldoGeral) {
        this.quantidadeSaldoGeral = quantidadeSaldoGeral;
    }

    public Double getValorSaldoSolic() {
        return valorSaldoSolic;
    }

    public void setValorSaldoSolic(Double valorSaldoSolic) {
        this.valorSaldoSolic = valorSaldoSolic;
    }

    public Double getOrcadoAjuste() {
        return orcadoAjuste;
    }

    public void setOrcadoAjuste(Double orcadoAjuste) {
        this.orcadoAjuste = orcadoAjuste;
    }

    public Double getQuantidadeSaldoAjustado() {
        return quantidadeSaldoAjustado;
    }

    public void setQuantidadeSaldoAjustado(Double quantidadeSaldoAjustado) {
        this.quantidadeSaldoAjustado = quantidadeSaldoAjustado;
    }

    public Double getQuantidadeSaldoAjustadoOriginal() {
        return quantidadeSaldoAjustadoOriginal;
    }

    public void setQuantidadeSaldoAjustadoOriginal(Double quantidadeSaldoAjustadoOriginal) {
        this.quantidadeSaldoAjustadoOriginal = quantidadeSaldoAjustadoOriginal;
    }

    public Serializable getId() {
        return keyField;
    }

    public String getLabel() {
        return tipoItem;
    }

    public boolean verificarId() {
        return false;
    }

    public boolean isMarcado() {
        return false;
    }
    
    /**
     * @return String composta única por OrcamentoItem.
     */
    public String getCompareId(){
        return this.getKeyField().toString()+"_"+this.getInsumoCod().toString();
    }

    @Override
    public int compareTo(OrcamentoItem o) {
        return this.getCompareId().compareTo(o.getCompareId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.keyField);
        hash = 83 * hash + Objects.hashCode(this.insumoCod);
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
        final OrcamentoItem other = (OrcamentoItem) obj;
        if (!Objects.equals(this.keyField, other.keyField)) {
            return false;
        }
        if (!Objects.equals(this.insumoCod, other.insumoCod)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "Insumo: " + insumoCod + " - " + discriminacao + " - " + codigoUnidade;
    }
}
