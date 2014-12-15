/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.followup;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.model.tipos.log_followup.EnumOpeLogFollowup;
import br.com.grupopibb.portalobra.model.tipos.log_followup.EnumTabLogFollowup;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Log_Followup")
public class LogFollowup implements EntityInterface<LogFollowup>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogF_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "LogF_Usuario")
    private String funcionario;
    /*
     */
    @Column(name = "LogF_Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    /*
     */
    @Column(name = "LogF_Operacao")
    @Enumerated(EnumType.STRING)
    private EnumOpeLogFollowup operacao;
    /*
     */
    @Column(name = "LogF_Tabela")
    @Enumerated(EnumType.STRING)
    private EnumTabLogFollowup tabela;
    /*
     */
    @Column(name = "LogF_Id_1")
    private Integer id1;
    /*
     */
    @Column(name = "LogF_Id_2")
    private Integer id2;
    /*
     */
    @OneToOne(targetEntity = CentroCusto.class)
    @JoinColumns({
        @JoinColumn(name = "LogF_Empresa_Cod", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "LogF_Filial_Cod", referencedColumnName = "Filial_Cod"),
        @JoinColumn(name = "LogF_Centro_Cod", referencedColumnName = "Centro_Cod")
    })
    private CentroCusto centro;
    /*
     */
    @Column(name = "LogF_Insumo_Cod")
    private Integer insumoCod;
    /*
     */
    @Column(name = "LogF_Lido")
    @Enumerated(EnumType.STRING)
    private EnumsGeral lido;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EnumOpeLogFollowup getOperacao() {
        return operacao;
    }

    public void setOperacao(EnumOpeLogFollowup operacao) {
        this.operacao = operacao;
    }

    public EnumTabLogFollowup getTabela() {
        return tabela;
    }

    public void setTabela(EnumTabLogFollowup tabela) {
        this.tabela = tabela;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Integer getInsumoCod() {
        return insumoCod;
    }

    public void setInsumoCod(Integer insumoCod) {
        this.insumoCod = insumoCod;
    }

    public EnumsGeral getLido() {
        return lido;
    }

    public void setLido(EnumsGeral lido) {
        this.lido = lido;
    }
    
    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return "T: " + tabela + " Op: " + operacao;        
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
    public int compareTo(LogFollowup o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
