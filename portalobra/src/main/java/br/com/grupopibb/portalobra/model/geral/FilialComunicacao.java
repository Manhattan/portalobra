/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Filial_Comunicacao")
public class FilialComunicacao implements EntityInterface<FilialComunicacao> {

    @Id
    @Column(name = "Empresa_Cod")
    private String empresaCod;
    /*
     */
    @Id
    @Column(name = "Filial_Cod")
    private String filialCod;
    /*
     */
    @ManyToOne(targetEntity = Filial.class)
    @JoinColumns(value = {
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod", insertable = false, updatable = false)
    })
    private Filial filial;
    /*
     */
    @Id
    @Column(name = "FilialCom_Seq")
    private String sequencial;
    /*
     */
    @Column(name = "FilialCom_Departamento")
    private String departamento;
    /*
     */
    @Column(name = "FilialCom_Telefone")
    private String telefone;
    /*
     */
    @Column(name = "FilialCom_Fax")
    private String fax;
    /*
     */
    @Column(name = "FilialCom_Contato")
    private String contato;
    /*
     */
    @Column(name = "FilialCom_Observacao")
    private String observacao;
    /*
     */
    @Column(name = "FilialCom_eMail")
    private String email;
    /*
     */
    @Column(name = "FilialCom_CargoContato")
    private String cargo;

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public String getFilialCod() {
        return filialCod;
    }

    public void setFilialCod(String filialCod) {
        this.filialCod = filialCod;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getSequencial() {
        return sequencial;
    }

    public void setSequencial(String sequencial) {
        this.sequencial = sequencial;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public Serializable getId() {
        return this.empresaCod + this.filialCod + this.sequencial;
    }

    @Override
    public String getLabel() {
        return this.getContato();
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
    public int compareTo(FilialComunicacao o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }
}
