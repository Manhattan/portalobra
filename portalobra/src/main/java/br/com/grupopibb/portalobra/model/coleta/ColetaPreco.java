/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.coleta;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author tone.lima
 */
@Entity
@Table(name="Coleta_de_Preco")
public class ColetaPreco implements EntityInterface<ColetaPreco> {

    @Id
    @NotNull
    @Column(name = "Coleta_Numero", nullable = false)
    private Long numero;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Coleta_Data")
    private Date data;
    /*
     */
    @Column(name = "Coleta_Taxa")
    private Double taxa;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Coleta_Concluido")
    private EnumsGeral concluido;
    /*
     */
    @Column(name = "Coleta_Obs")
    private String observacao;
    /*
     */
    @Size(max = 40)
    @Column(name = "Usuario_Instrucao_Edit")
    private String usuarioInstrucaoEdit;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Instrucao_Data_Edit")
    private Date instrucaoDataEdit;
    /*
     */
    @Size(max = 40)
    @Column(name = "Coleta_UsuarioConcluido")
    private String usuarioConcluido;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Coleta_DataConcluido")
    private Date dataConcluido;
    /*
     */
    @OneToMany(targetEntity = ColetaPrecoItem.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Coleta_Numero")
    private List<ColetaPrecoItem> itens;

    @Override
    public Serializable getId() {
        return numero;
    }

    @Override
    public String getLabel() {
        return String.valueOf(numero) + String.valueOf(data);
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
    public int compareTo(ColetaPreco o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public EnumsGeral getConcluido() {
        return concluido;
    }

    public void setConcluido(EnumsGeral concluido) {
        this.concluido = concluido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUsuarioInstrucaoEdit() {
        return usuarioInstrucaoEdit;
    }

    public void setUsuarioInstrucaoEdit(String usuarioInstrucaoEdit) {
        this.usuarioInstrucaoEdit = usuarioInstrucaoEdit;
    }

    public Date getInstrucaoDataEdit() {
        return instrucaoDataEdit;
    }

    public void setInstrucaoDataEdit(Date instrucaoDataEdit) {
        this.instrucaoDataEdit = instrucaoDataEdit;
    }

    public String getUsuarioConcluido() {
        return usuarioConcluido;
    }

    public void setUsuarioConcluido(String usuarioConcluido) {
        this.usuarioConcluido = usuarioConcluido;
    }

    public Date getDataConcluido() {
        return dataConcluido;
    }

    public void setDataConcluido(Date dataConcluido) {
        this.dataConcluido = dataConcluido;
    }

    public List<ColetaPrecoItem> getItens() {
        return itens;
    }

    public void setItens(List<ColetaPrecoItem> itens) {
        this.itens = itens;
    }
    
    
}
