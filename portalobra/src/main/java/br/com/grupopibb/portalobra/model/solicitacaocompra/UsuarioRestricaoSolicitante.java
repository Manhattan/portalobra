/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.solicitacaocompra;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
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
@Table(name="Usuario_Restricao_Solicitante")
public class UsuarioRestricaoSolicitante implements EntityInterface<UsuarioRestricaoSolicitante>{

    public UsuarioRestricaoSolicitante() {
    }

    public UsuarioRestricaoSolicitante(Solicitante solicitanteParent, Solicitante solicitanteChild) {
        this.solicitanteParent = solicitanteParent;
        this.solicitanteChild = solicitanteChild;
    }
    
    @Id
    @ManyToOne(targetEntity = Solicitante.class)
    @JoinColumn(name="UserName", referencedColumnName = "Solicitante_Nome")
    private Solicitante solicitanteParent;
    /*
     */
    @Id
    @ManyToOne(targetEntity = Solicitante.class)
    @JoinColumn(name="Solicitante_Cod")
    private Solicitante solicitanteChild;

    public Solicitante getSolicitanteParent() {
        return solicitanteParent;
    }

    public void setSolicitanteParent(Solicitante solicitanteParent) {
        this.solicitanteParent = solicitanteParent;
    }

    public Solicitante getSolicitanteChild() {
        return solicitanteChild;
    }

    public void setSolicitanteChild(Solicitante solicitanteChild) {
        this.solicitanteChild = solicitanteChild;
    }

    @Override
    public Serializable getId() {
        return solicitanteParent.getNome() + "_" + solicitanteChild.getCodigo();
    }

    @Override
    public String getLabel() {
        return solicitanteParent.getNome() + "_" + solicitanteChild.getCodigo();
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
    public int compareTo(UsuarioRestricaoSolicitante o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.solicitanteParent);
        hash = 47 * hash + Objects.hashCode(this.solicitanteChild);
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
        final UsuarioRestricaoSolicitante other = (UsuarioRestricaoSolicitante) obj;
        if (!Objects.equals(this.solicitanteParent, other.solicitanteParent)) {
            return false;
        }
        if (!Objects.equals(this.solicitanteChild, other.solicitanteChild)) {
            return false;
        }
        return true;
    }
    
    
}
