/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.projeto;

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
@Table(name = "Projeto")
public class Projeto implements EntityInterface<Projeto> {

    @Id
    @Column(name = "Proj_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Proj_Nome")
    private String nome;
    /*
     */
    @OneToMany(targetEntity = ProjetoCentroCusto.class, mappedBy = "projeto")
    private List<ProjetoCentroCusto> projetoCentroCusto;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return nome;
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
    public int compareTo(Projeto o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProjetoCentroCusto> getProjetoCentroCusto() {
        return projetoCentroCusto;
    }

    public void setProjetoCentroCusto(List<ProjetoCentroCusto> projetoCentroCusto) {
        this.projetoCentroCusto = projetoCentroCusto;
    }
    
}
