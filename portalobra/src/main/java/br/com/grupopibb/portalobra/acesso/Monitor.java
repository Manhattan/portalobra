/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.tipos.EnumSistemaMonitor;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Monitor")
@NamedQueries({
    @NamedQuery(name = "Monitor.findActiveUsers",
            query = " SELECT DISTINCT m FROM Monitor m "
            + " WHERE m.dataSaida is null "
            + " ORDER BY m.codigo "),
    @NamedQuery(name = "Monitor.countActiveUsers",
            query = " SELECT COUNT(DISTINCT m) FROM Monitor m "
            + " WHERE m.dataSaida is null ")
})
public class Monitor implements EntityInterface<Monitor> {

    public Monitor() {
    }

    public Monitor(Funcionario funcionario, Date dataEntrada, EnumSistemaMonitor sistema, String host) {
        this.funcionario = funcionario;
        this.dataEntrada = dataEntrada;
        this.sistema = sistema;
        this.host = host;
    }

    public Monitor(Funcionario funcionario, Date dataEntrada, Date dataAtual, Date dataSaida, String host) {
        this.funcionario = funcionario;
        this.dataEntrada = dataEntrada;
        this.dataAtual = dataAtual;
        this.dataSaida = dataSaida;
        this.host = host;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Mon_Cod")
    private Long codigo;
    /*
     */
    @ManyToOne(targetEntity = Funcionario.class)
    @JoinColumn(name = "Fun_Cod")
    private Funcionario funcionario;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Mon_Data_Entrada")
    private Date dataEntrada;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Mon_Data_Atual")
    private Date dataAtual;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Mon_Data_Saida")
    private Date dataSaida;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name="Mon_Sistema")
    private EnumSistemaMonitor sistema;
    /*
     */
    @Column(name="Mon_Host")
    private String host;

    public int getDuracao() {
        return 0;
    }

    @Override
    public Serializable getId() {
        return this.funcionario.getCodigo() + "_" + this.dataEntrada;
    }

    @Override
    public String getLabel() {
        return this.funcionario + " - " + this.dataEntrada;
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
    public int compareTo(Monitor o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public EnumSistemaMonitor getSistema() {
        return sistema;
    }

    public void setSistema(EnumSistemaMonitor sistema) {
        this.sistema = sistema;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
}
