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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name="Classe_Orcamentaria_Tipo")
public class ClasseOrcamentariaTipo implements EntityInterface<ClasseOrcamentariaTipo>{
    
    @Id
    @NotNull
    @Column(name = "Classe_Tipo", nullable=false)
    private String tipo;
    /**/
    @Column(name="ClasseTipo_Titulo")
    private String titulo;

    @Override
    public Serializable getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int compareTo(ClasseOrcamentariaTipo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
