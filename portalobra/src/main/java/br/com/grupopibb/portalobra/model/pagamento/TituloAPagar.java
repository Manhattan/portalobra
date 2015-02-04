/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.pagamento;

import br.com.grupopibb.portalobra.model.ar.DocumentoEntrada;
import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumSituacaoPagamento;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Titulos_a_Pagar")
public class TituloAPagar implements EntityInterface<TituloAPagar> {

    public TituloAPagar() {
    }

    /**
     * Construtor para criação do Título a Pagar que será inserido no AR.
     *
     * @param numero
     * @param credor
     * @param especieTitulo
     * @param valorTitulo
     * @param acrescimos
     * @param descontos
     * @param descontoAdiantamento
     * @param documentoEntrada
     * @param dataVencimento
     * @param numeroParcela
     * @param numeroSubParcela
     * @param naturezaPagamento
     * @param dataAgendaFinanceira
     * @param numeroDocumento
     * @param situacao
     * @param dataVencimentoFluxo
     * @param dataEmissaoTitulo
     * @param dataEntradaTitulo
     * @param dataVencimentoOriginal
     * @param tituloMae
     * @param valorOriginal
     * @param diasProrrogados
     * @param taxaJurosMes
     * @param tipoDocumentoEntrada
     * @param empresaCod
     * @param encargosFinanceiros
     * @param totalPago
     * @param taxaMulta
     * @param multa
     */
    public TituloAPagar(Long numero, String credor, EspecieTituloAPagar especieTitulo, Float valorTitulo, Float acrescimos, Float descontos,
            Float descontoAdiantamento, DocumentoEntrada documentoEntrada, Date dataVencimento, String numeroParcela, String numeroSubParcela,
            String naturezaPagamento, Date dataAgendaFinanceira, String numeroDocumento, EnumSituacaoPagamento situacao,
            Date dataVencimentoFluxo, Date dataEmissaoTitulo, Date dataEntradaTitulo, Date dataVencimentoOriginal,
            Float valorOriginal, Integer diasProrrogados, Float taxaJurosMes, String tipoDocumentoEntrada, String empresaCod,
            Float encargosFinanceiros, Float totalPago, Float taxaMulta, Float multa) {
        this.numero = numero;
        this.credor = credor;
        this.especieTitulo = especieTitulo;
        this.valorTitulo = valorTitulo;
        this.acrescimos = acrescimos;
        this.descontos = descontos;
        this.descontoAdiantamento = descontoAdiantamento;
        this.documentoEntrada = documentoEntrada;
        this.dataVencimento = dataVencimento;
        this.numeroParcela = numeroParcela;
        this.numeroSubParcela = numeroSubParcela;
        this.naturezaPagamento = naturezaPagamento;
        this.dataAgendaFinanceira = dataAgendaFinanceira;
        this.numeroDocumento = numeroDocumento;
        this.situacao = situacao;
        this.dataVencimentoFluxo = dataVencimentoFluxo;
        this.dataEmissaoTitulo = dataEmissaoTitulo;
        this.dataEntradaTitulo = dataEntradaTitulo;
        this.dataVencimentoOriginal = dataVencimentoOriginal;
        this.valorOriginal = valorOriginal;
        this.diasProrrogados = diasProrrogados;
        this.taxaJurosMes = taxaJurosMes;
        this.tipoDocumentoEntrada = tipoDocumentoEntrada;
        this.empresaCod = empresaCod;
        this.encargosFinanceiros = encargosFinanceiros;
        this.totalPago = totalPago;
        this.taxaMulta = taxaMulta;
        this.multa = multa;
    }
    /*
     */
    @Id
    @NotNull
    @Column(name = "TitaPag_Numero", nullable = false)
    private Long numero;
    /*
     */
    @Column(name = "Cre_Cod", nullable = false)
    private String credor;
    /*
     */
    @ManyToOne(targetEntity = EspecieTituloAPagar.class)
    @JoinColumn(name = "EspTit_Cod", insertable = false, updatable = false)
    private EspecieTituloAPagar especieTitulo;
    /*
     */
    @Column(name="EspTit_Cod")
    private String especieTituloCodigo;
    /*
     */
    @Column(name = "TitaPag_Valor", nullable = false)
    private Float valorTitulo;
    /*
     */
    @Column(name = "TitaPag_Acrescimos", nullable = false)
    private Float acrescimos;
    /*
     */
    @Column(name = "TitaPag_Descontos", nullable = false)
    private Float descontos;
    /*
     */
    @Column(name = "TitaPag_DescontoAdiantamento", nullable = false)
    private Float descontoAdiantamento;
    /*
     */
    @ManyToOne
    @JoinColumn(name = "Ent_Numero", nullable = false, insertable = false, updatable = false)
    private DocumentoEntrada documentoEntrada;
    /*
     */
    @Column(name = "Ent_Numero")
    private Long documentoEntradaNumero;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataVencimento", nullable = false)
    private Date dataVencimento;
    /*
     */
    @Column(name = "TitaPag_ParNumero", nullable = false)
    private String numeroParcela;
    /*
     */
    @Column(name = "TitaPag_SParNumero", nullable = false)
    private String numeroSubParcela;
    /*
     */
    @Column(name = "TitaPag_NatPag", nullable = false)
    private String naturezaPagamento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataAgendaFin")
    private Date dataAgendaFinanceira;
    /*
     */
    @Column(name = "TitaPag_DocNumero")
    private String numeroDocumento;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "TitaPag_Situacao", nullable = false)
    private EnumSituacaoPagamento situacao;
    /*
     */
    @OneToOne(targetEntity = Pagamento.class)
    @JoinColumn(name = "Pag_Numero")
    private Pagamento pagamento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataPagamento")
    private Date dataPagamento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataDocumento")
    private Date dataDocumento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataVencimentoFluxo")
    private Date dataVencimentoFluxo;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Pag_Pendente")
    private EnumsGeral pendente;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataEmissao")
    private Date dataEmissaoTitulo;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataEntrada")
    private Date dataEntradaTitulo;
    /*
     */
    @Column(name = "Instrucao_Cod")
    private String instrucaoCod;
    /*
     */
    @Column(name = "Usuario_Instrucao")
    private String usuarioInstrucao;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Instrucao_Data")
    private Date dataInstrucao;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TitaPag_DataVencimentoOrig")
    private Date dataVencimentoOriginal;
    /*
     */
    @ManyToOne(targetEntity = TituloAPagar.class)
    @JoinColumn(name = "TitaPagMae_Numero")
    private TituloAPagar tituloMae;
    /*
     */
    @Column(name = "TitaPag_ValorOrig")
    private Float valorOriginal;
    /*
     */
    @Column(name = "TitaPag_DiasProrrogados")
    private Integer diasProrrogados;
    /*
     */
    @Column(name = "TitaPag_TaxaJurosMes")
    private Float taxaJurosMes;
    /*
     */
    @Column(name = "EntDocTipo_Cod")
    private String tipoDocumentoEntrada;
    /*
     */
    @Column(name = "Empresa_Cod")
    private String empresaCod;
    /*
     */
    @Column(name = "TitaPag_EncargosFin")
    private Float encargosFinanceiros;
    /*
     */
    @Column(name = "TitaPag_TotalPago")
    private Float totalPago;
    /*
     */
    @Column(name = "TitaPag_TaxaMulta")
    private Float taxaMulta;
    /*
     */
    @Column(name = "TitaPag_Multa")
    private Float multa;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataEmissao")
    private Date dataEmissaoPagamento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataLiberacao")
    private Date dataLiberacaoPagamento;
    
    public Float getValorTotal(){
        return valorTitulo + acrescimos - descontos - descontoAdiantamento + multa + encargosFinanceiros;
    }

    /**
     * Calcula a diferença de dias entre a data de vencimento e a data de
     * pagamento do título. Se a data de pagamento for nula, é calculada a
     * diferença até a data atual.
     *
     * @return dias vencidos
     */
    public Integer getDiasVencidos() {
        if (dataPagamento == null) {
            dataPagamento = new Date();
        }
        if (dataVencimento.getTime() > dataPagamento.getTime()) {
            return 0;
        }
        return new Long(DateUtils.daysBetween(dataVencimento, dataPagamento)).intValue();
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCredor() {
        return credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
    }

    public EspecieTituloAPagar getEspecieTitulo() {
        return especieTitulo;
    }

    public void setEspecieTitulo(EspecieTituloAPagar especieTitulo) {
        this.especieTitulo = especieTitulo;
    }

    public String getEspecieTituloCodigo() {
        return especieTituloCodigo;
    }

    public void setEspecieTituloCodigo(String especieTituloCodigo) {
        this.especieTituloCodigo = especieTituloCodigo;
    }

    public Float getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(Float valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public Float getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(Float acrescimos) {
        this.acrescimos = acrescimos;
    }

    public Float getDescontos() {
        return descontos;
    }

    public void setDescontos(Float descontos) {
        this.descontos = descontos;
    }

    public Float getDescontoAdiantamento() {
        return descontoAdiantamento;
    }

    public void setDescontoAdiantamento(Float descontoAdiantamento) {
        this.descontoAdiantamento = descontoAdiantamento;
    }

    public DocumentoEntrada getDocumentoEntrada() {
        return documentoEntrada;
    }

    public void setDocumentoEntrada(DocumentoEntrada documentoEntrada) {
        this.documentoEntrada = documentoEntrada;
    }

    public Long getDocumentoEntradaNumero() {
        return documentoEntradaNumero;
    }

    public void setDocumentoEntradaNumero(Long documentoEntradaNumero) {
        this.documentoEntradaNumero = documentoEntradaNumero;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getNumeroSubParcela() {
        return numeroSubParcela;
    }

    public void setNumeroSubParcela(String numeroSubParcela) {
        this.numeroSubParcela = numeroSubParcela;
    }

    public String getNaturezaPagamento() {
        return naturezaPagamento;
    }

    public void setNaturezaPagamento(String naturezaPagamento) {
        this.naturezaPagamento = naturezaPagamento;
    }

    public Date getDataAgendaFinanceira() {
        return dataAgendaFinanceira;
    }

    public void setDataAgendaFinanceira(Date dataAgendaFinanceira) {
        this.dataAgendaFinanceira = dataAgendaFinanceira;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public EnumSituacaoPagamento getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacaoPagamento situacao) {
        this.situacao = situacao;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Date getDataVencimentoFluxo() {
        return dataVencimentoFluxo;
    }

    public void setDataVencimentoFluxo(Date dataVencimentoFluxo) {
        this.dataVencimentoFluxo = dataVencimentoFluxo;
    }

    public EnumsGeral getPendente() {
        return pendente;
    }

    public void setPendente(EnumsGeral pendente) {
        this.pendente = pendente;
    }

    public Date getDataEmissaoTitulo() {
        return dataEmissaoTitulo;
    }

    public void setDataEmissaoTitulo(Date dataEmissaoTitulo) {
        this.dataEmissaoTitulo = dataEmissaoTitulo;
    }

    public Date getDataEntradaTitulo() {
        return dataEntradaTitulo;
    }

    public void setDataEntradaTitulo(Date dataEntradaTitulo) {
        this.dataEntradaTitulo = dataEntradaTitulo;
    }

    public String getInstrucaoCod() {
        return instrucaoCod;
    }

    public void setInstrucaoCod(String instrucaoCod) {
        this.instrucaoCod = instrucaoCod;
    }

    public String getUsuarioInstrucao() {
        return usuarioInstrucao;
    }

    public void setUsuarioInstrucao(String usuarioInstrucao) {
        this.usuarioInstrucao = usuarioInstrucao;
    }

    public Date getDataInstrucao() {
        return dataInstrucao;
    }

    public void setDataInstrucao(Date dataInstrucao) {
        this.dataInstrucao = dataInstrucao;
    }

    public Date getDataVencimentoOriginal() {
        return dataVencimentoOriginal;
    }

    public void setDataVencimentoOriginal(Date dataVencimentoOriginal) {
        this.dataVencimentoOriginal = dataVencimentoOriginal;
    }

    public TituloAPagar getTituloMae() {
        return tituloMae;
    }

    public void setTituloMae(TituloAPagar tituloMae) {
        this.tituloMae = tituloMae;
    }

    public Float getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(Float valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public Integer getDiasProrrogados() {
        return diasProrrogados;
    }

    public void setDiasProrrogados(Integer diasProrrogados) {
        this.diasProrrogados = diasProrrogados;
    }

    public Float getTaxaJurosMes() {
        return taxaJurosMes;
    }

    public void setTaxaJurosMes(Float taxaJurosMes) {
        this.taxaJurosMes = taxaJurosMes;
    }

    public String getTipoDocumentoEntrada() {
        return tipoDocumentoEntrada;
    }

    public void setTipoDocumentoEntrada(String tipoDocumentoEntrada) {
        this.tipoDocumentoEntrada = tipoDocumentoEntrada;
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public Float getEncargosFinanceiros() {
        return encargosFinanceiros;
    }

    public void setEncargosFinanceiros(Float encargosFinanceiros) {
        this.encargosFinanceiros = encargosFinanceiros;
    }

    public Float getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Float totalPago) {
        this.totalPago = totalPago;
    }

    public Float getTaxaMulta() {
        return taxaMulta;
    }

    public void setTaxaMulta(Float taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public Float getMulta() {
        return multa;
    }

    public void setMulta(Float multa) {
        this.multa = multa;
    }

    public Date getDataEmissaoPagamento() {
        return dataEmissaoPagamento;
    }

    public void setDataEmissaoPagamento(Date dataEmissaoPagamento) {
        this.dataEmissaoPagamento = dataEmissaoPagamento;
    }

    public Date getDataLiberacaoPagamento() {
        return dataLiberacaoPagamento;
    }

    public void setDataLiberacaoPagamento(Date dataLiberacaoPagamento) {
        this.dataLiberacaoPagamento = dataLiberacaoPagamento;
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
    public int compareTo(TituloAPagar o) {
        return this.numero.compareTo(o.getNumero());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.numero);
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
        final TituloAPagar other = (TituloAPagar) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
}
