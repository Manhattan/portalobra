/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.insumo;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Classe_de_Insumos")
public class ClasseInsumos implements EntityInterface<ClasseInsumos> {

    public ClasseInsumos() {
    }
    @Id
    @NotNull
    @Column(name = "ClaIns_Cod", nullable = false)
    private String codigo;
    /**/
    @Column(name = "ClaIns_Titulo", nullable = false)
    private String titulo;
    /**/
    @Column(name = "ClaIns_Material")
    private String material;
    /**/
    @OneToOne(targetEntity = ClasseInsumosOrcamento.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ClaInsOrc_Cod")
    private ClasseInsumosOrcamento classeInsumosOrcamento;
    /**/
    @Size(max = 1)
    @Column(name = "ClaIns_EStoque")
    private String estoque;

    public String getTituloAbreviado() {
        return StringUtils.abbreviate(titulo, 30);
    }

    @Override
    public Serializable getId() {
        return codigo;
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
    public int compareTo(ClasseInsumos o) {
        return this.getStringId().compareToIgnoreCase(o.getStringId());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ClasseInsumosOrcamento getClasseInsumosOrcamento() {
        return classeInsumosOrcamento;
    }

    public void setClasseInsumosOrcamento(ClasseInsumosOrcamento classeInsumosOrcamento) {
        this.classeInsumosOrcamento = classeInsumosOrcamento;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigo);
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
        final ClasseInsumos other = (ClasseInsumos) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public ClasseInsumos initClasse(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        return this;
    }
}
