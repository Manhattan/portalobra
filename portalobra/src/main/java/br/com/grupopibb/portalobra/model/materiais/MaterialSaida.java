/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.materiais;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "MateriaisSaida")
@NamedQueries({
    @NamedQuery(name = "MaterialSaida.selectRange",
            query = " SELECT DISTINCT m FROM MaterialSaida m "
            + " WHERE ( :numeroSaida2 = 'todos' OR m.numeroSaida = :numeroSaida ) "
            + " AND ( m.centro = :centro ) "
            + " AND ( m.dataSaida BETWEEN :dataInicial AND :dataFinal ) "
            + " AND ( :numeroSaida3 = 'todos' OR m.numeroSaida IN :numeros ) "
            + " ORDER BY m.numeroSaida DESC "),
    @NamedQuery(name = "MaterialSaida.countRange",
            query = " SELECT COUNT(DISTINCT m) FROM MaterialSaida m "
            + " WHERE ( :numeroSaida2 = 'todos' OR m.numeroSaida = :numeroSaida ) "
            + " AND ( m.centro = :centro ) "
            + " AND ( m.dataSaida BETWEEN :dataInicial AND :dataFinal ) "
            + " AND ( :numeroSaida3 = 'todos' OR m.numeroSaida IN :numeros ) "),
    @NamedQuery(name = "MaterialSaida.selectMaxDepositoNumero",
            query = " SELECT MAX(m.numeroDeposito) FROM MaterialSaida m "
            + " WHERE ( m.tipoMovimento = 'T' )")
})
public class MaterialSaida implements EntityInterface<MaterialSaida> {

    public MaterialSaida() {
    }

    public MaterialSaida(MaterialSaida materialSaida) {
        this.numeroSaida = materialSaida.getNumeroSaida();
        this.centro = materialSaida.getCentro();
        this.dataSaida = materialSaida.getDataSaida();
        this.tipoMovimento = materialSaida.getTipoMovimento();
        this.tipoDocumento = materialSaida.getTipoDocumento();
        this.numeroDocumento = materialSaida.getNumeroDocumento();
        this.diaSaida = materialSaida.getDiaSaida();
        this.numeroDeposito = materialSaida.getNumeroDeposito();
        this.centroDestino = materialSaida.getCentroDestino();
        this.itens = materialSaida.getItens();

        if (centroCodDest != null) {
            this.centroCodDest = centroDestino.getCodigo();
            this.filialCodDest = centroDestino.getFilialCod();
            this.empresaCodDest = centroDestino.getEmpresaCod();
        } else {
            this.centroCodDest = "";
            this.filialCodDest = "";
            this.empresaCodDest = "";
        }
    }

    public MaterialSaida(Long numeroSaida, CentroCusto centro, Date dataSaida, String tipoMovimento, String tipoDocumento, String numeroDocumento, String diaSaida, Integer numeroDeposito, CentroCusto centroDestino, List<MaterialSaidaItens> itens) {
        this.numeroSaida = numeroSaida;
        this.centro = centro;
        this.dataSaida = dataSaida;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.diaSaida = diaSaida;
        this.numeroDeposito = numeroDeposito;
        this.centroDestino = centroDestino;
        this.itens = itens;

        this.centroCodDest = centroDestino.getCodigo();
        this.filialCodDest = centroDestino.getFilialCod();
        this.empresaCodDest = centroDestino.getEmpresaCod();
    }

    public void rebuildFields(CentroCusto centro, Date dataSaida, String tipoMovimento,
            String tipoDocumento, String numeroDocumento, String diaSaida, Integer numeroDeposito, CentroCusto centroDestino) {
        this.centro = centro;
        this.dataSaida = dataSaida;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.diaSaida = diaSaida;
        this.numeroDeposito = numeroDeposito;
        this.centroDestino = centroDestino;

        this.centroCodDest = centroDestino.getCodigo();
        this.filialCodDest = centroDestino.getFilialCod();
        this.empresaCodDest = centroDestino.getEmpresaCod();
    }

    public MaterialSaida(Long numeroSaida) {
        this.numeroSaida = numeroSaida;
    }

    public void rebuildFields(CentroCusto centro, Date dataSaida, String tipoMovimento,
            String tipoDocumento, String numeroDocumento, String diaSaida, String usuarioInsercao, Date dataInsercao) {
        this.centro = centro;
        this.dataSaida = dataSaida;
        this.tipoMovimento = tipoMovimento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.diaSaida = diaSaida;
        this.usuarioInsercao = usuarioInsercao;
        this.dataInsercao = dataInsercao;
    }
    @Id
    @Column(name = "Saida_Numero")
    private Long numeroSaida;
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
    @Column(name = "Saida_Data")
    private Date dataSaida;
    /*
     */
    @Column(name = "Saida_MovTipo")
    private String tipoMovimento;
    /*
     */
    @Column(name = "Saida_DocTipo")
    private String tipoDocumento;
    /*
     */
    @Column(name = "Saida_DocNumero")
    private String numeroDocumento;
    /*
     */
    @Column(name = "Saida_Dia")
    private String diaSaida;
    /*
     */
    @Column(name = "Saida_DepositoNumero")
    private Integer numeroDeposito;
    /*
     */
    @ManyToOne(targetEntity = CentroCusto.class)
    @JoinColumns({
        @JoinColumn(name = "Empresa_Cod_Dest", referencedColumnName = "Empresa_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "Filial_Cod_Dest", referencedColumnName = "Filial_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "Centro_Cod_Dest", referencedColumnName = "Centro_Cod", insertable = false, updatable = false)
    })
    private CentroCusto centroDestino;
    /*
     */
    @Column(name = "Centro_Cod_Dest")
    private String centroCodDest;
    /*
     */
    @Column(name = "Filial_Cod_Dest")
    private String filialCodDest;
    /*
     */
    @Column(name = "Empresa_Cod_Dest")
    private String empresaCodDest;
    /*
     */
    @OneToMany(targetEntity = MaterialSaidaItens.class, mappedBy = "materialSaida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialSaidaItens> itens;
    /*
     */
    @Column(name = "Audit_Insert_UserName")
    private String usuarioInsercao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Audit_Insert_Date")
    private Date dataInsercao;
    /*
     */
    @Column(name = "Audit_UserName")
    private String usuarioAlteracao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Audit_LastChange")
    private Date dataAlteracao;
    /*
     */
    @Transient
    private boolean itemRemovido = false;

    /**
     * Calcula o custo total da saída de material, através da multiplicação da
     * quantidade de itens solicitados pelo valor unitário de cada item.
     *
     * @return Double com o custo total da saída.
     */
    public Double getCustoTotal() {
        if (itens == null || itens.isEmpty()) {
            return 0.0;
        } else {
            Double custoTotal = 0.0;
            for (MaterialSaidaItens item : itens) {
                custoTotal += NumberUtils.isNull(item.getValor(), 0.0);
            }
            return custoTotal;
        }
    }

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
        return numeroSaida;
    }

    @Override
    public String getLabel() {
        return numeroSaida.toString();
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
    public int compareTo(MaterialSaida o) {
        return getId().toString().compareToIgnoreCase(o.getId().toString());
    }

    public Long getNumeroSaida() {
        return numeroSaida;
    }

    public void setNumeroSaida(Long numeroSaida) {
        this.numeroSaida = numeroSaida;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
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

    public String getDiaSaida() {
        return diaSaida;
    }

    public void setDiaSaida(String diaSaida) {
        this.diaSaida = diaSaida;
    }

    public Integer getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(Integer numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public List<MaterialSaidaItens> getItens() {
        return itens;
    }

    public void setItens(List<MaterialSaidaItens> itens) {
        this.itens = itens;
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

    public CentroCusto getCentroDestino() {
        return centroDestino;
    }

    public void setCentroDestino(CentroCusto centroDestino) {
        this.centroDestino = centroDestino;
    }

    public String getCentroCodDest() {
        return centroCodDest;
    }

    public void setCentroCodDest(String centroCodDest) {
        this.centroCodDest = centroCodDest;
    }

    public String getFilialCodDest() {
        return filialCodDest;
    }

    public void setFilialCodDest(String filialCodDest) {
        this.filialCodDest = filialCodDest;
    }

    public String getEmpresaCodDest() {
        return empresaCodDest;
    }

    public void setEmpresaCodDest(String empresaCodDest) {
        this.empresaCodDest = empresaCodDest;
    }
}
