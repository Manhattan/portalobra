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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name="CredorComunicacao")
public class CredorComunicacao implements EntityInterface<CredorComunicacao>{
    
    @Id
    @ManyToOne(targetEntity = Credor.class)
    @JoinColumn(name="Cre_Cod")
    private Credor credor;
    /*
     */
    @Id
    @Column(name="CreCom_Seq")
    private String sequencia;
    /*
     */
    @Column(name="CreCom_Departamento")
    private String departamento;
    /*
     */
    @Column(name="CreCom_Telefone")
    private String telefone;
    /*
     */
    @Column(name="CreCom_Fax")
    private String fax;
    /*
     */
    @Column(name="CreCom_Contato")
    private String contato;
    /*
     */
    @Column(name="CreCom_Observacao")
    private String observacao;
    /*
     */
    @Column(name="CreCom_CargoContato")
    private String cargoContato;
    /*
     */
    @Column(name="CreCom_eMail")
    private String email;

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCargoContato() {
        return cargoContato;
    }

    public void setCargoContato(String cargoContato) {
        this.cargoContato = cargoContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
    public Serializable getId() {
        return sequencia;
    }

    @Override
    public String getLabel() {
        return contato;
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(CredorComunicacao o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.credor);
        hash = 29 * hash + Objects.hashCode(this.sequencia);
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
        final CredorComunicacao other = (CredorComunicacao) obj;
        if (!Objects.equals(this.credor, other.credor)) {
            return false;
        }
        if (!Objects.equals(this.sequencia, other.sequencia)) {
            return false;
        }
        return true;
    }
    
        
}
