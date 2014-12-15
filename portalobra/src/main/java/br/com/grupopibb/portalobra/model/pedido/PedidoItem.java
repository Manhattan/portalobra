/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.pedido;

import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaItem;
import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Pedido_Item")
@NamedQueries({
    @NamedQuery(name = "PedidoItem.selectRange",
            query = " SELECT DISTINCT pi FROM PedidoItem pi "
            + " WHERE (:dataInicial2 = 'todos' OR :dataFinal2 = 'todos' OR pi.pedido.dataPedido BETWEEN :dataInicial AND :dataFinal) "
            + " AND (:especificacao2 = 'todos' OR pi.solicitacaoCompraItem.insumo.especificacao LIKE :especificacao) "
            + " AND (:numeroPedido2 = 'todos' OR pi.pedido.idSistema = :numeroPedido) "
            + " AND (:caracterizacao2 = 'todos' OR pi.solicitacaoCompraItem.insumo.caracterizacaoInsumo.titulo LIKE :caracterizacao) "
            + " AND (pi.pedido.centroCusto = :centro) "
            + " AND (:credor2 = 'todos' OR pi.pedido.credor = :credor) "
            + " ORDER BY pi.pedido.numero, pi.itemNumero DESC"),
    @NamedQuery(name = "PedidoItem.countRange",
            query = " SELECT COUNT(DISTINCT pi) FROM PedidoItem pi "
            + " WHERE (:dataInicial2 = 'todos' OR :dataFinal2 = 'todos' OR pi.pedido.dataPedido BETWEEN :dataInicial AND :dataFinal) "
            + " AND (:especificacao2 = 'todos' OR pi.solicitacaoCompraItem.insumo.especificacao LIKE :especificacao) "
            + " AND (:numeroPedido2 = 'todos' OR pi.pedido.idSistema = :numeroPedido) "
            + " AND (:caracterizacao2 = 'todos' OR pi.solicitacaoCompraItem.insumo.caracterizacaoInsumo.titulo like :caracterizacao) "
            + " AND (pi.pedido.centroCusto = :centro) "
            + " AND (:credor2 = 'todos' OR pi.pedido.credor = :credor) "),
//    @NamedQuery(name = "PedidoItem.findByInsumo",
//            query = " SELECT new br.com.grupopibb.portalobra.model.pedido.PedidoItem(pi.itemNumero, pi.pedido) FROM PedidoItem pi "
//            + " WHERE (:classeInsumo2 = 'todos' OR pi.solicitacaoCompraItem.insumo.classeCod = :classeInsumo) "
//            + " AND (:grupoInsumo2 = 'todos' OR pi.solicitacaoCompraItem.insumo.grupoCod = :grupoInsumo) "
//            + " AND (:caracterizacaoInsumo2 = 'todos' OR pi.solicitacaoCompraItem.insumo.caracterizacaoCod = :caracterizacaoInsumo) "),
    @NamedQuery(name = "PedidoItem.findOne",
            query = " SELECT DISTINCT pi FROM PedidoItem pi "
            + " WHERE (:pedido = pi.pedido) "
            + " AND (:itemNumero = pi.itemNumero) ")
})
public class PedidoItem implements EntityInterface<PedidoItem> {
 
    public PedidoItem() {
    }

    public PedidoItem(Integer itemNumero, Pedido pedido) {
        this.itemNumero = itemNumero;
        this.pedido = pedido;
    }

    public PedidoItem(DocumentoEntradaItem documentoEntradaItem, Integer itemNumero, Pedido pedido, String itemItem, Integer coletaItem, SolicitacaoCompraItem solicitacaoCompraItem, Float quantidade, Float precoInsumo, Date dataEntrega) {
        this.documentoEntradaItem = documentoEntradaItem;
        this.itemNumero = itemNumero;
        this.pedido = pedido;
        this.itemItem = itemItem;
        this.coletaItem = coletaItem;
        this.solicitacaoCompraItem = solicitacaoCompraItem;
        this.quantidade = quantidade;
        this.precoInsumo = precoInsumo;
        this.dataEntrega = dataEntrega;
    }
    @OneToOne(mappedBy = "pedidoItem", fetch = FetchType.LAZY)
    private DocumentoEntradaItem documentoEntradaItem;
    /*
     */
    @Id
    @NotNull
    @Column(name = "PedItem_Numero", nullable = false)
    private Integer itemNumero;
    /*
     */
    @Id
    @NotNull
    @ManyToOne(targetEntity = Pedido.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Pedido_Numero", nullable = false)
    private Pedido pedido;
    /*
     */
    @Column(name = "PedItem_Item", nullable = false)
    private String itemItem;
    /*
     */
    @Column(name = "ColetaItem_Numero")
    private Integer coletaItem;
    /*
     */
    @OneToOne(targetEntity = SolicitacaoCompraItem.class)
    @JoinColumns(value = {
        @JoinColumn(name = "Solic_Numero", referencedColumnName = "Solic_Numero"),
        @JoinColumn(name = "SolicItem_Numero", referencedColumnName = "SolicItem_Numero")
    })
    private SolicitacaoCompraItem solicitacaoCompraItem;
    /*
     */
    @Column(name = "PedItem_Quantidade", nullable = false)
    private Float quantidade;
    /*
     */
    @Column(name = "PedItem_PrecoInsumo", nullable = false)
    private Float precoInsumo;
    /*
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PedItem_DataEntrega")
    private Date dataEntrega;
    /*
     */
    @Column(name="PedItem_AliqIPI")
    private Float aliqIpi;
    /*
     */
    @Transient
    private boolean marcado;

    public String getIdComposto() {
        return pedido.getIdSistema().toString() + "/" + itemItem;
    }

    public Float getValorTotalItem() {
        return quantidade * precoInsumo;
    }

    public String getValorTotalItemFormatado() {
        return NumberUtils.formatCurrencyNoSymbol(getValorTotalItem());
    }

    public DocumentoEntradaItem getDocumentoEntradaItem() {
        return documentoEntradaItem;
    }

    public void setDocumentoEntradaItem(DocumentoEntradaItem documentoEntradaItem) {
        this.documentoEntradaItem = documentoEntradaItem;
    }

    public Integer getItemNumero() {
        return itemNumero;
    }

    public void setItemNumero(Integer itemNumero) {
        this.itemNumero = itemNumero;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public Integer getColetaItem() {
        return coletaItem;
    }

    public void setColetaItem(Integer coletaItem) {
        this.coletaItem = coletaItem;
    }

    public SolicitacaoCompraItem getSolicitacaoCompraItem() {
        return solicitacaoCompraItem;
    }

    public void setSolicitacaoCompraItem(SolicitacaoCompraItem solicitacaoCompraItem) {
        this.solicitacaoCompraItem = solicitacaoCompraItem;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPrecoInsumo() {
        return precoInsumo;
    }

    public void setPrecoInsumo(Float precoInsumo) {
        this.precoInsumo = precoInsumo;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Float getAliqIpi() {
        return aliqIpi;
    }

    public void setAliqIpi(Float aliqIpi) {
        this.aliqIpi = aliqIpi;
    }

    @Override
    public Serializable getId() {
        return pedido.getNumero().toString() + getItemNumero().toString();
    }

    /**
     * Muda o retorno do método getId() para String.
     *
     * @return Id em formato de String.
     */
    public String getStringId() {
        return getId().toString();
    }

    @Override
    public String getLabel() {
        return solicitacaoCompraItem.getInsumo().getEspecificacao();
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    @Override
    public int compareTo(PedidoItem o) {
        return (this.pedido.getNumero().toString() + this.getItemNumero()).compareTo(o.pedido.getNumero() + o.getItemItem());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.itemNumero);
        hash = 97 * hash + Objects.hashCode(this.pedido);
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
        final PedidoItem other = (PedidoItem) obj;
        if (!Objects.equals(this.itemNumero, other.itemNumero)) {
            return false;
        }
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        return true;
    }
}
