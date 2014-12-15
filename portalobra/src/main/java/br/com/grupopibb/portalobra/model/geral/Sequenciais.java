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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Sequenciais")
@NamedQuery(name = "Sequenciais.findByName",
        query = " SELECT DISTINCT s FROM Sequenciais s "
        + " WHERE s.nomeTabela = :nomeTabela")
public class Sequenciais implements EntityInterface<Sequenciais> {

    public Sequenciais() {
    }

    public Sequenciais(String nomeTabela, Long numero) {
        this.nomeTabela = nomeTabela;
        this.numero = numero;
    }

    @Id
    @Column(name = "Seq_NomeTabela")
    private String nomeTabela;
    /*
     */
    @Column(name = "Seq_Numero")
    private Long numero;

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public Serializable getId() {
        return nomeTabela;
    }

    @Override
    public String getLabel() {
        return numero.toString();
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
    public int compareTo(Sequenciais o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nomeTabela);
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
        final Sequenciais other = (Sequenciais) obj;
        if (!Objects.equals(this.nomeTabela, other.nomeTabela)) {
            return false;
        }
        return true;
    }
    
    public Sequenciais initNumber(Long numero){
        this.numero = numero;
        return this;
    }
    
    public Sequenciais initNumber(Integer numero){
        this.numero = numero.longValue();
        return this;
    }
}
