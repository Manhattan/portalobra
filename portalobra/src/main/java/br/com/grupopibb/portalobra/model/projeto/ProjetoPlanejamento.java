/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.projeto;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Projeto_Planejamento")
@NamedQuery(name = "ProjetoPlanejamento.findByCentro",
        query = " SELECT DISTINCT pp FROM ProjetoPlanejamento pp "
        + " INNER JOIN pp.projetoOrcamento po "
        + " INNER JOIN po.projeto p "
        + " INNER JOIN p.projetoCentroCusto pc "
        + " WHERE (pc.centro = :centro) "
        + " AND (po.tipo = 'E') "
        + " AND (po.selecionado = 'S') ")
public class ProjetoPlanejamento implements EntityInterface<ProjetoPlanejamento> {

    @Id
    @Column(name = "Plan_Cod")
    private Integer codigo;
    /*
     */
    @OneToOne(targetEntity = ProjetoOrcamento.class)
    @JoinColumn(name = "Orc_Cod")
    private ProjetoOrcamento projetoOrcamento;
    /*
     */
    @Column(name = "Plan_Comentario")
    private String comentario;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return comentario;
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
    public int compareTo(ProjetoPlanejamento o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ProjetoOrcamento getProjetoOrcamento() {
        return projetoOrcamento;
    }

    public void setProjetoOrcamento(ProjetoOrcamento projetoOrcamento) {
        this.projetoOrcamento = projetoOrcamento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
