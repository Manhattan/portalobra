/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.insumo;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Grupo_de_Insumos")
@NamedQueries({
    @NamedQuery(name = "GrupoInsumos.findByClasse",
            query = " SELECT DISTINCT g FROM GrupoInsumos g "
            + " WHERE g.classeCod = :classe "),
    @NamedQuery(name = "GrupoInsumos.findByClasseGrupo",
            query = " SELECT DISTINCT g FROM GrupoInsumos g "
            + " WHERE CONCAT(g.classeCod,g.codigo) = :classeGrupo")
})
public class GrupoInsumos implements EntityInterface<GrupoInsumos> {

    @Id
    @NotNull
    @Column(name = "GruIns_Cod")
    private String codigo;
    /*
     */
    @ManyToOne(targetEntity = ClasseInsumos.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ClaIns_Cod", insertable = false, updatable = false)
    private ClasseInsumos classeInsumos;
    /*
     */
    @Id
    @Column(name = "ClaIns_Cod")
    private String classeCod;
    /*
     */
    @Size(min = 1, max = 40)
    @Column(name = "GruIns_Titulo", nullable = false)
    private String titulo;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "GruIns_MostraNoOrcamento")
    private EnumsGeral mostraNoOrcamento;
    /*
     */
    @ManyToOne(targetEntity = ClasseOrcamentaria.class)
    @JoinColumn(name = "Classe_Cod")
    private ClasseOrcamentaria classeOrcamentaria;
    /*
     */
    @Size(max = 1)
    @Column(name = "GO_ES")
    private String goES;

    public String getTituloAbreviado() {
        return StringUtils.abbreviate(titulo, 30);
    }

    @Override
    public Serializable getId() {
        return classeCod + codigo;
    }

    /**
     * Muda o retorno do método getId() para String.
     *
     * @return Id em formato de String.
     */
    public String getStringId() {
        return getId().toString();
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
    public int compareTo(GrupoInsumos o) {
        return this.getStringId().compareToIgnoreCase(o.getStringId());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ClasseInsumos getClasseInsumos() {
        return classeInsumos;
    }

    public void setClasseInsumos(ClasseInsumos classeInsumos) {
        this.classeInsumos = classeInsumos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EnumsGeral getMostraNoOrcamento() {
        return mostraNoOrcamento;
    }

    public void setMostraNoOrcamento(EnumsGeral mostraNoOrcamento) {
        this.mostraNoOrcamento = mostraNoOrcamento;
    }

    public ClasseOrcamentaria getClasseOrcamentaria() {
        return classeOrcamentaria;
    }

    public void setClasseOrcamentaria(ClasseOrcamentaria classeOrcamentaria) {
        this.classeOrcamentaria = classeOrcamentaria;
    }

    public String getGoES() {
        return goES;
    }

    public void setGoES(String goES) {
        this.goES = goES;
    }

    public String getClasseCod() {
        return classeCod;
    }

    public void setClasseCod(String classeCod) {
        this.classeCod = classeCod;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        hash = 89 * hash + Objects.hashCode(this.classeInsumos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoInsumos other = (GrupoInsumos) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.classeInsumos, other.classeInsumos)) {
            return false;
        }
        return true;
    }

    public GrupoInsumos initClasse(String codigo, String titulo, String classeCod) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.classeCod = classeCod;
        return this;
    }
}
