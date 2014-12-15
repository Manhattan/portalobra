/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.coleta;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name="Coleta_de_Preco_Itens")
@NamedQueries({
    @NamedQuery(name = "ColetaPrecoItem.find",
            query = " SELECT DISTINCT c FROM ColetaPrecoItem c "
            + " WHERE (c.coleta = :coletaNumero) "
            + " AND (c.numero = :coletaItemNumero) ")
})
public class ColetaPrecoItem implements EntityInterface<ColetaPrecoItem> {

    @Id
    @NotNull
    @Column(name = "ColetaItem_Numero", nullable = false)
    private Long numero;
    /**/
    @Id
    @ManyToOne(targetEntity = ColetaPreco.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Coleta_Numero", nullable = false)
    private ColetaPreco coleta;
    /**/
    @OneToOne(targetEntity = SolicitacaoCompraItem.class, fetch = FetchType.LAZY)
    @JoinColumns(value={
    @JoinColumn(name="SolicItem_Numero", referencedColumnName = "SolicItem_Numero"),
    @JoinColumn(name="Solic_Numero", referencedColumnName = "Solic_Numero")    
    })
    private SolicitacaoCompraItem solicitacaoItem;
    /**/
    @Size(max=3)
    @Column(name="ColetaItem_Item", nullable=false)
    private String itemItem;
    /**/
    @Size(max=5)
    @Column(name="Instrucao_Cod")
    private String instrucaoCodigo;
    /**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Instrucao_Data")
    private Date instrucaoData;
    /**/
    @Size(max=30)
    @Column(name="Usuario_Instrucao")
    private String usuarioInstrucao;
    /**/
    @OneToOne(targetEntity = Credor.class, fetch = FetchType.EAGER)
    @JoinColumn(name="Instrucao_Cre_Cod")
    private Credor credor;
    /**/
    @Size(max=30)
    @Column(name="Usuario_AutorizaInstrucao")
    private String usuarioAutorizaInstrucao;

    @Override
    public Serializable getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int compareTo(ColetaPrecoItem o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public ColetaPreco getColeta() {
        return coleta;
    }

    public void setColeta(ColetaPreco coleta) {
        this.coleta = coleta;
    }

    public SolicitacaoCompraItem getSolicitacaoItem() {
        return solicitacaoItem;
    }

    public void setSolicitacaoItem(SolicitacaoCompraItem solicitacaoItem) {
        this.solicitacaoItem = solicitacaoItem;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public String getInstrucaoCodigo() {
        return instrucaoCodigo;
    }

    public void setInstrucaoCodigo(String instrucaoCodigo) {
        this.instrucaoCodigo = instrucaoCodigo;
    }

    public Date getInstrucaoData() {
        return instrucaoData;
    }

    public void setInstrucaoData(Date instrucaoData) {
        this.instrucaoData = instrucaoData;
    }

    public String getUsuarioInstrucao() {
        return usuarioInstrucao;
    }

    public void setUsuarioInstrucao(String usuarioInstrucao) {
        this.usuarioInstrucao = usuarioInstrucao;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public String getUsuarioAutorizaInstrucao() {
        return usuarioAutorizaInstrucao;
    }

    public void setUsuarioAutorizaInstrucao(String usuarioAutorizaInstrucao) {
        this.usuarioAutorizaInstrucao = usuarioAutorizaInstrucao;
    }
    
    
}
