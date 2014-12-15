/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.ar;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.geral.NaturezaFinanceira;
import br.com.grupopibb.portalobra.model.geral.NaturezaFiscal;
import br.com.grupopibb.portalobra.model.pagamento.TituloAPagar;
import br.com.grupopibb.portalobra.model.pedido.Pedido;
import br.com.grupopibb.portalobra.model.tipos.EnumCTR;
import br.com.grupopibb.portalobra.model.tipos.EnumRecolhimentoImposto;
import br.com.grupopibb.portalobra.model.tipos.EnumTipoEntrada;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Entity 
@Table(name = "Documento_de_Entrada")
@NamedQueries({
    @NamedQuery(name = "DocumentoEntrada.findParam",
            query = " SELECT DISTINCT d FROM DocumentoEntrada d "
            + " WHERE (:dataInicial2 = 'todos' OR :dataFinal2 = 'todos' OR d.avisoRecebimento.data BETWEEN :dataInicial AND :dataFinal) "
            + " AND (:numeroAr2 = 'todos' OR d.avisoRecebimento.numero = :numeroAr) "
            + " AND (:docTipo2 = 'todos' OR d.docTipo = :docTipo) "
            + " AND (:docNumero2 = 'todos' OR d.docNumero = :docNumero) "
            + " AND (:credor2 = 'todos' OR d.credor = :credor) "
            //       + " AND (:pedidos2 = 'todos' OR d.pedido.numero in :pedidos) "
            + " ORDER BY d.avisoRecebimento.data DESC"),
    @NamedQuery(name = "DocumentoEntrada.countParam",
            query = " SELECT COUNT(DISTINCT d) FROM DocumentoEntrada d "
            + " WHERE (:dataInicial2 = 'todos' OR :dataFinal2 = 'todos' OR d.avisoRecebimento.data BETWEEN :dataInicial AND :dataFinal) "
            + " AND (:numeroAr2 = 'todos' OR d.avisoRecebimento.numero = :numeroAr) "
            + " AND (:docTipo2 = 'todos' OR d.docTipo = :docTipo) "
            + " AND (:docNumero2 = 'todos' OR d.docNumero = :docNumero) "
            + " AND (:credor2 = 'todos' OR d.credor = :credor) "),
//       + " AND (:pedidos2 = 'todos' OR d.pedido.numero in :pedidos) "),
    @NamedQuery(name = "DocumentoEntrada.countNumeroDocumento",
            query = " SELECT COUNT(DISTINCT d) FROM DocumentoEntrada d "
            + " WHERE (d.numeroDocumento = :numeroDocumento) ")
})
public class DocumentoEntrada implements EntityInterface<DocumentoEntrada> {

    public DocumentoEntrada() {
    }

    /**
     * Cosntrutor com valores default para criação de novo DocumentoEntrada no
     * formulário.
     *
     * @param avisoRecebimento
     * @param docValor
     * @param valorICMS
     * @param valorIPI
     * @param valorAcrescimos
     * @param valorDescontos
     * @param valorFinanciadoBens
     * @param valorAClassificar
     * @param descontoProduto
     * @param despesasAcessorias
     */
    public DocumentoEntrada(Long numero, AvisoRecebimento avisoRecebimento, Float docValor, Float valorICMS, Float valorIPI, Float valorAcrescimos, Float valorDescontos, Float valorFinanciadoBens, Float valorAClassificar, Float descontoProduto, Float despesasAcessorias) {
        this.numero = numero;
        this.avisoRecebimento = avisoRecebimento;
        this.docValor = docValor;
        this.valorICMS = valorICMS;
        this.valorIPI = valorIPI;
        this.valorAcrescimos = valorAcrescimos;
        this.valorDescontos = valorDescontos;
        this.valorFinanciadoBens = valorFinanciadoBens;
        this.valorAClassificar = valorAClassificar;
        this.descontoProduto = descontoProduto;
        this.despesasAcessorias = despesasAcessorias;
    }
    @Id
    @NotNull
    @Column(name = "Ent_Numero")
    private Long numero;
    /*
     */
    @Column(name = "Ent_Item")
    private String item;
    /*
     */
    @OneToOne(targetEntity = Pedido.class, mappedBy = "documentoEntrada", fetch = FetchType.EAGER)
    private Pedido pedido;
    /*
     */
    @OneToMany(targetEntity = DocumentoEntradaItem.class, mappedBy = "documentoEntrada", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocumentoEntradaItem> itens;
    /*
     */
    @OneToMany(targetEntity = DocumentoEntradaAvaliacao.class, mappedBy="documentoEntrada", fetch = FetchType.LAZY)
    private List<DocumentoEntradaAvaliacao> itensAvaliacao;
    /*
     */
    @OneToMany(targetEntity = TituloAPagar.class, mappedBy = "documentoEntrada", fetch = FetchType.LAZY)
    private List<TituloAPagar> titulosAPagar;
    /*
     */
    @ManyToOne(targetEntity = AvisoRecebimento.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "AR_Numero")
    private AvisoRecebimento avisoRecebimento;
    /*
     */
    @OneToOne(targetEntity = Credor.class)
    @JoinColumn(name = "Cre_Cod", nullable = false)
    private Credor credor;
    /*
     */
    @OneToOne(targetEntity = DocumentoEntradaTipo.class)
    @JoinColumn(name = "EntDocTipo_Cod")
    private DocumentoEntradaTipo docTipo;
    /*
     */
    @Column(name = "Ent_DocNumero", nullable = false)
    private String docNumero;
    /*
     */
    @Column(name = "Ent_DocSerie")
    private String docSerie;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Ent_DocData", nullable = false)
    private Date dataEmissao;
    /*
     */
    @Column(name = "Ent_DocUFOrigem")
    private String docUFOrigem;
    /*
     */
    @Column(name = "Ent_DocValor", nullable = false)
    private Float docValor;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Ent_DataEntrada", nullable = false)
    private Date dataEntrada;
    /*
     */
    @OneToOne(targetEntity = NaturezaFiscal.class)
    @JoinColumn(name = "Nop_Cod")
    private NaturezaFiscal naturezaFiscal;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_DocCTR")
    private EnumCTR transporteCTR;
    /*
     */
    @Column(name = "Ent_BaseICMS")
    private Float baseICMS;
    /*
     */
    @Column(name = "Ent_ValorICMS")
    private Float valorICMS;
    /*
     */
    @Column(name = "Ent_BaseICMSSubs")
    private Float baseICMSSubs;
    /*
     */
    @Column(name = "Ent_ValorICMSSubs")
    private Float valorICMSSubs;
    /*
     */
    @Column(name = "Ent_BasePercISS")
    private Float basePercISS;
    /*
     */
    @Column(name = "Ent_BaseISS")
    private Float baseISS;
    /*
     */
    @Column(name = "Ent_AliqISS")
    private Float aliqISS;
    /*
     */
    @Column(name = "Ent_ValorISS")
    private Float valorISS;
    /*
     */
    @Column(name = "Ent_ValorIPI")
    private Float valorIPI;
    /*
     */
    @Column(name = "Ent_BasePercINSS")
    private Float basePercINSS;
    /*
     */
    @Column(name = "Ent_BaseINSS")
    private Float baseINSS;
    /*
     */
    @Column(name = "Ent_AliqINSS")
    private Float aliqINSS;
    /*
     */
    @Column(name = "Ent_CalcINSS")
    private Float calcINSS;
    /*
     */
    @Column(name = "Ent_MinINSS")
    private Float minINSS;
    /*
     */
    @Column(name = "Ent_ValorINSS")
    private Float valorINSS;
    /*
     */
    @Column(name = "Ent_BasePercIRRF")
    private Float basePercIRRF;
    /*
     */
    @Column(name = "Ent_BaseIRRF")
    private Float baseIRRF;
    /*
     */
    @Column(name = "Ent_AliqIRRF")
    private Float aliqIRRF;
    /*
     */
    @Column(name = "Ent_CalcIRRF")
    private Float calcIRRF;
    /*
     */
    @Column(name = "Ent_MinIRRF")
    private Float minIRRF;
    /*
     */
    @Column(name = "Ent_ValorIRRF")
    private Float valorIRRF;
    /*
     */
    @Column(name = "Ent_ValorAcrescimos")
    private Float valorAcrescimos;
    /*
     */
    @Column(name = "Ent_ValorRetencao")
    private Float valorRetencao;
    /*
     */
    @Column(name = "Ent_ValorDescontos")
    private Float valorDescontos;
    /*
     */
    @OneToOne(targetEntity = CentroCusto.class)
    @JoinColumns({
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod", nullable = false),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod", nullable = false),
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod", nullable = false)
    })
    private CentroCusto centro;
    /*
     */
    @Column(name = "Ent_TipoTransporte")
    @Enumerated(EnumType.STRING)
    private EnumsGeral tipoTransporte;
    /*
     */
    @Column(name = "Ent_Numero_ConhecimentoFrete")
    private Integer numeroConhecimentoFrete;
    /*
     */
    @Column(name = "Ent_ValorLetraPatrimonial")
    private Float valorFinanciadoBens;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_ISS_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoISS;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_INSS_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoINSS;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_IRRF_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoIRRF;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_Tipo")
    private EnumTipoEntrada tipoEntrada;
    /*
     */
    @Column(name = "Ficha_Numero")
    private String fichaNumero;
    /*
     */
    @OneToOne(targetEntity = NaturezaFinanceira.class)
    @JoinColumn(name = "MovFinNatureza_Cod")
    private NaturezaFinanceira naturezaFinanceira;
    /*
     */
    @Column(name = "Ent_ValorDivergencia")
    private Float valorDivergencia;
    /*
     */
    @Column(name = "Entrada_Numero")
    private Integer entradaNumero;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_OperacaoEntreNucleos")
    private EnumsGeral operacaoEntreNucleos;
    /*
     */
    @Column(name = "Ent_DocValorRp")
    private Float valorAClassificar;
    /*
     */
    @Column(name = "Ent_DocValor_BaseRateio")
    private Float docValorBaseRateio;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_ParcelasAlteradas")
    private EnumsGeral parcelasAlteradas;
    /*
     */
    @Column(name = "Ent_ValorDivergenciaCTB")
    private Float valorDivergenciaCTB;
    /*
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "Ent_Observacao")
    private String observacao;
    /*
     */
    @Column(name = "Ent_BasePercPIS")
    private Float basePercPIS;
    /*
     */
    @Column(name = "Ent_BasePIS")
    private Float basePIS;
    /*
     */
    @Column(name = "Ent_AliqPIS")
    private Float aliqPIS;
    /*
     */
    @Column(name = "Ent_CalcPIS")
    private Float calcPIS;
    /*
     */
    @Column(name = "Ent_MinPIS")
    private Float minPIS;
    /*
     */
    @Column(name = "Ent_ValorPIS")
    private Float valorPIS;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_PIS_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoPIS;
    /*
     */
    @Column(name = "Ent_BasePercCOFINS")
    private Float basePercCOFINS;
    /*
     */
    @Column(name = "Ent_BaseCOFINS")
    private Float baseCOFINS;
    /*
     */
    @Column(name = "Ent_AliqCOFINS")
    private Float aliqCOFINS;
    /*
     */
    @Column(name = "Ent_CalcCOFINS")
    private Float calcCOFINS;
    /*
     */
    @Column(name = "Ent_MinCOFINS")
    private Float minCOFINS;
    /*
     */
    @Column(name = "Ent_ValorCOFINS")
    private Float valorCOFINS;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_COFINS_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoCOFINS;
    /*
     */
    @Column(name = "Ent_BasePercCSSL")
    private Float basePercCSSL;
    /*
     */
    @Column(name = "Ent_BaseCSSL")
    private Float baseCSSL;
    /*
     */
    @Column(name = "Ent_AliqCSSL")
    private Float aliqCSSL;
    /*
     */
    @Column(name = "Ent_CalcCSSL")
    private Float calcCSSL;
    /*
     */
    @Column(name = "Ent_MinCSSL")
    private Float minCSSL;
    /*
     */
    @Column(name = "Ent_ValorCSSL")
    private Float valorCSSL;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_CSSL_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoCSSL;
    /*
     */
    @Column(name = "Ent_DescontoProduto")
    private Float descontoProduto;
    /**
     * O mesmo que acrescimo do produto
     */
    @Column(name = "Ent_AcrescimoProduto")
    private Float despesasAcessorias;
    /*
     */
    @Column(name = "Ent_MedicaoRetencao")
    private Float medicaoRetencao;
    /*
     */
    @Column(name = "Ent_MedicaoRetencaoSobre")
    private String medicaoRetencaoSobre;
    /*
     */
    @Column(name = "Ent_MedicaoDesconto")
    private Float medicaoDesconto;
    /*
     */
    @Column(name = "Ent_MedicaoReajuste")
    private Float medicaoReajuste;
    /*
     */
    @Column(name = "Ent_Numero_ISS")
    private Integer numeroISS;
    /*
     */
    @Column(name = "Ent_Numero_INSS")
    private Integer numeroINSS;
    /*
     */
    @Column(name = "Ent_Numero_PIS")
    private Integer numeroPIS;
    /*
     */
    @Column(name = "Ent_Numero_COFINS")
    private Integer numeroCOFINS;
    /*
     */
    @Column(name = "Ent_Numero_CSLL")
    private Integer numeroCSLL;
    /*
     */
    @Column(name = "Ent_Numero_IRRF")
    private Integer numeroIRRF;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_Conforme")
    private EnumsGeral conforme;
    /*
     */
    @Column(name = "Ent_PontuacaoAvaliacao")
    private Float pontuacaoAvaliacao;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Ent_DataRegistro")
    private Date dataRegistro;
    /*
     */
    @Column(name = "Ent_ZDifAliq")
    private String zDifAliq;
    /*
     */
    @Column(name = "Cre_Cod_Ped")
    private String credorCodPedido;
    /*
     */
    @Column(name = "Ent_MaterialFornecido")
    private Float materialFornecido;
    /*
     */
    @Column(name = "Ent_MaterialFornecido_C")
    private Float materialFornecidoC;
    /*
     */
    @Column(name = "Ent_MaterialFornecido_A")
    private Float materialFornecidoA;
    /*
     */
    @Column(name = "Ent_MedicaoReajuste_C")
    private Float medicaoReajusteC;
    /*
     */
    @Column(name = "Ent_MedicaoReajuste_A")
    private Float medicaoReajusteA;
    /*
     */
    @Column(name = "Ent_MedicaoDesconto_C")
    private Float medicaoDescontoC;
    /*
     */
    @Column(name = "Ent_MedicaoDesconto_A")
    private Float medicaoDescontoA;
    /*
     */
    @Column(name = "Ent_MedicaoRetencao_C")
    private Float medicaoRetencaoC;
    /*
     */
    @Column(name = "Ent_MedicaoRetencao_A")
    private Float medicaoRetencaoA;
    /*
     */
    @Column(name = "Classificacao_IRRF")
    private String classificacaoIRRF;
    /*
     */
    @Column(name = "Usuario_Instrucao")
    private String usuarioAutorizacao;
    /*
     */
    @Column(name = "Instrucao_Cod")
    private String situacao;
    /*
     */
    @Column(name = "Instrucao_Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAutorizacao;
    /*
     */
    @Column(name = "Ent_DocNumero_NUM")
    private Integer numeroDocumento;
    /*
     */
    @Column(name = "Ent_DocNumero_COMPL")
    private String complementoDocumento;
    /*
     */
    @Column(name = "DocDig_Numero")
    private Integer docDigNumero;
    /*
     */
    @Column(name = "Ent_CFOP")
    private Integer CFOP;
    /*
     */
    @Column(name = "SRem_POST_ANT")
    private String sRemPostAnt;
    /*
     */
    @Column(name = "Ent_AliqSS")
    private Float aliqSS;
    /**
     * SEST/SENAT
     */
    @Column(name = "Ent_BasePercSS")
    private Float basePercSS;
    /**
     * SEST/SENAT
     */
    @Column(name = "Ent_BaseSS")
    private Float baseSS;
    /**
     * SEST/SENAT
     */
    @Column(name = "Ent_ValorSS")
    private Float valorSS;
    /**
     * SEST/SENAT
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Ent_SS_Recolhimento")
    private EnumRecolhimentoImposto recolhimentoSS;
    /**
     * SEST/SENAT
     */
    @Column(name = "Ent_Numero_SS")
    private Integer numeroSS;

    /**
     * Calcula a diferença de dias entre a data de emissão da nf e a data de
     * entrada do material na obra. Se a data de entrada for nula, calcula a
     * diferença até a data atual.
     *
     * @return atraso na entrada do material
     */
    public Integer getAtrasoEntrada() {
        if (dataEntrada == null) {
            dataEntrada = new Date();
        }
        if (dataEmissao.getTime() > dataEntrada.getTime()) {
            return 0;
        }
        return new Long(DateUtils.daysBetween(dataEmissao, dataEntrada)).intValue();
    }

    /**
     * Calcula a diferença entre a data de entrada do material e a data que foi
     * registrado o AR. Se a data de registro for nula (operacionalmente não
     * acontece), é calculada a diferença com a data atual.
     *
     * @return atraso no registro do AR
     */
    public Integer getAtrasoRegistroAR() {
        Date dataRegistroAR = avisoRecebimento.getData();
        if (dataRegistroAR == null) {
            dataRegistroAR = new Date();
        }
        if (dataEntrada.getTime() > dataRegistroAR.getTime()) {
            return 0;
        }
        return new Long(DateUtils.daysBetween(dataEntrada, dataRegistroAR)).intValue();
    }

    public String getSituacaoFormatada() {
        switch (situacao) {
            case "A":
                return "Autorizado";
            case "R":
                return "Recusado";
            default:
                return "";
        }
    }

    /**
     * @return Número do item com o tipo inteiro.
     */
    public Integer getItemNum() {
        if (StringUtils.isNotBlank(item)) {
            return Integer.valueOf(item);
        } else {
            return 0;
        }
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<DocumentoEntradaItem> getItens() {
        return itens;
    }

    public void setItens(List<DocumentoEntradaItem> itens) {
        this.itens = itens;
    }

    public List<DocumentoEntradaAvaliacao> getItensAvaliacao() {
        return itensAvaliacao;
    }

    public void setItensAvaliacao(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        this.itensAvaliacao = itensAvaliacao;
    }

    public List<TituloAPagar> getTitulosAPagar() {
        return titulosAPagar;
    }

    public void setTitulosAPagar(List<TituloAPagar> titulosAPagar) {
        this.titulosAPagar = titulosAPagar;
    }

    public AvisoRecebimento getAvisoRecebimento() {
        return avisoRecebimento;
    }

    public void setAvisoRecebimento(AvisoRecebimento avisoRecebimento) {
        this.avisoRecebimento = avisoRecebimento;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public DocumentoEntradaTipo getDocTipo() {
        return docTipo;
    }

    public void setDocTipo(DocumentoEntradaTipo docTipo) {
        this.docTipo = docTipo;
    }

    public String getDocNumero() {
        return docNumero;
    }

    public void setDocNumero(String docNumero) {
        this.docNumero = docNumero;
    }

    public String getDocSerie() {
        return docSerie;
    }

    public void setDocSerie(String docSerie) {
        this.docSerie = docSerie;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDocUFOrigem() {
        return docUFOrigem;
    }

    public void setDocUFOrigem(String docUFOrigem) {
        this.docUFOrigem = docUFOrigem;
    }

    public Float getDocValor() {
        return docValor;
    }

    public void setDocValor(Float docValor) {
        this.docValor = docValor;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public NaturezaFiscal getNaturezaFiscal() {
        return naturezaFiscal;
    }

    public void setNaturezaFiscal(NaturezaFiscal naturezaFiscal) {
        this.naturezaFiscal = naturezaFiscal;
    }

    public EnumCTR getTransporteCTR() {
        return transporteCTR;
    }

    public void setTransporteCTR(EnumCTR transporteCTR) {
        this.transporteCTR = transporteCTR;
    }

    public Float getBaseICMS() {
        return baseICMS;
    }

    public void setBaseICMS(Float baseICMS) {
        this.baseICMS = baseICMS;
    }

    public Float getValorICMS() {
        return valorICMS;
    }

    public void setValorICMS(Float valorICMS) {
        this.valorICMS = valorICMS;
    }

    public Float getBaseICMSSubs() {
        return baseICMSSubs;
    }

    public void setBaseICMSSubs(Float baseICMSSubs) {
        this.baseICMSSubs = baseICMSSubs;
    }

    public Float getValorICMSSubs() {
        return valorICMSSubs;
    }

    public void setValorICMSSubs(Float valorICMSSubs) {
        this.valorICMSSubs = valorICMSSubs;
    }

    public Float getBasePercISS() {
        return basePercISS;
    }

    public void setBasePercISS(Float basePercISS) {
        this.basePercISS = basePercISS;
    }

    public Float getBaseISS() {
        return baseISS;
    }

    public void setBaseISS(Float baseISS) {
        this.baseISS = baseISS;
    }

    public Float getAliqISS() {
        return aliqISS;
    }

    public void setAliqISS(Float aliqISS) {
        this.aliqISS = aliqISS;
    }

    public Float getValorISS() {
        return valorISS;
    }

    public void setValorISS(Float valorISS) {
        this.valorISS = valorISS;
    }

    public Float getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(Float valorIPI) {
        this.valorIPI = valorIPI;
    }

    public Float getBasePercINSS() {
        return basePercINSS;
    }

    public void setBasePercINSS(Float basePercINSS) {
        this.basePercINSS = basePercINSS;
    }

    public Float getBaseINSS() {
        return baseINSS;
    }

    public void setBaseINSS(Float baseINSS) {
        this.baseINSS = baseINSS;
    }

    public Float getAliqINSS() {
        return aliqINSS;
    }

    public void setAliqINSS(Float aliqINSS) {
        this.aliqINSS = aliqINSS;
    }

    public Float getCalcINSS() {
        return calcINSS;
    }

    public void setCalcINSS(Float calcINSS) {
        this.calcINSS = calcINSS;
    }

    public Float getMinINSS() {
        return minINSS;
    }

    public void setMinINSS(Float minINSS) {
        this.minINSS = minINSS;
    }

    public Float getValorINSS() {
        return valorINSS;
    }

    public void setValorINSS(Float valorINSS) {
        this.valorINSS = valorINSS;
    }

    public Float getBasePercIRRF() {
        return basePercIRRF;
    }

    public void setBasePercIRRF(Float basePercIRRF) {
        this.basePercIRRF = basePercIRRF;
    }

    public Float getBaseIRRF() {
        return baseIRRF;
    }

    public void setBaseIRRF(Float baseIRRF) {
        this.baseIRRF = baseIRRF;
    }

    public Float getAliqIRRF() {
        return aliqIRRF;
    }

    public void setAliqIRRF(Float aliq_IRRF) {
        this.aliqIRRF = aliq_IRRF;
    }

    public Float getCalcIRRF() {
        return calcIRRF;
    }

    public void setCalcIRRF(Float calcIRRF) {
        this.calcIRRF = calcIRRF;
    }

    public Float getMinIRRF() {
        return minIRRF;
    }

    public void setMinIRRF(Float minIRRF) {
        this.minIRRF = minIRRF;
    }

    public Float getValorIRRF() {
        return valorIRRF;
    }

    public void setValorIRRF(Float valorIRRF) {
        this.valorIRRF = valorIRRF;
    }

    public Float getValorAcrescimos() {
        return valorAcrescimos;
    }

    public void setValorAcrescimos(Float valorAcrescimos) {
        this.valorAcrescimos = valorAcrescimos;
    }

    public Float getValorRetencao() {
        return valorRetencao;
    }

    public void setValorRetencao(Float valorRetencao) {
        this.valorRetencao = valorRetencao;
    }

    public Float getValorDescontos() {
        return valorDescontos;
    }

    public void setValorDescontos(Float valorDescontos) {
        this.valorDescontos = valorDescontos;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Float getValorFinanciadoBens() {
        return valorFinanciadoBens;
    }

    public void setValorFinanciadoBens(Float valorFinanciadoBens) {
        this.valorFinanciadoBens = valorFinanciadoBens;
    }

    public EnumsGeral getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(EnumsGeral tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Integer getNumeroConhecimentoFrete() {
        return numeroConhecimentoFrete;
    }

    public void setNumeroConhecimentoFrete(Integer numeroConhecimentoFrete) {
        this.numeroConhecimentoFrete = numeroConhecimentoFrete;
    }

    public EnumRecolhimentoImposto getRecolhimentoISS() {
        return recolhimentoISS;
    }

    public void setRecolhimentoISS(EnumRecolhimentoImposto recolhimentoISS) {
        this.recolhimentoISS = recolhimentoISS;
    }

    public EnumRecolhimentoImposto getRecolhimentoINSS() {
        return recolhimentoINSS;
    }

    public void setRecolhimentoINSS(EnumRecolhimentoImposto recolhimentoINSS) {
        this.recolhimentoINSS = recolhimentoINSS;
    }

    public EnumRecolhimentoImposto getRecolhimentoIRRF() {
        return recolhimentoIRRF;
    }

    public void setRecolhimentoIRRF(EnumRecolhimentoImposto recolhimentoIRRF) {
        this.recolhimentoIRRF = recolhimentoIRRF;
    }

    public EnumRecolhimentoImposto getRecolhimentoPIS() {
        return recolhimentoPIS;
    }

    public void setRecolhimentoPIS(EnumRecolhimentoImposto recolhimentoPIS) {
        this.recolhimentoPIS = recolhimentoPIS;
    }

    public EnumRecolhimentoImposto getRecolhimentoCOFINS() {
        return recolhimentoCOFINS;
    }

    public void setRecolhimentoCOFINS(EnumRecolhimentoImposto recolhimentoCOFINS) {
        this.recolhimentoCOFINS = recolhimentoCOFINS;
    }

    public EnumRecolhimentoImposto getRecolhimentoCSSL() {
        return recolhimentoCSSL;
    }

    public void setRecolhimentoCSSL(EnumRecolhimentoImposto recolhimentoCSSL) {
        this.recolhimentoCSSL = recolhimentoCSSL;
    }

    public EnumRecolhimentoImposto getRecolhimentoSS() {
        return recolhimentoSS;
    }

    public void setRecolhimentoSS(EnumRecolhimentoImposto recolhimentoSS) {
        this.recolhimentoSS = recolhimentoSS;
    }

    public EnumTipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(EnumTipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getFichaNumero() {
        return fichaNumero;
    }

    public void setFichaNumero(String fichaNumero) {
        this.fichaNumero = fichaNumero;
    }

    public NaturezaFinanceira getNaturezaFinanceira() {
        return naturezaFinanceira;
    }

    public void setNaturezaFinanceira(NaturezaFinanceira naturezaFinanceira) {
        this.naturezaFinanceira = naturezaFinanceira;
    }

    public Float getValorDivergencia() {
        return valorDivergencia;
    }

    public void setValorDivergencia(Float valorDivergencia) {
        this.valorDivergencia = valorDivergencia;
    }

    public Integer getEntradaNumero() {
        return entradaNumero;
    }

    public void setEntradaNumero(Integer entradaNumero) {
        this.entradaNumero = entradaNumero;
    }

    public EnumsGeral getOperacaoEntreNucleos() {
        return operacaoEntreNucleos;
    }

    public void setOperacaoEntreNucleos(EnumsGeral operacaoEntreNucleos) {
        this.operacaoEntreNucleos = operacaoEntreNucleos;
    }

    public Float getValorAClassificar() {
        return valorAClassificar;
    }

    public void setValorAClassificar(Float valorAClassificar) {
        this.valorAClassificar = valorAClassificar;
    }

    public Float getDocValorBaseRateio() {
        return docValorBaseRateio;
    }

    public void setDocValorBaseRateio(Float docValorBaseRateio) {
        this.docValorBaseRateio = docValorBaseRateio;
    }

    public EnumsGeral getParcelasAlteradas() {
        return parcelasAlteradas;
    }

    public void setParcelasAlteradas(EnumsGeral parcelasAlteradas) {
        this.parcelasAlteradas = parcelasAlteradas;
    }

    public Float getValorDivergenciaCTB() {
        return valorDivergenciaCTB;
    }

    public void setValorDivergenciaCTB(Float valorDivergenciaCTB) {
        this.valorDivergenciaCTB = valorDivergenciaCTB;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getBasePercPIS() {
        return basePercPIS;
    }

    public void setBasePercPIS(Float basePercPIS) {
        this.basePercPIS = basePercPIS;
    }

    public Float getBasePIS() {
        return basePIS;
    }

    public void setBasePIS(Float basePIS) {
        this.basePIS = basePIS;
    }

    public Float getAliqPIS() {
        return aliqPIS;
    }

    public void setAliqPIS(Float aliqPIS) {
        this.aliqPIS = aliqPIS;
    }

    public Float getCalcPIS() {
        return calcPIS;
    }

    public void setCalcPIS(Float calcPIS) {
        this.calcPIS = calcPIS;
    }

    public Float getMinPIS() {
        return minPIS;
    }

    public void setMinPIS(Float minPIS) {
        this.minPIS = minPIS;
    }

    public Float getValorPIS() {
        return valorPIS;
    }

    public void setValorPIS(Float valorPIS) {
        this.valorPIS = valorPIS;
    }

    public Float getBasePercCOFINS() {
        return basePercCOFINS;
    }

    public void setBasePercCOFINS(Float basePercCOFINS) {
        this.basePercCOFINS = basePercCOFINS;
    }

    public Float getBaseCOFINS() {
        return baseCOFINS;
    }

    public void setBaseCOFINS(Float baseCOFINS) {
        this.baseCOFINS = baseCOFINS;
    }

    public Float getAliqCOFINS() {
        return aliqCOFINS;
    }

    public void setAliqCOFINS(Float aliqCOFINS) {
        this.aliqCOFINS = aliqCOFINS;
    }

    public Float getCalcCOFINS() {
        return calcCOFINS;
    }

    public void setCalcCOFINS(Float calcCOFINS) {
        this.calcCOFINS = calcCOFINS;
    }

    public Float getMinCOFINS() {
        return minCOFINS;
    }

    public void setMinCOFINS(Float minCOFINS) {
        this.minCOFINS = minCOFINS;
    }

    public Float getValorCOFINS() {
        return valorCOFINS;
    }

    public void setValorCOFINS(Float valorCOFINS) {
        this.valorCOFINS = valorCOFINS;
    }

    public Float getBasePercCSSL() {
        return basePercCSSL;
    }

    public void setBasePercCSSL(Float basePercCSSL) {
        this.basePercCSSL = basePercCSSL;
    }

    public Float getBaseCSSL() {
        return baseCSSL;
    }

    public void setBaseCSSL(Float baseCSSL) {
        this.baseCSSL = baseCSSL;
    }

    public Float getAliqCSSL() {
        return aliqCSSL;
    }

    public void setAliqCSSL(Float aliqCSSL) {
        this.aliqCSSL = aliqCSSL;
    }

    public Float getCalcCSSL() {
        return calcCSSL;
    }

    public void setCalcCSSL(Float calcCSSL) {
        this.calcCSSL = calcCSSL;
    }

    public Float getMinCSSL() {
        return minCSSL;
    }

    public void setMinCSSL(Float minCSSL) {
        this.minCSSL = minCSSL;
    }

    public Float getValorCSSL() {
        return valorCSSL;
    }

    public void setValorCSSL(Float valorCSSL) {
        this.valorCSSL = valorCSSL;
    }

    public Float getDescontoProduto() {
        return descontoProduto;
    }

    public void setDescontoProduto(Float descontoProduto) {
        this.descontoProduto = descontoProduto;
    }

    public Float getDespesasAcessorias() {
        return despesasAcessorias;
    }

    public void setDespesasAcessorias(Float despesasAcessorias) {
        this.despesasAcessorias = despesasAcessorias;
    }

    public Float getMedicaoRetencao() {
        return medicaoRetencao;
    }

    public void setMedicaoRetencao(Float medicaoRetencao) {
        this.medicaoRetencao = medicaoRetencao;
    }

    public String getMedicaoRetencaoSobre() {
        return medicaoRetencaoSobre;
    }

    public void setMedicaoRetencaoSobre(String medicaoRetencaoSobre) {
        this.medicaoRetencaoSobre = medicaoRetencaoSobre;
    }

    public Float getMedicaoDesconto() {
        return medicaoDesconto;
    }

    public void setMedicaoDesconto(Float medicaoDesconto) {
        this.medicaoDesconto = medicaoDesconto;
    }

    public Float getMedicaoReajuste() {
        return medicaoReajuste;
    }

    public void setMedicaoReajuste(Float medicaoReajuste) {
        this.medicaoReajuste = medicaoReajuste;
    }

    public Integer getNumeroISS() {
        return numeroISS;
    }

    public void setNumeroISS(Integer numeroISS) {
        this.numeroISS = numeroISS;
    }

    public Integer getNumeroINSS() {
        return numeroINSS;
    }

    public void setNumeroINSS(Integer numeroINSS) {
        this.numeroINSS = numeroINSS;
    }

    public Integer getNumeroPIS() {
        return numeroPIS;
    }

    public void setNumeroPIS(Integer numeroPIS) {
        this.numeroPIS = numeroPIS;
    }

    public Integer getNumeroCOFINS() {
        return numeroCOFINS;
    }

    public void setNumeroCOFINS(Integer numeroCOFINS) {
        this.numeroCOFINS = numeroCOFINS;
    }

    public Integer getNumeroCSLL() {
        return numeroCSLL;
    }

    public void setNumeroCSLL(Integer numeroCSLL) {
        this.numeroCSLL = numeroCSLL;
    }

    public Integer getNumeroIRRF() {
        return numeroIRRF;
    }

    public void setNumeroIRRF(Integer numeroIRRF) {
        this.numeroIRRF = numeroIRRF;
    }

    public EnumsGeral getConforme() {
        return conforme;
    }

    public void setConforme(EnumsGeral conforme) {
        this.conforme = conforme;
    }

    public Float getPontuacaoAvaliacao() {
        return pontuacaoAvaliacao;
    }

    public void setPontuacaoAvaliacao(Float pontuacaoAvaliacao) {
        this.pontuacaoAvaliacao = pontuacaoAvaliacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getzDifAliq() {
        return zDifAliq;
    }

    public void setzDifAliq(String zDifAliq) {
        this.zDifAliq = zDifAliq;
    }

    public String getCredorCodPedido() {
        return credorCodPedido;
    }

    public void setCredorCodPedido(String credorCodPedido) {
        this.credorCodPedido = credorCodPedido;
    }

    public Float getMaterialFornecido() {
        return materialFornecido;
    }

    public void setMaterialFornecido(Float materialFornecido) {
        this.materialFornecido = materialFornecido;
    }

    public Float getMaterialFornecidoC() {
        return materialFornecidoC;
    }

    public void setMaterialFornecidoC(Float materialFornecidoC) {
        this.materialFornecidoC = materialFornecidoC;
    }

    public Float getMaterialFornecidoA() {
        return materialFornecidoA;
    }

    public void setMaterialFornecidoA(Float materialFornecidoA) {
        this.materialFornecidoA = materialFornecidoA;
    }

    public Float getMedicaoReajusteC() {
        return medicaoReajusteC;
    }

    public void setMedicaoReajusteC(Float medicaoReajusteC) {
        this.medicaoReajusteC = medicaoReajusteC;
    }

    public Float getMedicaoReajusteA() {
        return medicaoReajusteA;
    }

    public void setMedicaoReajusteA(Float medicaoReajusteA) {
        this.medicaoReajusteA = medicaoReajusteA;
    }

    public Float getMedicaoDescontoC() {
        return medicaoDescontoC;
    }

    public void setMedicaoDescontoC(Float medicaoDescontoC) {
        this.medicaoDescontoC = medicaoDescontoC;
    }

    public Float getMedicaoDescontoA() {
        return medicaoDescontoA;
    }

    public void setMedicaoDescontoA(Float medicaoDescontoA) {
        this.medicaoDescontoA = medicaoDescontoA;
    }

    public Float getMedicaoRetencaoC() {
        return medicaoRetencaoC;
    }

    public void setMedicaoRetencaoC(Float medicaoRetencaoC) {
        this.medicaoRetencaoC = medicaoRetencaoC;
    }

    public Float getMedicaoRetencaoA() {
        return medicaoRetencaoA;
    }

    public void setMedicaoRetencaoA(Float medicaoRetencaoA) {
        this.medicaoRetencaoA = medicaoRetencaoA;
    }

    public String getClassificacaoIRRF() {
        return classificacaoIRRF;
    }

    public void setClassificacaoIRRF(String classificacaoIRRF) {
        this.classificacaoIRRF = classificacaoIRRF;
    }

    public String getUsuarioAutorizacao() {
        return usuarioAutorizacao;
    }

    public void setUsuarioAutorizacao(String usuarioAutorizacao) {
        this.usuarioAutorizacao = usuarioAutorizacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getComplementoDocumento() {
        return complementoDocumento;
    }

    public void setComplementoDocumento(String complementoDocumento) {
        this.complementoDocumento = complementoDocumento;
    }

    public Integer getDocDigNumero() {
        return docDigNumero;
    }

    public void setDocDigNumero(Integer docDigNumero) {
        this.docDigNumero = docDigNumero;
    }

    public Integer getCFOP() {
        return CFOP;
    }

    public void setCFOP(Integer CFOP) {
        this.CFOP = CFOP;
    }

    public String getsRemPostAnt() {
        return sRemPostAnt;
    }

    public void setsRemPostAnt(String sRemPostAnt) {
        this.sRemPostAnt = sRemPostAnt;
    }

    public Float getAliqSS() {
        return aliqSS;
    }

    public void setAliqSS(Float aliqSS) {
        this.aliqSS = aliqSS;
    }

    public Float getBasePercSS() {
        return basePercSS;
    }

    public void setBasePercSS(Float basePercSS) {
        this.basePercSS = basePercSS;
    }

    public Float getBaseSS() {
        return baseSS;
    }

    public void setBaseSS(Float baseSS) {
        this.baseSS = baseSS;
    }

    public Float getValorSS() {
        return valorSS;
    }

    public void setValorSS(Float valorSS) {
        this.valorSS = valorSS;
    }

    public Integer getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(Integer numeroSS) {
        this.numeroSS = numeroSS;
    }

    @Override
    public Serializable getId() {
        return numero;
    }

    @Override
    public String getLabel() {
        return numero.toString();
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
    public int compareTo(DocumentoEntrada o) {
        return getNumero().compareTo(o.getNumero());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.numero);
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
        final DocumentoEntrada other = (DocumentoEntrada) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
}
