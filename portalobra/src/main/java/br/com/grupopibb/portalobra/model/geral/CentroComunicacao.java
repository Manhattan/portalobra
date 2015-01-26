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
 * @author tone.lima
 */
@Entity
@Table(name = "CentroComunicacao")
public class CentroComunicacao implements EntityInterface<CentroComunicacao> {

    public CentroComunicacao() {
    }

    public CentroComunicacao(String departamento, String telefone, String contato, String email, String cargo) {
        this.departamento = departamento;
        this.telefone = telefone;
        this.contato = contato;
        this.email = email;
        this.cargo = cargo;
    }

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
    @Id
    @Column(name = "Centro_Cod")
    private String centroCod;
    /*
     */
    @Id
    @Column(name = "CenCom_Seq")
    private String sequencial;
    /*
     */
    @ManyToOne(targetEntity = CentroCusto.class)
    @JoinColumns(value = {
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod", insertable = false, updatable = false)
    })
    private CentroCusto centro;
    /*
     */
    @Column(name = "CenCom_Departamento")
    private String departamento;
    /*
     */
    @Column(name = "CenCom_Telefone")
    private String telefone;
    /*
     */
    @Column(name = "CenCom_Fax")
    private String fax;
    /*
     */
    @Column(name = "CenCom_Contato")
    private String contato;
    /*
     */
    @Column(name = "CenCom_Observacao")
    private String observacao;
    /*
     */
    @Column(name = "CenCom_eMail")
    private String email;
    /*
     */
    @Column(name = "CenCom_CargoContato")
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

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public String getSequencial() {
        return sequencial;
    }

    public void setSequencial(String sequencial) {
        this.sequencial = sequencial;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
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
        return empresaCod + filialCod + centroCod + sequencial;
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
        return false;
    }

    @Override
    public int compareTo(CentroComunicacao o) {
        return getId().toString().compareTo(o.getId().toString());
    }
}
