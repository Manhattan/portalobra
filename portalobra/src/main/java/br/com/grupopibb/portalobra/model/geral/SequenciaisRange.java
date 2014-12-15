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
@Table(name = "SequenciaisRange")
@NamedQuery(name = "SequenciaisRange.findByName",
        query = " SELECT DISTINCT s FROM SequenciaisRange s "
        + " WHERE s.nomeTabela = :nomeTabela"
        + " AND s.range = :range")
public class SequenciaisRange implements EntityInterface<SequenciaisRange> {

    @Id
    @Column(name = "Seq_NomeTabela")
    private String nomeTabela;
    /*
     */
    @Id
    @Column(name = "Seq_Range")
    private String range;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
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
        return nomeTabela;
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
    public int compareTo(SequenciaisRange o) {
        return (this.getNomeTabela() + "_" + this.getRange()).compareTo(o.getNomeTabela() + "_" + o.getRange());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nomeTabela);
        hash = 97 * hash + Objects.hashCode(this.range);
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
        final SequenciaisRange other = (SequenciaisRange) obj;
        if (!Objects.equals(this.nomeTabela, other.nomeTabela)) {
            return false;
        }
        if (!Objects.equals(this.range, other.range)) {
            return false;
        }
        return true;
    }

    public SequenciaisRange initNumber(Long numero) {
        this.numero = numero;
        return this;
    }

    public SequenciaisRange initNumber(Integer numero) {
        this.numero = numero.longValue();
        return this;
    }
}
