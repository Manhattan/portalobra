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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Caracterizacao_de_Insumos")
@NamedQueries({
    @NamedQuery(name = "CaracterizacaoInsumos.findParam",
            query = " SELECT DISTINCT c FROM CaracterizacaoInsumos c "
            + " WHERE (c.grupoCod = :grupo) "
            + " AND (c.classeCod = :classe) "),
    @NamedQuery(name = "CaracterizacaoInsumos.findByClasseGrupoCarac",
        query = " SELECT DISTINCT c FROM CaracterizacaoInsumos c "
        + " WHERE CONCAT(c.classeCod, c.grupoCod, c.codigo) = :classeGrupoCarac")
})
public class CaracterizacaoInsumos implements EntityInterface<CaracterizacaoInsumos> {

    public CaracterizacaoInsumos() {
    }
    /**/
    @Id
    @NotNull
    @Column(name = "CarIns_Cod")
    private String codigo;
    /**/
    @Id
    @Column(name = "GruIns_Cod")
    private String grupoCod;
    /**/
    @Id
    @Column(name = "ClaIns_Cod")
    private String classeCod;
    /**/
    @ManyToOne(targetEntity = GrupoInsumos.class)
    @JoinColumns(value = {
        @JoinColumn(name = "GruIns_Cod", referencedColumnName = "GruIns_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "ClaIns_Cod", referencedColumnName = "ClaIns_Cod", insertable = false, updatable = false)
    })
    private GrupoInsumos grupoInsumos;
    /*
     */
    @Column(name = "CarIns_Titulo", nullable = false)
    private String titulo;
    
    public String getTituloAbreviado(){
        return StringUtils.abbreviate(titulo, 30);
    }

    @Override
    public Serializable getId() {
        return classeCod + grupoCod + codigo;
    }
    
    /**
     * Muda o retorno do método getId() para String.
     * @return Id em formato de String.
     */
    public String getStringId(){
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
    public int compareTo(CaracterizacaoInsumos o) {
        return this.getStringId().compareToIgnoreCase(o.getStringId());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public GrupoInsumos getGrupoInsumos() {
        return grupoInsumos;
    }

    public void setGrupoInsumos(GrupoInsumos grupoInsumos) {
        this.grupoInsumos = grupoInsumos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasseCod() {
        return classeCod;
    }

    public void setClasseCod(String classeCod) {
        this.classeCod = classeCod;
    }

    public String getGrupoCod() {
        return grupoCod;
    }

    public void setGrupoCod(String grupoCod) {
        this.grupoCod = grupoCod;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.codigo);
        hash = 13 * hash + Objects.hashCode(this.grupoCod);
        hash = 13 * hash + Objects.hashCode(this.classeCod);
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
        final CaracterizacaoInsumos other = (CaracterizacaoInsumos) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.grupoCod, other.grupoCod)) {
            return false;
        }
        if (!Objects.equals(this.classeCod, other.classeCod)) {
            return false;
        }
        return true;
    }

    public CaracterizacaoInsumos initClasse(String codigo, String titulo, String classeCod, String grupoCod) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.classeCod = classeCod;
        this.grupoCod = grupoCod;
        return this;
    }
}
