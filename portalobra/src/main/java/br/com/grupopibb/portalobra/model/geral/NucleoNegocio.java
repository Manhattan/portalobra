/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Nucleo_de_Negocio")
public class NucleoNegocio implements EntityInterface<NucleoNegocio> {

    @Id
    @Column(name="Nucleo_Cod")
    private String codigo;
    /*
     */
    @Column(name="Nucleo_Nome")
    private String nome;

    
    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.codigo + " - " + this.nome;
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
    public int compareTo(NucleoNegocio o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
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
        final NucleoNegocio other = (NucleoNegocio) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
