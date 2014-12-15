/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.insumo;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name="Classe_Orcamentaria")
public class ClasseOrcamentaria implements EntityInterface<ClasseOrcamentaria>{

    
    @Id
    @NotNull
    @Column(name="Classe_Cod", nullable = false, length = 2)
    private String codigo;
    /**/
    @Column(name="Classe_Titulo", length = 35)
    private String titulo;
    /**/
    @ManyToOne(targetEntity = ClasseOrcamentariaTipo.class)
    @JoinColumn(name="Classe_Tipo")
    private ClasseOrcamentariaTipo tipo;
    /**/
    @Column(name="Classe_OpcoesEspCentro", length = 240)
    private String opcoesEspecieCentro;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMarcado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(ClasseOrcamentaria o) {
        return getLabel().compareToIgnoreCase(o.getLabel());
    }
}
