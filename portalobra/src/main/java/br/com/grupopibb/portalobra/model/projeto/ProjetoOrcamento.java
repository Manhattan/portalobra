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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Projeto_Orcamento")
public class ProjetoOrcamento implements EntityInterface<ProjetoOrcamento> {

    @Id
    @Column(name = "Orc_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Orc_Item")
    private Integer item;
    /*
     */
    @ManyToOne(targetEntity = Projeto.class)
    @JoinColumn(name = "Proj_Cod")
    private Projeto projeto;
    /*
     */
    @Column(name = "Orc_Titulo")
    private String titulo;
    /*
     */
    @Column(name = "Orc_Tipo", length = 1)
    private String tipo;
    /*
     */
    @Column(name = "Orc_Selecionado", length = 1)
    private String selecionado;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return titulo;
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
    public int compareTo(ProjetoOrcamento o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(String selecionado) {
        this.selecionado = selecionado;
    }
}
