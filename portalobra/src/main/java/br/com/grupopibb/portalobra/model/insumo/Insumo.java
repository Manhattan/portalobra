/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.insumo;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.orcamento.ProjetoItemOrcPlanIns;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItemOrcPlan;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Insumo")
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll",
            query = " SELECT DISTINCT i FROM Insumo i "
            + " WHERE (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "),
    @NamedQuery(name = "Insumo.find",
            query = " SELECT DISTINCT i FROM Insumo i "
            + " WHERE (i.codigo = :id) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "),
    @NamedQuery(name = "Insumo.findParam",
            query = " SELECT DISTINCT i FROM Insumo i "
            + " WHERE (:codigo2 = 'todos' OR i.codigo LIKE :codigo) "
            + " AND (:especificacao2 = 'todos' OR i.especificacao LIKE :especificacao) "
            + " AND (:classe2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.classeInsumos.codigo = :classe) "
            + " AND (:grupo2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.codigo = :grupo) "
            + " AND (:caracterizacao2 = 'todos' OR i.caracterizacaoInsumo.codigo = :caracterizacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "),
    @NamedQuery(name = "Insumo.countParam",
            query = " SELECT COUNT(DISTINCT i) FROM Insumo i "
            + " WHERE (:codigo2 = 'todos' OR i.codigo LIKE :codigo) "
            + " AND (:especificacao2 = 'todos' OR i.especificacao LIKE :especificacao) "
            + " AND (:classe2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.classeInsumos.codigo = :classe) "
            + " AND (:grupo2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.codigo = :grupo) "
            + " AND (:caracterizacao2 = 'todos' OR i.caracterizacaoInsumo.codigo = :caracterizacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "),
    @NamedQuery(name = "Insumo.findParamOrc",
            query = " SELECT DISTINCT i FROM Insumo i join i.projetoItemOrcPlanIns orcIns join orcIns.projetoItemOrcPlan orc join i.caracterizacaoInsumo.grupoInsumos.classeInsumos.classeInsumosOrcamento classeOrc"
            + " WHERE (:codigo2 = 'todos' OR i.codigo LIKE :codigo) "
            + " AND (:especificacao2 = 'todos' OR i.especificacao LIKE :especificacao) "
            + " AND (:classe2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.classeInsumos.codigo = :classe) "
            + " AND (:grupo2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.codigo = :grupo) "
            + " AND (:caracterizacao2 = 'todos' OR i.caracterizacaoInsumo.codigo = :caracterizacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "
            + " AND (orcIns.orcPlanCod = 0) "
            + " AND (orc.planCod = :planCod) "
            + " AND (classeOrc.codigo <> 'T')"),
    @NamedQuery(name = "Insumo.countParamOrc",
            query = " SELECT COUNT(DISTINCT i) FROM Insumo i join i.projetoItemOrcPlanIns orcIns join orcIns.projetoItemOrcPlan orc join i.caracterizacaoInsumo.grupoInsumos.classeInsumos.classeInsumosOrcamento classeOrc"
            + " WHERE (:codigo2 = 'todos' OR i.codigo LIKE :codigo) "
            + " AND (:especificacao2 = 'todos' OR i.especificacao LIKE :especificacao) "
            + " AND (:classe2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.classeInsumos.codigo = :classe) "
            + " AND (:grupo2 = 'todos' OR i.caracterizacaoInsumo.grupoInsumos.codigo = :grupo) "
            + " AND (:caracterizacao2 = 'todos' OR i.caracterizacaoInsumo.codigo = :caracterizacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "
            + " AND (orcIns.orcPlanCod = 0) "
            + " AND (orc.planCod = :planCod) "
            + " AND (classeOrc.codigo <> 'T') "),
    @NamedQuery(name = "Insumo.findByInsumo",
            query = " SELECT DISTINCT i FROM Insumo i "
            + " WHERE (:codigo2 = 'todos' OR i.codigo LIKE :codigo) "
            + " AND (:especificacao2 = 'todos' OR i.especificacao LIKE :especificacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) "),
    @NamedQuery(name = "Insumo.findByOutros",
            query = " SELECT DISTINCT i FROM Insumo i "
            + " WHERE (:classe2 = 'todos' OR i.classeCod = :classe) "
            + " AND (:grupo2 = 'todos' OR i.grupoCod = :grupo) "
            + " AND (:caracterizacao2 = 'todos' OR i.caracterizacaoCod = :caracterizacao) "
            + " AND (i.itemObsoleto = 'N' OR i.itemObsoleto IS NULL) ")
})
//(:insumoCod2 = 'todos' OR f.insumoCod like :insumoCod)
public class Insumo implements EntityInterface<Insumo> {

    @Id
    @NotNull
    @Column(name = "Insumo_Cod", nullable = false)
    private Long codigo;
    /*
     */
    @Column(name = "Insumo_Especificacao", nullable = false)
    private String especificacao;
    /*
     */
    @OneToOne(targetEntity = Unidade.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Unid_Cod", nullable = false)
    private Unidade unidade;
    /*
     */
    @ManyToOne(targetEntity = CaracterizacaoInsumos.class, fetch = FetchType.EAGER)
    @JoinColumns(value = {
        @JoinColumn(name = "GruIns_Cod", referencedColumnName = "GruIns_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "ClaIns_Cod", referencedColumnName = "ClaIns_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "CarIns_Cod", referencedColumnName = "CarIns_Cod", insertable = false, updatable = false)
    })
    private CaracterizacaoInsumos caracterizacaoInsumo;
    /*
     */
    @Column(name = "CarIns_Cod")
    private String caracterizacaoCod;
    /*
     */
    @Column(name = "GruIns_Cod")
    private String grupoCod;
    /*
     */
    @Column(name = "ClaIns_Cod")
    private String classeCod;
    /*
     */
    @NotNull
    @Column(name = "INSUMO_ITEMOBSOLETO")
    private String itemObsoleto;
    /*
     */
    @Transient
    private Boolean marcado = false;
    /*
     */
    @Transient
    private List<SolicitacaoCompraItemOrcPlan> itensPlanoOrcamento;
    /*
     */
    @OneToMany(targetEntity = ProjetoItemOrcPlanIns.class, mappedBy = "insumo", fetch = FetchType.LAZY)
    private List<ProjetoItemOrcPlanIns> projetoItemOrcPlanIns;

    /**
     * Verifica se o Insumo atual gera estoque ou não.
     *
     * @return True ou false.
     */
    public boolean getGeraEstoque() {
        try {
            if (this.getCaracterizacaoInsumo() == null || this.getCaracterizacaoInsumo().getGrupoInsumos() == null || this.getCaracterizacaoInsumo().getGrupoInsumos().getClasseInsumos() == null) {
                return false;
            } else if (this.getCaracterizacaoInsumo().getGrupoInsumos().getClasseInsumos().getEstoque() != null && this.getCaracterizacaoInsumo().getGrupoInsumos().getClasseInsumos().getEstoque().equals("S")) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public String getEspecificacaoAbrev() {
        return StringUtils.abbreviate(especificacao, 30);
    }

    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.especificacao;
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return this.marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    @Override
    public int compareTo(Insumo o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public CaracterizacaoInsumos getCaracterizacaoInsumo() {
        return caracterizacaoInsumo;
    }

    public void setCaracterizacaoInsumo(CaracterizacaoInsumos caracterizacaoInsumo) {
        this.caracterizacaoInsumo = caracterizacaoInsumo;
    }

    public String getItemObsoleto() {
        return itemObsoleto;
    }

    public void setItemObsoleto(String itemObsoleto) {
        this.itemObsoleto = itemObsoleto;
    }

    public String getCaracterizacaoCod() {
        return caracterizacaoCod;
    }

    public void setCaracterizacaoCod(String caracterizacaoCod) {
        this.caracterizacaoCod = caracterizacaoCod;
    }

    public String getGrupoCod() {
        return grupoCod;
    }

    public void setGrupoCod(String grupoCod) {
        this.grupoCod = grupoCod;
    }

    public String getClasseCod() {
        return classeCod;
    }

    public void setClasseCod(String classeCod) {
        this.classeCod = classeCod;
    }

    public List<SolicitacaoCompraItemOrcPlan> getItensPlanoOrcamento() {
        return itensPlanoOrcamento;
    }

    public void setItensPlanoOrcamento(List<SolicitacaoCompraItemOrcPlan> itensPlanoOrcamento) {
        this.itensPlanoOrcamento = itensPlanoOrcamento;
    }

    public List<ProjetoItemOrcPlanIns> getProjetoItemOrcPlanIns() {
        return projetoItemOrcPlanIns;
    }

    public void setProjetoItemOrcPlanIns(List<ProjetoItemOrcPlanIns> projetoItemOrcPlanIns) {
        this.projetoItemOrcPlanIns = projetoItemOrcPlanIns;
    }

    @Override
    public String toString() {
        return codigo.toString() + " - " + especificacao + " - " + unidade.getCodigo();
    }
}