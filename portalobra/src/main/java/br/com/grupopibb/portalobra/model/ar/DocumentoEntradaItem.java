/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.ar;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Documento_de_Entrada_Item")
@NamedQueries({
    @NamedQuery(name = "DocumentoEntrataItem.findBySolic",
            query = " SELECT DISTINCT d FROM DocumentoEntradaItem d "
            + " WHERE (d.solicitacaoCompraItem = :solicItem) "),
    @NamedQuery(name = "DocumentoEntradaItem.findByInsumo", 
            query = " SELECT DISTINCT d FROM DocumentoEntradaItem d"
        + " WHERE ( :centro2 = 'todos' OR d.documentoEntrada.centro = :centro ) "
        + " AND ( d.insumo = :insumo ) ")
})
public class DocumentoEntradaItem implements EntityInterface<DocumentoEntradaItem> {

    public DocumentoEntradaItem() {
    }

    /**
     * Construtor padrão para inclusão de itens no registro do AR.
     *
     * @param documentoEntrada
     * @param itemNumero
     * @param itemItem
     * @param pedidoItem
     * @param solicitacaoCompraItem
     * @param insumoCod
     * @param quantidade
     * @param totalItem
     * @param especificacao
     * @param unidade
     * @param quantidadeRecebida
     * @param precoUnitarioE
     * @param totalItemE
     * @param aliqIPIE
     * @param valorIPIE
     * @param totalItemComIPIE
     * @param totalItemRp
     * @param precoUnitarioPedido
     * @param totalItemPedido
     * @param aliqIPIPedido
     * @param valorIPIPedido
     * @param totalItemComIPIPedido
     * @param descontoProduto
     * @param acrescimoProduto
     * @param assuntoOrigem
     * @param assuntoCod
     * @param GOCod
     * @param SubGOCod
     */
    public DocumentoEntradaItem(DocumentoEntrada documentoEntrada, Integer itemNumero, String itemItem, PedidoItem pedidoItem,
            SolicitacaoCompraItem solicitacaoCompraItem, Long insumoCod, Float quantidade, Float totalItem, String especificacao,
            String unidade, Float quantidadeRecebida, Float precoUnitarioE, Float totalItemE, Float aliqIPIE, Float valorIPIE,
            Float totalItemComIPIE, Float totalItemRp, Float precoUnitarioPedido, Float totalItemPedido, Float aliqIPIPedido,
            Float valorIPIPedido, Float totalItemComIPIPedido, Float descontoProduto, Float acrescimoProduto, String assuntoOrigem,
            String assuntoCod, String GOCod, String SubGOCod, Float valorAcrescimosRat, Float valorDescontosRat, Float precoUnitario) {
        this.documentoEntrada = documentoEntrada;
        this.itemNumero = itemNumero;
        this.itemItem = itemItem;
        this.pedidoItem = pedidoItem;
        this.solicitacaoCompraItem = solicitacaoCompraItem;
        this.insumoCod = insumoCod;
        this.quantidade = quantidade;
        this.totalItem = totalItem;
        this.especificacao = especificacao;
        this.unidade = unidade;
        this.quantidadeRecebida = quantidadeRecebida;
        this.precoUnitarioE = precoUnitarioE;
        this.totalItemE = totalItemE;
        this.aliqIPIE = aliqIPIE;
        this.valorIPIE = valorIPIE;
        this.totalItemComIPIE = totalItemComIPIE;
        this.totalItemRp = totalItemRp;
        this.precoUnitarioPedido = precoUnitarioPedido;
        this.totalItemPedido = totalItemPedido;
        this.aliqIPIPedido = aliqIPIPedido;
        this.valorIPIPedido = valorIPIPedido;
        this.totalItemComIPIPedido = totalItemComIPIPedido;
        this.descontoProduto = descontoProduto;
        this.acrescimoProduto = acrescimoProduto;
        this.assuntoOrigem = assuntoOrigem;
        this.assuntoCod = assuntoCod;
        this.GOCod = GOCod;
        this.SubGOCod = SubGOCod;
        this.valorAcrescimosRat = valorAcrescimosRat;
        this.valorDescontosRat = valorDescontosRat;
        this.precoUnitario = precoUnitario;
    }
    @Id
    @NotNull
    @ManyToOne(targetEntity = DocumentoEntrada.class)
    @JoinColumn(name = "Ent_Numero", nullable = false)
    private DocumentoEntrada documentoEntrada;
    /*
     */
    @Id
    @NotNull
    @Column(name = "EntItem_Numero", nullable = false)
    private Integer itemNumero;
    /*
     */
    @Column(name = "EntItem_Item", nullable = false)
    private String itemItem;
    /*
     */
    @OneToOne(targetEntity = PedidoItem.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "Pedido_Numero", referencedColumnName = "Pedido_Numero"),
        @JoinColumn(name = "PedItem_Numero", referencedColumnName = "PedItem_Numero")
    })
    private PedidoItem pedidoItem;
    /*
     */
    @ManyToOne(targetEntity = SolicitacaoCompraItem.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "Solic_Numero", referencedColumnName = "Solic_Numero"),
        @JoinColumn(name = "SolicItem_Numero", referencedColumnName = "SolicItem_Numero")
    })
    private SolicitacaoCompraItem solicitacaoCompraItem;
    /*
     */
    @Column(name = "Insumo_Cod")
    private Long insumoCod;
    /*
     */
    @ManyToOne(targetEntity = Insumo.class)
    @JoinColumn(name = "Insumo_Cod", insertable = false, updatable = false)
    private Insumo insumo;
    /*
     * Quantidade do produto entregue.
     */
    @Column(name = "EntItem_Quantidade", nullable = false)
    private Float quantidade;
    /*
     */
    @Column(name = "EntItem_PrecoUnitario", nullable = false)
    private Float precoUnitario;
    /*
     * Total Produto. Preço Unitário Entrege x Quantidade Entregue.
     */
    @Column(name = "EntItem_TotalItem", nullable = false)
    private Float totalItem;
    /*
     */
    @Column(name = "EntItem_AliqIPI", nullable = false)
    private Float aliqIPI;
    /*
     */
    @Column(name = "EntItem_ValorIPI", nullable = false)
    private Float valorIPI;
    /*
     */
    @Column(name = "EntItem_ValorAcrescimos_Rat", nullable = false)
    private Float valorAcrescimosRat;
    /*
     */
    @Column(name = "EntItem_ValorDescontos_Rat", nullable = false)
    private Float valorDescontosRat;
    /*
     */
    @Column(name = "Assunto_Origem", nullable = false)
    private String assuntoOrigem;
    /*
     */
    @Column(name = "Assunto_Cod", nullable = false)
    private String assuntoCod;
    /*
     */
    @Column(name = "GO_Cod", nullable = false)
    private String GOCod;
    /*
     */
    @Column(name = "SubGO_Cod", nullable = false)
    private String SubGOCod;
    /*
     */
    @Column(name = "EntItem_Especificacao")
    private String especificacao;
    /*
     */
    @Column(name = "EntItem_Unidade")
    private String unidade;
    /*
     */
    @Column(name = "EntItem_TotalItemcomIPI")
    private Float totalItemComIPI;
    /*
     */
    @Column(name = "EntItem_Frete_Rat")
    private Float freteRat;
    /*
     */
    @Column(name = "EntItem_PrecoFrete")
    private Float precoFrete;
    /*
     */
    @Column(name = "EntItem_QuantidadeRecebida")
    private Float quantidadeRecebida;
    /*
     */
    @Column(name = "BFF_Documento")
    private String documentoBFF;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BFF_DataDoc")
    private Date dataDocBFF;
    /*
     * Preço unitário do produto entregue.
     */
    @Column(name = "EntItem_PrecoUnitario_E")
    private Float precoUnitarioE;
    /*
     * Sub-Total. Não inclui IPI e Despesas.
     */
    @Column(name = "EntItem_TotalItem_E")
    private Float totalItemE;
    /*
     */
    @Column(name = "EntItem_AliqIPI_E")
    private Float aliqIPIE;
    /*
     */
    @Column(name = "EntItem_ValorIPI_E")
    private Float valorIPIE;
    /*
     * Total do item entrege.
     */
    @Column(name = "EntItem_TotalItemcomIPI_E")
    private Float totalItemComIPIE;
    /*
     */
    @Column(name = "EntItem_ValorDivergencia")
    private Float valorDivergencia;
    /*
     */
    @Column(name = "EntItem_DivergenciaFrete")
    private Float divergenciaFrete;
    /*
     */
    @Column(name = "Entrada_Numero")
    private Integer entradaNumero;
    /*
     */
    @Column(name = "EntradaItem_Numero")
    private Integer entradaItemNumero;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "EntItem_AplicacaoDireta")
    private EnumsGeral aplicacaoDireta;
    /*
     */
    @Column(name = "SRem_Ent_Numero")
    private Integer sRemEntNumero;
    /*
     */
    @Column(name = "EntItem_TotalItemRp")
    private Float totalItemRp;
    /*
     */
    @Column(name = "EntItem_TotalItem_BaseRateio")
    private Float totalItemBaseRateio;
    /*
     */
    @Column(name = "EntItem_PrecoUnitario_Ped")
    private Float precoUnitarioPedido;
    /*
     */
    @Column(name = "EntItem_TotalItem_Ped")
    private Float totalItemPedido;
    /*
     */
    @Column(name = "EntItem_AliqIPI_Ped")
    private Float aliqIPIPedido;
    /*
     */
    @Column(name = "EntItem_ValorIPI_Ped")
    private Float valorIPIPedido;
    /*
     */
    @Column(name = "EntItem_TotalItemcomIPI_Ped")
    private Float totalItemComIPIPedido;
    /*
     */
    @Column(name = "EntItem_Divergencia_Rat")
    private Float divergenciaRateio;
    /*
     */
    @Column(name = "EntItem_DivergenciaCTB_Rat")
    private Float divergenciaCTBRateio;
    /*
     */
    @Column(name = "EntItem_TotalItem_Final")
    private Float totalItemFinal;
    /*
     */
    @Column(name = "EntItem_PrecoUnitario_Final")
    private Float precoUnitarioFinal;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "EntItem_ZeraPrevisao")
    private EnumsGeral zeraPrevisao;
    /*
     */
    @Column(name = "EntItem_DescontoProduto")
    private Float descontoProduto;
    /*
     */
    @Column(name = "EntItem_AcrescimoProduto")
    private Float acrescimoProduto;
    /*
     */
    @Column(name = "EntItem_TotalItemProduto")
    private Float totalItemProduto;
    /*
     */
    @Column(name = "EntItem_MedReajuste_Rat")
    private Float medReajusteRateio;
    /*
     */
    @Column(name = "EntItem_MaterialFornecido_Rat")
    private Float materialFornecidoRateio;
    /*
     */
    @Column(name = "EntItem_MedReajuste_Rat_A")
    private Float medReajusteRateioA;
    /*
     */
    @Column(name = "EntItem_MatFornecido_Rat_A")
    private Float materialFornecidoRateioA;
    /*
     */
    @Column(name = "EntItem_MedDesconto_Rat")
    private Float medDescontoRateio;
    /*
     */
    @Column(name = "EntItem_MedRetencao_Rat")
    private Float medRetencaoRateio;
    /*
     */
    @Column(name = "EntItem_MedDesconto_Rat_A")
    private Float medDescontoRateioA;
    /*
     */
    @Column(name = "EntItem_MedRetencao_Rat_A")
    private Float medRetencaoRateioA;
    /*
     */
    @Column(name = "EntItem_TotalItemProduto_C")
    private Float totalItemProdutoC;
    /*
     */
    @Column(name = "EntItem_TotalItemProduto_A")
    private Float totalItemProdutoA;
    /*
     */
    @Column(name = "EntItem_TotalItemProdutoDig")
    private Float totalItemProdutoDig;
    /*
     */
    @Column(name = "CExecMed_Numero")
    private Integer cExecMedNumero;

    public Float getSaldoAReceber() {
        return pedidoItem.getQuantidade() - quantidadeRecebida;
    }

    /**
     * Calcula o Valor do IPI (Entregue).
     *
     * @param total
     * @param aliq
     */
    public void calcValorIPI(Float total, Float aliq) {
        if (aliq != null && total != null) {
            valorIPIE = (aliq * total) / 100;
        }
    }

    /**
     * Calcula a Aliquota do IPI (Entregue).
     *
     * @param total
     * @param valor
     */
    public void calcAliqIPIE(Float total, Float valor) {
        if (valor != null && total != null) {
            aliqIPIE = (valor / total) * 100;
        }
    }

    public DocumentoEntrada getDocumentoEntrada() {
        return documentoEntrada;
    }

    public void setDocumentoEntrada(DocumentoEntrada documentoEntrada) {
        this.documentoEntrada = documentoEntrada;
    }

    public Integer getItemNumero() {
        return itemNumero;
    }

    public void setItemNumero(Integer itemNumero) {
        this.itemNumero = itemNumero;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public PedidoItem getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

    public SolicitacaoCompraItem getSolicitacaoCompraItem() {
        return solicitacaoCompraItem;
    }

    public void setSolicitacaoCompraItem(SolicitacaoCompraItem solicitacaoCompraItem) {
        this.solicitacaoCompraItem = solicitacaoCompraItem;
    }

    public Long getInsumoCod() {
        return insumoCod;
    }

    public void setInsumoCod(Long insumoCod) {
        this.insumoCod = insumoCod;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * @return Total do Produto (Entregue)
     */
    public Float getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Float totalItem) {
        this.totalItem = totalItem;
    }

    public Float getAliqIPI() {
        return aliqIPI;
    }

    public void setAliqIPI(Float aliqIPI) {
        this.aliqIPI = aliqIPI;
    }

    public Float getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(Float valorIPI) {
        this.valorIPI = valorIPI;
    }

    public Float getValorAcrescimosRat() {
        return valorAcrescimosRat;
    }

    public void setValorAcrescimosRat(Float valorAcrescimosRat) {
        this.valorAcrescimosRat = valorAcrescimosRat;
    }

    public Float getValorDescontosRat() {
        return valorDescontosRat;
    }

    public void setValorDescontosRat(Float valorDescontosRat) {
        this.valorDescontosRat = valorDescontosRat;
    }

    public String getAssuntoOrigem() {
        return assuntoOrigem;
    }

    public void setAssuntoOrigem(String assuntoOrigem) {
        this.assuntoOrigem = assuntoOrigem;
    }

    public String getAssuntoCod() {
        return assuntoCod;
    }

    public void setAssuntoCod(String assuntoCod) {
        this.assuntoCod = assuntoCod;
    }

    public String getGOCod() {
        return GOCod;
    }

    public void setGOCod(String GOCod) {
        this.GOCod = GOCod;
    }

    public String getSubGOCod() {
        return SubGOCod;
    }

    public void setSubGOCod(String SubGOCod) {
        this.SubGOCod = SubGOCod;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Float getTotalItemComIPI() {
        return totalItemComIPI;
    }

    public void setTotalItemComIPI(Float totalItemComIPI) {
        this.totalItemComIPI = totalItemComIPI;
    }

    public Float getFreteRat() {
        return freteRat;
    }

    public void setFreteRat(Float freteRat) {
        this.freteRat = freteRat;
    }

    public Float getPrecoFrete() {
        return precoFrete;
    }

    public void setPrecoFrete(Float precoFrete) {
        this.precoFrete = precoFrete;
    }

    public Float getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public void setQuantidadeRecebida(Float quantidadeRecebida) {
        this.quantidadeRecebida = quantidadeRecebida;
    }

    public String getDocumentoBFF() {
        return documentoBFF;
    }

    public void setDocumentoBFF(String documentoBFF) {
        this.documentoBFF = documentoBFF;
    }

    public Date getDataDocBFF() {
        return dataDocBFF;
    }

    public void setDataDocBFF(Date dataDocBFF) {
        this.dataDocBFF = dataDocBFF;
    }

    public Float getPrecoUnitarioE() {
        if (totalItem == 0 || quantidade == 0) {
            precoUnitarioE = 0F;
        } else if (totalItem != null && quantidade != null) {
            precoUnitarioE = totalItem / quantidade;
        }
        return precoUnitarioE;
    }

    public void setPrecoUnitarioE(Float precoUnitarioE) {
        this.precoUnitarioE = precoUnitarioE;
    }

    /**
     * @return Sub-Total (Entregue)
     */
    public Float getTotalItemE() {
        if (totalItem != null && descontoProduto != null) {
            totalItemE = totalItem - descontoProduto;
            totalItemE = NumberUtils.arredondarHalfUp(totalItemE);
        }
        return totalItemE;
    }

    public void setTotalItemE(Float totalItemE) {
        this.totalItemE = totalItemE;
    }

    /**
     * Pega o percentual do IPI do valor do IPI em cima do Sub-Total do valor.
     *
     * @return % IPI (Entregue)
     */
    public Float getAliqIPIE() {
        return aliqIPIE;
    }

    public void setAliqIPIE(Float aliqIPIE) {
        this.aliqIPIE = aliqIPIE;
    }

    /**
     * Pega o valor do IPI aplicando o percentual em cima do Sub-Total.
     *
     * @return
     */
    public Float getValorIPIE() {
        return valorIPIE;
    }

    public void setValorIPIE(Float valorIPIE) {
        this.valorIPIE = valorIPIE;
    }

    /**
     * Valor total do item entregue. Calculado automaticamente de acordo com o
     * sub-total, valor IPI e acrescimos.
     *
     * @return Total do Item Entregue
     */
    public Float getTotalItemComIPIE() {
        if (totalItemE != null && valorIPIE != null && acrescimoProduto != null) {
            totalItemComIPIE = totalItemE + valorIPIE + acrescimoProduto;
        }
        if (totalItemComIPIE == null) {
            totalItemComIPIE = 0F;
        }
        return totalItemComIPIE;
    }

    public void setTotalItemComIPIE(Float totalItemComIPIE) {
        this.totalItemComIPIE = totalItemComIPIE;
    }

    public Float getValorDivergencia() {
        return valorDivergencia;
    }

    public void setValorDivergencia(Float valorDivergencia) {
        this.valorDivergencia = valorDivergencia;
    }

    public Float getDivergenciaFrete() {
        return divergenciaFrete;
    }

    public void setDivergenciaFrete(Float divergenciaFrete) {
        this.divergenciaFrete = divergenciaFrete;
    }

    public Integer getEntradaNumero() {
        return entradaNumero;
    }

    public void setEntradaNumero(Integer entradaNumero) {
        this.entradaNumero = entradaNumero;
    }

    public Integer getEntradaItemNumero() {
        return entradaItemNumero;
    }

    public void setEntradaItemNumero(Integer entradaItemNumero) {
        this.entradaItemNumero = entradaItemNumero;
    }

    public EnumsGeral getAplicacaoDireta() {
        return aplicacaoDireta;
    }

    public void setAplicacaoDireta(EnumsGeral aplicacaoDireta) {
        this.aplicacaoDireta = aplicacaoDireta;
    }

    public Integer getsRemEntNumero() {
        return sRemEntNumero;
    }

    public void setsRemEntNumero(Integer sRemEntNumero) {
        this.sRemEntNumero = sRemEntNumero;
    }

    public Float getTotalItemRp() {
        return totalItemRp;
    }

    public void setTotalItemRp(Float totalItemRp) {
        this.totalItemRp = totalItemRp;
    }

    public Float getTotalItemBaseRateio() {
        return totalItemBaseRateio;
    }

    public void setTotalItemBaseRateio(Float totalItemBaseRateio) {
        this.totalItemBaseRateio = totalItemBaseRateio;
    }

    public Float getPrecoUnitarioPedido() {
        return precoUnitarioPedido;
    }

    public void setPrecoUnitarioPedido(Float precoUnitarioPedido) {
        this.precoUnitarioPedido = precoUnitarioPedido;
    }

    /**
     * Total do Produto Recebido calculado pela quantidadeRecebida x
     * precoUnitarioPedido.
     *
     * @return Total do Produto.
     */
    public Float getTotalItemPedido() {
        if (quantidadeRecebida != null && precoUnitarioPedido != null) {
            totalItemPedido = quantidadeRecebida * precoUnitarioPedido;
            totalItemPedido = NumberUtils.arredondarHalfUp(totalItemPedido);
        }
        return totalItemPedido;
    }

    public void setTotalItemPedido(Float totalItemPedido) {
        this.totalItemPedido = totalItemPedido;
    }

    public Float getAliqIPIPedido() {
        return aliqIPIPedido;
    }

    public void setAliqIPIPedido(Float aliqIPIPedido) {
        this.aliqIPIPedido = aliqIPIPedido;
    }

    public Float getValorIPIPedido() {
        return valorIPIPedido;
    }

    public void setValorIPIPedido(Float valorIPIPedido) {
        this.valorIPIPedido = valorIPIPedido;
    }

    /**
     * Calcula o total final do item recebido com o valor do IPI.
     *
     * @return Total do Item.
     */
    public Float getTotalItemComIPIPedido() {
        if (quantidadeRecebida != null && precoUnitarioPedido != null && valorIPIPedido != null) {
            totalItemComIPIPedido = (quantidadeRecebida * precoUnitarioPedido) + valorIPIPedido;
            totalItemComIPIPedido = NumberUtils.arredondarHalfUp(totalItemComIPIPedido);
        }
        return totalItemComIPIPedido;
    }

    public void setTotalItemComIPIPedido(Float totalItemComIPIPedido) {
        this.totalItemComIPIPedido = totalItemComIPIPedido;
    }

    public Float getDivergenciaRateio() {
        return divergenciaRateio;
    }

    public void setDivergenciaRateio(Float divergenciaRateio) {
        this.divergenciaRateio = divergenciaRateio;
    }

    public Float getDivergenciaCTBRateio() {
        return divergenciaCTBRateio;
    }

    public void setDivergenciaCTBRateio(Float divergenciaCTBRateio) {
        this.divergenciaCTBRateio = divergenciaCTBRateio;
    }

    public Float getTotalItemFinal() {
        return totalItemFinal;
    }

    public void setTotalItemFinal(Float totalItemFinal) {
        this.totalItemFinal = totalItemFinal;
    }

    public Float getPrecoUnitarioFinal() {
        return precoUnitarioFinal;
    }

    public void setPrecoUnitarioFinal(Float precoUnitarioFinal) {
        this.precoUnitarioFinal = precoUnitarioFinal;
    }

    public EnumsGeral getZeraPrevisao() {
        return zeraPrevisao;
    }

    public void setZeraPrevisao(EnumsGeral zeraPrevisao) {
        this.zeraPrevisao = zeraPrevisao;
    }

    public Float getDescontoProduto() {
        return descontoProduto;
    }

    public void setDescontoProduto(Float descontoProduto) {
        this.descontoProduto = descontoProduto;
    }

    public Float getAcrescimoProduto() {
        return acrescimoProduto;
    }

    public void setAcrescimoProduto(Float acrescimoProduto) {
        this.acrescimoProduto = acrescimoProduto;
    }

    public Float getTotalItemProduto() {
        return totalItemProduto;
    }

    public void setTotalItemProduto(Float totalItemProduto) {
        this.totalItemProduto = totalItemProduto;
    }

    public Float getMedReajusteRateio() {
        return medReajusteRateio;
    }

    public void setMedReajusteRateio(Float medReajusteRateio) {
        this.medReajusteRateio = medReajusteRateio;
    }

    public Float getMaterialFornecidoRateio() {
        return materialFornecidoRateio;
    }

    public void setMaterialFornecidoRateio(Float materialFornecidoRateio) {
        this.materialFornecidoRateio = materialFornecidoRateio;
    }

    public Float getMedReajusteRateioA() {
        return medReajusteRateioA;
    }

    public void setMedReajusteRateioA(Float medReajusteRateioA) {
        this.medReajusteRateioA = medReajusteRateioA;
    }

    public Float getMaterialFornecidoRateioA() {
        return materialFornecidoRateioA;
    }

    public void setMaterialFornecidoRateioA(Float materialFornecidoRateioA) {
        this.materialFornecidoRateioA = materialFornecidoRateioA;
    }

    public Float getMedDescontoRateio() {
        return medDescontoRateio;
    }

    public void setMedDescontoRateio(Float medDescontoRateio) {
        this.medDescontoRateio = medDescontoRateio;
    }

    public Float getMedRetencaoRateio() {
        return medRetencaoRateio;
    }

    public void setMedRetencaoRateio(Float medRetencaoRateio) {
        this.medRetencaoRateio = medRetencaoRateio;
    }

    public Float getMedDescontoRateioA() {
        return medDescontoRateioA;
    }

    public void setMedDescontoRateioA(Float medDescontoRateioA) {
        this.medDescontoRateioA = medDescontoRateioA;
    }

    public Float getMedRetencaoRateioA() {
        return medRetencaoRateioA;
    }

    public void setMedRetencaoRateioA(Float medRetencaoRateioA) {
        this.medRetencaoRateioA = medRetencaoRateioA;
    }

    public Float getTotalItemProdutoC() {
        return totalItemProdutoC;
    }

    public void setTotalItemProdutoC(Float totalItemProdutoC) {
        this.totalItemProdutoC = totalItemProdutoC;
    }

    public Float getTotalItemProdutoA() {
        return totalItemProdutoA;
    }

    public void setTotalItemProdutoA(Float totalItemProdutoA) {
        this.totalItemProdutoA = totalItemProdutoA;
    }

    public Float getTotalItemProdutoDig() {
        return totalItemProdutoDig;
    }

    public void setTotalItemProdutoDig(Float totalItemProdutoDig) {
        this.totalItemProdutoDig = totalItemProdutoDig;
    }

    public Integer getcExecMedNumero() {
        return cExecMedNumero;
    }

    public void setcExecMedNumero(Integer cExecMedNumero) {
        this.cExecMedNumero = cExecMedNumero;
    }

    @Override
    public Serializable getId() {
        return Long.valueOf(documentoEntrada.getNumero().toString() + itemNumero.toString());
    }

    public String getStringId() {
        return getId().toString();
    }

    @Override
    public String getLabel() {
        return itemNumero.toString();
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return false;
    }

    @Override
    public int compareTo(DocumentoEntradaItem o) {
        return this.getStringId().compareTo(o.getStringId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.documentoEntrada);
        hash = 29 * hash + Objects.hashCode(this.itemNumero);
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
        final DocumentoEntradaItem other = (DocumentoEntradaItem) obj;
        if (!Objects.equals(this.documentoEntrada, other.documentoEntrada)) {
            return false;
        }
        if (!Objects.equals(this.itemNumero, other.itemNumero)) {
            return false;
        }
        return true;
    }
}
