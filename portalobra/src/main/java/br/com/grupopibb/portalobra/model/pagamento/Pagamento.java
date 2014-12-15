/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.pagamento;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Pagamentos")
public class Pagamento implements EntityInterface<Pagamento>{

    @Id
    @Column(name = "Pag_Numero")
    private Long numero;
    /*
     */
    @Column(name = "Empresa_Cod")
    private String empresaCod;
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
    @Column(name = "Filial_Cod")
    private String filialCod;
    /*
     */
    @Column(name = "DocSigla_Ano")
    private String docSiglaAno;
    /*
     */
    @Column(name = "Pag_ValorPagamento")
    private Double valorPagamento;
    /*
     */
    @Column(name = "DocSigla_Sequencia")
    private String docSiglaSequencia;
    /*
     */
    @Column(name = "Pag_Favorecido")
    private String favorecido;
    /*
     */
    @Column(name = "AgenteFinanceiroTipo_Cod")
    private String tipoAgenteFinanceiro;
    /*
     */
    @Column(name = "AgenteFinanceiro_Cod")
    private String agenteFinanceiro;
    /*
     */
    @Column(name = "AgeFin_ContaNumero")
    private String numeroConta;
    /*
     */
    @Column(name = "Pag_DocTipo")
    private String tipoDoc;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Pag_Cancelado")
    private EnumsGeral cancelado;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Pag_Transferencia")
    private EnumsGeral transferencia;
    /*
     */
    @Column(name = "MovFin_Numero")
    private Integer numeroMovimentoFinanceiro;
    /*
     */
    @Column(name = "Pag_HistoricoChqTransf")
    private String historicoChequeTransf;
    /*
     */
    @Column(name = "Pag_DocNumero")
    private String docNumero;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Pag_Emitido")
    private EnumsGeral emitido;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Pag_Pendente")
    private EnumsGeral pendente;
    /*
     */
    @Column(name = "Nucleo_Cod")
    private String nucleo;
    /*
     */
    @Column(name = "AgenteFinanceiroTipo_Cod_Dest")
    private String tipoDestino;
    /*
     */
    @Column(name = "AgenteFinanceiro_Cod_Dest")
    private String agenteFinanceiroDestino;
    /*
     */
    @Column(name = "AgeFin_ContaNumero_Dest")
    private String contaDestino;
    /*
     */
    @Column(name = "Nucleo_Cod_Dest")
    private String nucleoDestino;
    /*
     */
    @Column(name = "MovFinNatureza_Cod")
    private String naturezaFinanceira;
    /*
     */
    @Column(name = "MovFinTipo_Cod")
    private String tipoMovimento;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataLiberacao")
    private Date dataLiberacao;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Pag_DataEmissao")
    private Date dataEmissao;
    /*
     */
    @Column(name = "Pag_MotivoTransf")
    private String motivoTransferencia;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "R_Pendencia_Data")
    private Date dataPendencia;
    /*
     */
    @Column(name = "R_Pendencia_Usuario")
    private String usuarioPendencia;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Efetiva_Data")
    private Date dataEfetivacao;
    /*
     */
    @Column(name = "Efetiva_Usuario")
    private String usuarioEfetivacao;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
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

    public String getFilialCod() {
        return filialCod;
    }

    public void setFilialCod(String filialCod) {
        this.filialCod = filialCod;
    }

    public String getDocSiglaAno() {
        return docSiglaAno;
    }

    public void setDocSiglaAno(String docSiglaAno) {
        this.docSiglaAno = docSiglaAno;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public String getDocSiglaSequencia() {
        return docSiglaSequencia;
    }

    public void setDocSiglaSequencia(String docSiglaSequencia) {
        this.docSiglaSequencia = docSiglaSequencia;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getTipoAgenteFinanceiro() {
        return tipoAgenteFinanceiro;
    }

    public void setTipoAgenteFinanceiro(String tipoAgenteFinanceiro) {
        this.tipoAgenteFinanceiro = tipoAgenteFinanceiro;
    }

    public String getAgenteFinanceiro() {
        return agenteFinanceiro;
    }

    public void setAgenteFinanceiro(String agenteFinanceiro) {
        this.agenteFinanceiro = agenteFinanceiro;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public EnumsGeral getCancelado() {
        return cancelado;
    }

    public void setCancelado(EnumsGeral cancelado) {
        this.cancelado = cancelado;
    }

    public EnumsGeral getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(EnumsGeral transferencia) {
        this.transferencia = transferencia;
    }

    public Integer getNumeroMovimentoFinanceiro() {
        return numeroMovimentoFinanceiro;
    }

    public void setNumeroMovimentoFinanceiro(Integer numeroMovimentoFinanceiro) {
        this.numeroMovimentoFinanceiro = numeroMovimentoFinanceiro;
    }

    public String getHistoricoChequeTransf() {
        return historicoChequeTransf;
    }

    public void setHistoricoChequeTransf(String historicoChequeTransf) {
        this.historicoChequeTransf = historicoChequeTransf;
    }

    public String getDocNumero() {
        return docNumero;
    }

    public void setDocNumero(String docNumero) {
        this.docNumero = docNumero;
    }

    public EnumsGeral getEmitido() {
        return emitido;
    }

    public void setEmitido(EnumsGeral emitido) {
        this.emitido = emitido;
    }

    public EnumsGeral getPendente() {
        return pendente;
    }

    public void setPendente(EnumsGeral pendente) {
        this.pendente = pendente;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(String tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public String getAgenteFinanceiroDestino() {
        return agenteFinanceiroDestino;
    }

    public void setAgenteFinanceiroDestino(String agenteFinanceiroDestino) {
        this.agenteFinanceiroDestino = agenteFinanceiroDestino;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getNucleoDestino() {
        return nucleoDestino;
    }

    public void setNucleoDestino(String nucleoDestino) {
        this.nucleoDestino = nucleoDestino;
    }

    public String getNaturezaFinanceira() {
        return naturezaFinanceira;
    }

    public void setNaturezaFinanceira(String naturezaFinanceira) {
        this.naturezaFinanceira = naturezaFinanceira;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getMotivoTransferencia() {
        return motivoTransferencia;
    }

    public void setMotivoTransferencia(String motivoTransferencia) {
        this.motivoTransferencia = motivoTransferencia;
    }

    public Date getDataPendencia() {
        return dataPendencia;
    }

    public void setDataPendencia(Date dataPendencia) {
        this.dataPendencia = dataPendencia;
    }

    public String getUsuarioPendencia() {
        return usuarioPendencia;
    }

    public void setUsuarioPendencia(String usuarioPendencia) {
        this.usuarioPendencia = usuarioPendencia;
    }

    public Date getDataEfetivacao() {
        return dataEfetivacao;
    }

    public void setDataEfetivacao(Date dataEfetivacao) {
        this.dataEfetivacao = dataEfetivacao;
    }

    public String getUsuarioEfetivacao() {
        return usuarioEfetivacao;
    }

    public void setUsuarioEfetivacao(String usuarioEfetivacao) {
        this.usuarioEfetivacao = usuarioEfetivacao;
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
    public int compareTo(Pagamento o) {
        return this.numero.compareTo(o.getNumero());
    }
}
