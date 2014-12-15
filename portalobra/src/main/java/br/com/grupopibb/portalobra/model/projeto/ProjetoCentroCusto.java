/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.projeto;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Projeto_CentrodeCusto")
public class ProjetoCentroCusto implements EntityInterface<ProjetoCentroCusto>{

    @Id
    @Column(name = "ProjCentro_Numero")
    private Integer numero;
    /*
     */
    @Id
    @ManyToOne(targetEntity = Projeto.class)
    @JoinColumn(name = "Proj_Cod")
    private Projeto projeto;
    /*
     */
    @OneToOne(targetEntity = CentroCusto.class)
    @JoinColumns({
        @JoinColumn(name="Empresa_Cod", referencedColumnName = "Empresa_Cod", columnDefinition = "char(3)"),
        @JoinColumn(name="Filial_Cod", referencedColumnName = "Filial_Cod", columnDefinition = "char(2)"),
        @JoinColumn(name="Centro_Cod", referencedColumnName = "Centro_Cod", columnDefinition = "char(4)")
    })
    private CentroCusto centro;

    @Override
    public Serializable getId() {
        return projeto.getId().toString() + numero.toString();
    }

    @Override
    public String getLabel() {
        return centro.getIdCompleto();
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
    public int compareTo(ProjetoCentroCusto o) {
        return getId().toString().compareTo(o.getId().toString());
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }
    
    
}
