/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.geral;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.geral.CentroCustoFacade;
import br.com.grupopibb.portalobra.dao.solicitacaocompra.SolicitacaoCompraItemFacade;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class CentroCustoController extends EntityController<CentroCusto> implements Serializable {

    @EJB
    private CentroCustoFacade centroCustoFacade;
    private CentroCusto current;

    @Override
    protected void setEntity(CentroCusto t) {
        this.current = t;
    }

    @Override
    protected CentroCusto getNewEntity() {
        CentroCusto centro = new CentroCusto();
        return centro;
    }

    @Override
    protected void performDestroy() {
    }

    @Override
    protected String create() {
        return "";
    }

    @Override
    protected String update() {
        return "";
    }

    @Override
    public EntityPagination getPagination() {
        return pagination;
    }

    public CentroCustoFacade getFacade() {
        return centroCustoFacade;
    }

    public CentroCusto getCurrent() {
        return current;
    }
    /**
     * Pesquisa um Centro de Custo
     */
}
