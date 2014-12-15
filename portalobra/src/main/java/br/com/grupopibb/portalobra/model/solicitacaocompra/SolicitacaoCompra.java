/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.solicitacaocompra;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Solicitacao_de_Compra")
@NamedQueries({
    @NamedQuery(name = "SolicitacaoCompra.selectRange",
            query = " SELECT DISTINCT sc FROM SolicitacaoCompra sc "
            + " WHERE (:centroCusto2 = 'todos' OR sc.centro.codigo = :centroCusto) "),
    @NamedQuery(name = "SolicitacaoCompra.countRange",
            query = " SELECT COUNT (DISTINCT sc) FROM SolicitacaoCompra sc "
            + " WHERE (:centroCusto2 = 'todos' OR sc.centro.codigo = :centroCusto) "),
    @NamedQuery(name = "SolicitacaoCompra.selectMaxId",
            query = " SELECT MAX(sc.numero) FROM SolicitacaoCompra sc"),
    @NamedQuery(name = "SolicitacaoCompra.selectMaxCentroNumero",
            query = " SELECT MAX(sc.idSistema) FROM SolicitacaoCompra sc "
            + " WHERE sc.centro = :centro")
})
public class SolicitacaoCompra implements EntityInterface<SolicitacaoCompra> {

    public SolicitacaoCompra() {
    }
    
    public SolicitacaoCompra(SolicitacaoCompra solicitacaoCompra) {
        this.numero = solicitacaoCompra.getNumero();
        this.empresa = solicitacaoCompra.getEmpresa();
        this.filial = solicitacaoCompra.getFilial();
        this.centro = solicitacaoCompra.getCentro();
        this.solicitante = solicitacaoCompra.getSolicitante();
        this.dataSolicitacao = solicitacaoCompra.getDataSolicitacao();
        this.situacao = solicitacaoCompra.getSituacao();
        this.idSistema = solicitacaoCompra.getIdSistema();
        this.concluido = solicitacaoCompra.getConcluido();
        this.usuarioInstrucaoEdit = solicitacaoCompra.getUsuarioInstrucaoEdit();
        this.instrucaoDataEdit = solicitacaoCompra.getInstrucaoDataEdit();
        this.itens = solicitacaoCompra.getItens();
    
    }

    public SolicitacaoCompra(Long numero) {
        this.numero = numero;
    }

    public SolicitacaoCompra(Long numero, String empresa, String filial, CentroCusto centro,
            Solicitante solicitante, Date dataSolicitacao, EnumsGeral situacao, Integer idSistema,
            EnumsGeral concluido, String usuarioInstrucaoEdit, Date instrucaoDataEdit, List<SolicitacaoCompraItem> itens) {
        this.numero = numero;
        this.empresa = empresa;
        this.filial = filial;
        this.centro = centro;
        this.solicitante = solicitante;
        this.dataSolicitacao = dataSolicitacao;
        this.situacao = situacao;
        this.idSistema = idSistema;
        this.concluido = concluido;
        this.usuarioInstrucaoEdit = usuarioInstrucaoEdit;
        this.instrucaoDataEdit = instrucaoDataEdit;
        this.itens = itens;
    }

    /**
     *
     * Utiliza a ideia de Construtor para setar os atributos da entidade sem
     * precisar criar uma nova instância. Funciona como a função set executada
     * em lote.
     *
     * @param centro
     * @param solicitante
     * @param dataSolicitacao
     * @param situacao
     * @param idSistema
     * @param concluido
     * @param usuarioInstrucaoEdit
     * @param instrucaoDataEdit *
     */
    public void rebuildFields(CentroCusto centro, Solicitante solicitante, Date dataSolicitacao, EnumsGeral situacao,
            Integer idSistema, EnumsGeral concluido, String usuarioInstrucaoEdit, Date instrucaoDataEdit) {
        this.centro = centro;
        this.solicitante = solicitante;
        this.dataSolicitacao = dataSolicitacao;
        this.situacao = situacao;
        this.idSistema = idSistema;
        this.concluido = concluido;
        this.usuarioInstrucaoEdit = usuarioInstrucaoEdit;
        this.instrucaoDataEdit = instrucaoDataEdit;
    }
    /*
     */
    @Id
    @NotNull
    @Column(name = "Solic_Numero", nullable = false)
    private Long numero;
    /*
     */
    @Column(name = "Empresa_Cod", insertable = false, updatable = false)
    private String empresa;
    /*
     */
    @Column(name = "Filial_Cod", insertable = false, updatable = false)
    private String filial;
    /*
     */
    @ManyToOne(targetEntity = CentroCusto.class, fetch = FetchType.EAGER)
    @JoinColumns(value = {
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod"),
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod")})
    private CentroCusto centro;
    /*
     */
    @OneToOne(targetEntity = Solicitante.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Solicitante_Cod")
    private Solicitante solicitante;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Solic_Data")
    private Date dataSolicitacao;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Solic_Situacao")
    private EnumsGeral situacao;
    /*
     */
    @Column(name = "Solic_CentroNumero")
    private Integer idSistema;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Solic_Concluido")
    private EnumsGeral concluido;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Solic_DataConcluido")
    private Date dataConcluido;
    /*
     */
    @Size(max = 40)
    @Column(name = "Solic_UsuarioConcluido", length = 40)
    private String usuarioConcluido;
    /*
     */
    @Size(max = 3)
    @Column(name = "Solic_SetorResponsavel", length = 3)
    private String setorResponsavel;
    /*
     */
    @Size(max = 40)
    @Column(name = "Usuario_Instrucao_Edit", length = 40)
    private String usuarioInstrucaoEdit;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Instrucao_Data_Edit")
    private Date instrucaoDataEdit;
    /*
     */
    @OneToMany(targetEntity = SolicitacaoCompraItem.class, mappedBy = "solicitacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitacaoCompraItem> itens;
    /*
     */
    @Transient
    private boolean itemRemovido = false;

    /**
     * Responde se a solicitação existente possui um item removido. Informado ao
     * remover um item.
     *
     * @return
     */
    public boolean isItemRemovido() {
        return itemRemovido;
    }

    /**
     * Informa à solicitação que esta possui um item removido.
     *
     * @param itemRemovido
     */
    public void setItemRemovido(boolean itemRemovido) {
        this.itemRemovido = itemRemovido;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public EnumsGeral getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumsGeral situacao) {
        this.situacao = situacao;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public EnumsGeral getConcluido() {
        return concluido;
    }

    public void setConcluido(EnumsGeral concluido) {
        this.concluido = concluido;
    }

    public Date getDataConcluido() {
        return dataConcluido;
    }

    public void setDataConcluido(Date dataConcluido) {
        this.dataConcluido = dataConcluido;
    }

    public String getUsuarioConcluido() {
        return usuarioConcluido;
    }

    public void setUsuarioConcluido(String usuarioConcluido) {
        this.usuarioConcluido = usuarioConcluido;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public String getUsuarioInstrucaoEdit() {
        return usuarioInstrucaoEdit;
    }

    public void setUsuarioInstrucaoEdit(String usuarioInstrucaoEdit) {
        this.usuarioInstrucaoEdit = usuarioInstrucaoEdit;
    }

    public Date getInstrucaoDataEdit() {
        return instrucaoDataEdit;
    }

    public void setInstrucaoDataEdit(Date instrucaoDataEdit) {
        this.instrucaoDataEdit = instrucaoDataEdit;
    }

    public List<SolicitacaoCompraItem> getItens() {
        return itens;
    }

    public void setItens(List<SolicitacaoCompraItem> itens) {
        this.itens = itens;
    }

    @Override
    public Serializable getId() {
        return numero;
    }

    @Override
    public String getLabel() {
        return String.valueOf(numero) + String.valueOf(dataSolicitacao);
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(SolicitacaoCompra o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
