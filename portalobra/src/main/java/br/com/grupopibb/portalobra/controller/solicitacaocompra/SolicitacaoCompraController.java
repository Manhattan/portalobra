/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.solicitacaocompra;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author tone.lima
 */
@ManagedBean
@ViewScoped
public class SolicitacaoCompraController extends EntityController<SolicitacaoCompra> implements Serializable {

    private SolicitacaoCompra current;
    
    public void initSolicitacao(SolicitacaoCompra solic){
        setEntity(solic);
    }

    @Override
    protected void setEntity(SolicitacaoCompra t) {
        this.current = t;
    }

    @Override
    protected SolicitacaoCompra getNewEntity() {
        return new SolicitacaoCompra();
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

    public SolicitacaoCompra getCurrent() {
        return current;
    }

    public void setCurrent(SolicitacaoCompra current) {
        this.current = current;
    }
}
