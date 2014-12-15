/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author administrator
 */
public class PosicaoCredor implements Serializable {

    public PosicaoCredor() {
    }

    public PosicaoCredor(Integer numeroTitAPag, String credorCod, Date vencimento, Float valorNominal, Float acrescimos, Float descontos, Float descontoAdi, Float encargos, Float multa, Float totalPago, Float valorLiquido, String tipoDoc, String natureza, String numeroDoc, String documento, String parcela, String empresa, String filial, String centroCod, String centroNome, String numeroSP, String numeroFicha, Integer atraso, Integer atrasoR, Date vencimentoOriginal, String pendente, String nucleo, Date dataPagto, Date dataLiberacao) {
        this.numeroTitAPag = numeroTitAPag;
        this.credorCod = credorCod;
        this.vencimento = vencimento;
        this.valorNominal = valorNominal;
        this.acrescimos = acrescimos;
        this.descontos = descontos;
        this.descontoAdi = descontoAdi;
        this.encargos = encargos;
        this.multa = multa;
        this.totalPago = totalPago;
        this.valorLiquido = valorLiquido;
        this.tipoDoc = tipoDoc;
        this.natureza = natureza;
        this.numeroDoc = numeroDoc;
        this.documento = documento;
        this.parcela = parcela;
        this.empresa = empresa;
        this.filial = filial;
        this.centroCod = centroCod;
        this.centroNome = centroNome;
        this.numeroSP = numeroSP;
        this.numeroFicha = numeroFicha;
        this.atraso = atraso;
        this.atrasoR = atrasoR;
        this.vencimentoOriginal = vencimentoOriginal;
        this.pendente = pendente;
        this.nucleo = nucleo;
        this.dataPagto = dataPagto;
        this.dataLiberacao = dataLiberacao;
    }
    
    private Integer numeroTitAPag;
    private String credorCod;
    private Date vencimento;
    private Float valorNominal;
    private Float acrescimos;
    private Float descontos;
    private Float descontoAdi;
    private Float encargos;
    private Float multa;
    private Float totalPago;
    private Float valorLiquido;
    private String tipoDoc;
    private String natureza;
    private String numeroDoc;
    private String documento;
    private String parcela;
    private String empresa;
    private String filial;
    private String centroCod;
    private String centroNome;
    private String numeroSP;
    private String numeroFicha;
    private Integer atraso;
    private Integer atrasoR;
    private Date vencimentoOriginal;
    private String pendente;
    private String nucleo;
    private Date dataPagto;
    private Date dataLiberacao;

    public Integer getNumeroTitAPag() {
        return numeroTitAPag;
    }

    public void setNumeroTitAPag(Integer numeroTitAPag) {
        this.numeroTitAPag = numeroTitAPag;
    }

    public String getCredorCod() {
        return credorCod;
    }

    public void setCredorCod(String credorCod) {
        this.credorCod = credorCod;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Float getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(Float valorNominal) {
        this.valorNominal = valorNominal;
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

    public Float getDescontoAdi() {
        return descontoAdi;
    }

    public void setDescontoAdi(Float descontoAdi) {
        this.descontoAdi = descontoAdi;
    }

    public Float getEncargos() {
        return encargos;
    }

    public void setEncargos(Float encargos) {
        this.encargos = encargos;
    }

    public Float getMulta() {
        return multa;
    }

    public void setMulta(Float multa) {
        this.multa = multa;
    }

    public Float getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Float totalPago) {
        this.totalPago = totalPago;
    }

    public Float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public String getCentroNome() {
        return centroNome;
    }

    public void setCentroNome(String centroNome) {
        this.centroNome = centroNome;
    }

    public String getNumeroSP() {
        return numeroSP;
    }

    public void setNumeroSP(String numeroSP) {
        this.numeroSP = numeroSP;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public Integer getAtraso() {
        return atraso;
    }

    public void setAtraso(Integer atraso) {
        this.atraso = atraso;
    }

    public Integer getAtrasoR() {
        return atrasoR;
    }

    public void setAtrasoR(Integer atrasoR) {
        this.atrasoR = atrasoR;
    }

    public Date getVencimentoOriginal() {
        return vencimentoOriginal;
    }

    public void setVencimentoOriginal(Date vencimentoOriginal) {
        this.vencimentoOriginal = vencimentoOriginal;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
        this.dataPagto = dataPagto;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.numeroTitAPag);
        hash = 79 * hash + Objects.hashCode(this.credorCod);
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
        final PosicaoCredor other = (PosicaoCredor) obj;
        if (!Objects.equals(this.numeroTitAPag, other.numeroTitAPag)) {
            return false;
        }
        if (!Objects.equals(this.credorCod, other.credorCod)) {
            return false;
        }
        return true;
    }
    
    
}
