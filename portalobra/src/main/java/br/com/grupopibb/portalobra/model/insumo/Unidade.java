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
@Table(name="Unidade")
public class Unidade implements EntityInterface<Unidade> {

    public Unidade() {
    }
    
    @Id
    @NotNull
    @Column(name = "Unid_Cod", nullable = false)
    private String codigo;
    /**/
    @Size(min = 1, max = 100)
    @Column(name = "Unid_Titulo", nullable = false)
    private String titulo;
    /**/
    @Size(max = 10)
    @Column(name = "UNID")
    private String unidade;
    /**/
    @Size(max = 1)
    @Column(name = "Unid_IntFrac")
    private String inteiraFracionada;

    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.codigo;
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
    public int compareTo(Unidade o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getInteiraFracionada() {
        return inteiraFracionada;
    }

    public void setInteiraFracionada(String inteiraFracionada) {
        this.inteiraFracionada = inteiraFracionada;
    }

    @Override
    public String toString() {
        return codigo;
    }
    
    
}
