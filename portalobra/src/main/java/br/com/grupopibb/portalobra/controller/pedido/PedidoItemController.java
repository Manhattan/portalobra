/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.pedido;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.pedido.PedidoItemFacade;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.pedido.Pedido;
import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tone.lima
 */
@ManagedBean
@ViewScoped
public class PedidoItemController extends EntityController<PedidoItem> implements Serializable {

    @EJB
    private PedidoItemFacade pedidoItemFacade;
    private PedidoItem current;
    private List<PedidoItem> itensSelecionados;
    private CentroCusto centroSelecionado;
    private Credor credor;
    private List<PedidoItem> pedidoItens;
    //Filtro de pesquisa dos itens do pedido
    private Date pedidoDataInicial;
    private Date pedidoDataFinal;
    private String caracterizacao;
    private String especificacao;
    private Integer numeroPedido;

    /**
     * Executado após o bean JSF ser criado.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Executado antes do bean JSF ser destruído.
     */
    @PreDestroy
    public void end() {
    }

    public void addOrRemoveItem(Pedido pedido, Integer itemNumero, Boolean marcado) {
        if (itensSelecionados == null) {
            itensSelecionados = new ArrayList<>();
        }
        PedidoItem pedItem = new PedidoItem(itemNumero, pedido);
        if (marcado) {
            itensSelecionados.add(pedItem);
        } else {
            itensSelecionados.remove(pedItem);
        }
    }
    
    public void limparItensSelecionados(){
        if (itensSelecionados != null){
        itensSelecionados.clear();
        }
        itensSelecionados = null;
    }

    /**
     * Inicia o Credor a ser gerada a AR.
     *
     * @param c Credor
     */
    public void selecionaCredor(Credor c) {
        this.credor = c;
        pesquisar(centroSelecionado);
    }

    @Override
    protected void setEntity(PedidoItem t) {
        current = t;
    }

    public PedidoItemFacade getFacade() {
        return pedidoItemFacade;
    }

    /**
     * Pesquisa os itens dos pedidos de acordo com os filtros informados.
     *
     * @param centro
     */
    public void pesquisar(CentroCusto c) {
        centroSelecionado = c;
        recreateTable();
    }

    @Override
    protected PedidoItem getNewEntity() {
        return new PedidoItem();
    }

    @Override
    protected void performDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().pesqCount(centroSelecionado, credor, pedidoDataInicial, pedidoDataFinal, especificacao, numeroPedido, caracterizacao).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listPesqParamRange(centroSelecionado, credor, pedidoDataInicial, pedidoDataFinal, especificacao, numeroPedido, caracterizacao, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public PedidoItem getCurrent() {
        return current;
    }

    public void setCurrent(PedidoItem current) {
        this.current = current;
    }

    public CentroCusto getCentroSelecionado() {
        return centroSelecionado;
    }

    public void setCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
    }

    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public Date getPedidoDataInicial() {
        return pedidoDataInicial;
    }

    public void setPedidoDataInicial(Date pedidoDataInicial) {
        this.pedidoDataInicial = pedidoDataInicial;
    }

    public Date getPedidoDataFinal() {
        return pedidoDataFinal;
    }

    public void setPedidoDataFinal(Date pedidoDataFinal) {
        this.pedidoDataFinal = pedidoDataFinal;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public List<PedidoItem> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<PedidoItem> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }
        
}
