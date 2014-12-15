/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.orcamento;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Projeto_ItemOrcPlanIns")
public class ProjetoItemOrcPlanIns implements EntityInterface<ProjetoItemOrcPlanIns> {

    @Id
    @Column(name = "OrcPlan_Cod")
    private Integer orcPlanCod;
    /*
     */
    @Id
    @Column(name = "ItemOrcPlan_Cod")
    private Integer itemOrcPlanCod;
    /*
     */
    @ManyToOne(targetEntity = ProjetoItemOrcPlan.class)
    @JoinColumns(value = {
        @JoinColumn(name = "OrcPlan_Cod", referencedColumnName = "OrcPlan_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "ItemOrcPlan_Cod", referencedColumnName = "ItemOrcPlan_Cod", insertable = false, updatable = false)
    })
    private ProjetoItemOrcPlan projetoItemOrcPlan;
    /*
     */
    @Id
    @ManyToOne(targetEntity = Insumo.class)
    @JoinColumn(name = "Insumo_Cod")
    private Insumo insumo;

    public ProjetoItemOrcPlan getProjetoItemOrcPlan() {
        return projetoItemOrcPlan;
    }

    public void setProjetoItemOrcPlan(ProjetoItemOrcPlan projetoItemOrcPlan) {
        this.projetoItemOrcPlan = projetoItemOrcPlan;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    @Override
    public Serializable getId() {
        return projetoItemOrcPlan;
    }

    public Integer getOrcPlanCod() {
        return orcPlanCod;
    }

    public void setOrcPlanCod(Integer orcPlanCod) {
        this.orcPlanCod = orcPlanCod;
    }

    public Integer getItemOrcPlanCod() {
        return itemOrcPlanCod;
    }

    public void setItemOrcPlanCod(Integer itemOrcPlanCod) {
        this.itemOrcPlanCod = itemOrcPlanCod;
    }

    @Override
    public String getLabel() {
        return getId().toString();
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
    public int compareTo(ProjetoItemOrcPlanIns o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }
}
