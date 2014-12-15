/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.orcamento;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name="Projeto_ItemOrcPlan")
public class ProjetoItemOrcPlan implements EntityInterface<ProjetoItemOrcPlan>{
    
    @Id
    @Column(name="OrcPlan_Cod")
    private Integer orcPlanCod;
    /*
     */
    @Id
    @Column(name="ItemOrcPlan_Cod")
    private Integer itemOrcPlanCod;
    /*
     */
    @OneToMany(targetEntity = ProjetoItemOrcPlanIns.class, mappedBy = "projetoItemOrcPlan")
    private List<ProjetoItemOrcPlanIns> projetoItemOrcPlanIns;
    /*
     */
    @Column(name="Plan_Cod")
    private Integer planCod;

    public List<ProjetoItemOrcPlanIns> getProjetoItemOrcPlanIns() {
        return projetoItemOrcPlanIns;
    }

    public void setProjetoItemOrcPlanIns(List<ProjetoItemOrcPlanIns> projetoItemOrcPlanIns) {
        this.projetoItemOrcPlanIns = projetoItemOrcPlanIns;
    }

    public Integer getPlanCod() {
        return planCod;
    }

    public void setPlanCod(Integer planCod) {
        this.planCod = planCod;
    }

    @Override
    public Serializable getId() {
        return orcPlanCod  + "_" + itemOrcPlanCod;
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
        return "";
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
    public int compareTo(ProjetoItemOrcPlan o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }
    
}
