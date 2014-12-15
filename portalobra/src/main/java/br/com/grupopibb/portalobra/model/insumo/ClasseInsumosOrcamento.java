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
import javax.validation.constraints.Size;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name="Classe_de_Insumos_Orcamento")
public class ClasseInsumosOrcamento implements EntityInterface<ClasseInsumosOrcamento> {

    @Id
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ClaInsOrc_Cod", nullable = false)
    private String codigo;
    /**/
    @Size(min = 1, max = 30)
    @Column(name = "ClaInsOrc_Titulo", nullable = false)
    private String titulo;
    /**/
    @Column(name = "ClaInsOrc_Ordem", nullable = false)
    private int ordem;

    @Override
    public Serializable getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int compareTo(ClasseInsumosOrcamento o) {
        return getLabel().compareToIgnoreCase(o.getLabel());
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

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
    
}
