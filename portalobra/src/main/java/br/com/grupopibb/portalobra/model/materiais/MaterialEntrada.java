/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.materiais;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.tipos.EnumSistemaOrigemEstoque;
import br.com.grupopibb.portalobra.model.tipos.EnumTipoMovimentoMaterialEntrada;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "MateriaisEntrada")
@NamedQueries({
    @NamedQuery(name = "MaterialEntrada.selectRange",
            query = " SELECT DISTINCT m FROM MaterialEntrada m "
            + " WHERE ( :numeroEntrada2 = 'todos' OR m.numeroEntrada = :numeroEntrada ) "
            + " AND ( m.centro = :centro ) "
            + " AND ( m.dataEntrada BETWEEN :dataInicial AND :dataFinal ) "
            + " AND ( :origem2 = 'todos' OR m.entradaOrigem = :origem ) "
            + " AND ( :numeroEntrada3 = 'todos' OR m.numeroEntrada IN :numeros ) "
            + " ORDER BY m.numeroEntrada DESC "),
    @NamedQuery(name = "MaterialEntrada.countRange",
            query = " SELECT COUNT(DISTINCT m) FROM MaterialEntrada m "
            + " WHERE ( :numeroEntrada2 = 'todos' OR m.numeroEntrada = :numeroEntrada ) "
            + " AND ( m.centro = :centro ) "
            + " AND ( m.dataEntrada BETWEEN :dataInicial AND :dataFinal ) "
            + " AND ( :origem2 = 'todos' OR m.entradaOrigem = :origem ) "
            + " AND ( :numeroEntrada3 = 'todos' OR m.numeroEntrada IN :numeros ) ")
})
public class MaterialEntrada implements EntityInterface<MaterialEntrada> {

    public MaterialEntrada() {
    }

    public MaterialEntrada(MaterialEntrada materialEntrada) {
        this.numeroEntrada = materialEntrada.getNumeroEntrada();
        this.centro = materialEntrada.getCentro();
        this.dataEntrada = materialEntrada.getDataEntrada();
        this.tipoMovimento = materialEntrada.getTipoMovimento();
        this.tipoDocumento = materialEntrada.getTipoDocumento();
        this.numeroDocumento = materialEntrada.getNumeroDocumento();
        this.diaEntrada = materialEntrada.getDiaEntrada();
        this.entradaOrigem = materialEntrada.getEntradaOrigem();
        this.itens = materialEntrada.getItens();
    }

    public MaterialEntrada(Long numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public MaterialEntrada(Long numeroEntrada, CentroCusto centro, Date dataEntrada, EnumTipoMovimentoMaterialEntrada tipoMovimento,
            String tipoDocumento, String numeroDocumento, String diaEntrada, EnumSistemaOrigemEstoque entradaOrigem,
            List<MaterialEntradaItens> itens) {
        this.numeroEntrada = numeroEntrada;
        this.centro = centro;
        this.dataEntrada = dataEntrada;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.diaEntrada = diaEntrada;
        this.entradaOrigem = entradaOrigem;
        this.itens = itens;
    }

    /**
     * Utiliza a ideia de Construtor para setar os atributos da entidade sem
     * precisar criar uma nova instância. Funciona como a função set executada
     * em lote.
     *
     * @param centro
     * @param dataEntrada
     * @param tipoMovimento
     * @param tipoDocumento
     * @param numeroDocumento
     * @param diaEntrada
     * @param entradaOrigem
     */
    public void rebuildFields(CentroCusto centro, Date dataEntrada, EnumTipoMovimentoMaterialEntrada tipoMovimento,
            String tipoDocumento, String numeroDocumento, String diaEntrada, EnumSistemaOrigemEstoque entradaOrigem,
            String usuarioInsercao, Date dataInsercao) {
        this.centro = centro;
        this.dataEntrada = dataEntrada;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.diaEntrada = diaEntrada;
        this.entradaOrigem = entradaOrigem;
        this.usuarioInsercao = usuarioInsercao;
        this.dataInsercao = dataInsercao;
    }
    /*
     */
    @Id
    @Column(name = "Entrada_Numero")
    private Long numeroEntrada;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "Entrada_Data")
    private Date dataEntrada;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Entrada_MovTipo")
    private EnumTipoMovimentoMaterialEntrada tipoMovimento;
    /*
     */
    @Column(name = "Entrada_DocTipo")
    private String tipoDocumento;
    /*
     */
    @Size(max = 7)
    @Column(name = "Entrada_DocNumero")
    private String numeroDocumento;
    /*
     */
    @Column(name = "Entrada_Dia")
    private String diaEntrada;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Entrada_Origem")
    private EnumSistemaOrigemEstoque entradaOrigem;
    /*
     */
    @Column(name="Audit_Insert_UserName")
    private String usuarioInsercao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name="Audit_Insert_Date")
    private Date dataInsercao;
    /*
     */
    @Column(name="Audit_UserName")
    private String usuarioAlteracao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name="Audit_LastChange")
    private Date dataAlteracao;
    /*
     */
    @OneToMany(targetEntity = MaterialEntradaItens.class, mappedBy = "materialEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialEntradaItens> itens;
    /*
     */
    @Transient
    private boolean itemRemovido = false;

    /**
     * Responde se a movimentação existente possui um item removido. Informado
     * ao remover um item.
     *
     * @return
     */
    public boolean isItemRemovido() {
        return itemRemovido;
    }

    public void setItemRemovido(boolean itemRemovido) {
        this.itemRemovido = itemRemovido;
    }

    @Override
    public Serializable getId() {
        return numeroEntrada;
    }

    @Override
    public String getLabel() {
        return numeroEntrada.toString();
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
    public int compareTo(MaterialEntrada o) {
        return this.getNumeroEntrada().compareTo(o.getNumeroEntrada());
    }

    public Long getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(Long numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public EnumTipoMovimentoMaterialEntrada getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(EnumTipoMovimentoMaterialEntrada tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<MaterialEntradaItens> getItens() {
        return itens;
    }

    public void setItens(List<MaterialEntradaItens> itens) {
        this.itens = itens;
    }

    public String getDiaEntrada() {
        return diaEntrada;
    }

    public void setDiaEntrada(String diaEntrada) {
        this.diaEntrada = diaEntrada;
    }

    public EnumSistemaOrigemEstoque getEntradaOrigem() {
        return entradaOrigem;
    }

    public void setEntradaOrigem(EnumSistemaOrigemEstoque entradaOrigem) {
        this.entradaOrigem = entradaOrigem;
    }

    public String getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(String usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    public Date getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
}
