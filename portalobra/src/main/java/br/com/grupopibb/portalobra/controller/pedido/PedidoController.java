/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.pedido;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.pedido.PedidoFacade;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaAvaliacao;
import br.com.grupopibb.portalobra.model.pedido.Pedido;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author tone.lima
 */
@ManagedBean
@ViewScoped
public class PedidoController extends EntityController<Pedido> implements Serializable {

    private Pedido current;

    @PostConstruct
    public void init() {
    }
    
    @Override
    protected void setEntity(Pedido t) {
        this.current = t;
    }

    @Override
    protected Pedido getNewEntity() {
        return new Pedido();
    }

    @Override
    protected void performDestroy() {
    }

    @Override
    protected String create() {
        return JsfUtil.MANTEM;
    }

    @Override
    protected String update() {
        return JsfUtil.MANTEM;
    }

    @Override
    public EntityPagination getPagination() {
        return null;
    }

    public String initPedido(Pedido pedido) {
        this.current = pedido;
        return JsfUtil.REL_PEDIDO;
    }

    public Pedido getCurrent() {
        return current;
    }

    public void setCurrent(Pedido current) {
        this.current = current;
    }

    public List<DocumentoEntradaAvaliacao> getItensAvaliacao() {
        if (current != null && current.getItens() != null && !current.getItens().isEmpty() && current.getItens().get(0).getDocumentoEntradaItem() != null && current.getItens().get(0).getDocumentoEntradaItem().getDocumentoEntrada() != null && current.getItens().get(0).getDocumentoEntradaItem().getDocumentoEntrada().getItensAvaliacao() != null) {
            return current.getItens().get(0).getDocumentoEntradaItem().getDocumentoEntrada().getItensAvaliacao();
        }
        return new ArrayList<>();
    }
}
