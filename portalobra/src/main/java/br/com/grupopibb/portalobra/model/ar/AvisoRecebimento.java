/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.ar;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Aviso_de_Recebimento")
@NamedQuery(name = "AvisoRecebimento.selectMaxCentroNumero",
        query = " SELECT MAX(ar.idSistema) FROM AvisoRecebimento ar "
        + " WHERE ar.centro = :centro")
public class AvisoRecebimento implements EntityInterface<AvisoRecebimento> {

    public AvisoRecebimento() {
    }

    public AvisoRecebimento(Long numero, Integer idSistema, Date data, CentroCusto centro, Credor credor, EnumsGeral ultimoAR, String observacao, String tipoAR, String tipoEnt) {
        this.numero = numero;
        this.idSistema = idSistema;
        this.data = data;
        this.centro = centro;
        this.credor = credor;
        this.ultimoAR = ultimoAR;
        this.observacao = observacao;
        this.tipoAR = tipoAR;
        this.tipoEnt = tipoEnt;
    }

    public AvisoRecebimento(Date data) {
        this.data = data;
    }

    /**
     * Utiliza a ideia de Construtor para setar os atributos da entidade sem
     * precisar criar uma nova instância. 
     * Funciona como a função set executada em lote.
     *
     * @param numero
     * @param centro
     * @param credor
     * @param ultimoAR
     * @param observacao
     * @param tipoAR
     * @param tipoEnt
     */
    public void rebuildFields(Long numero, CentroCusto centro, Credor credor, EnumsGeral ultimoAR, String observacao, String tipoAR, String tipoEnt) {
        this.numero = numero;
        this.centro = centro;
        this.credor = credor;
        this.ultimoAR = ultimoAR;
        this.observacao = observacao;
        this.tipoAR = tipoAR;
        this.tipoEnt = tipoEnt;
    }
    @Id
    @NotNull
    @Column(name = "AR_Numero", nullable = false)
    private Long numero;
    /*
     */
    @Column(name = "AR_CentroNumero")
    private Integer idSistema;
    /*
     */
    @Column(name = "AR_Data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    /*
     */
    @OneToOne(targetEntity = CentroCusto.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod", nullable = false),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod", nullable = false),
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod", nullable = false)
    })
    private CentroCusto centro;
    /*
     */
    @OneToOne(targetEntity = Credor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Cre_Cod", nullable = false)
    private Credor credor;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "AR_UltimoAR", nullable = false)
    private EnumsGeral ultimoAR;
    /*
     */
    @Column(name = "AR_Observacao")
    private String observacao;
    /*
     */
    @Column(name = "AR_TipoAR", nullable = false)
    private String tipoAR;
    /*
     */
    @Column(name = "Ent_Tipo")
    private String tipoEnt;
    /*
     */
    @OneToMany(targetEntity = DocumentoEntrada.class, mappedBy = "avisoRecebimento")
    private List<DocumentoEntrada> documentosEntrada;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public EnumsGeral getUltimoAR() {
        return ultimoAR;
    }

    public void setUltimoAR(EnumsGeral ultimoAR) {
        this.ultimoAR = ultimoAR;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipoAR() {
        return tipoAR;
    }

    public void setTipoAR(String tipoAR) {
        this.tipoAR = tipoAR;
    }

    public String getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(String tipoEnt) {
        this.tipoEnt = tipoEnt;
    }

    public List<DocumentoEntrada> getDocumentosEntrada() {
        return documentosEntrada;
    }

    public void setDocumentosEntrada(List<DocumentoEntrada> documentosEntrada) {
        this.documentosEntrada = documentosEntrada;
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
    public int compareTo(AvisoRecebimento o) {
        return this.numero.compareTo(o.getNumero());
    }
}
