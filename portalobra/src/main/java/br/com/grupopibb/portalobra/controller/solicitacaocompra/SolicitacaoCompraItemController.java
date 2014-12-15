/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.solicitacaocompra;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.solicitacaocompra.SolicitacaoCompraItemFacade;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import java.io.Serializable;
import java.util.Date;
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
public class SolicitacaoCompraItemController  extends EntityController<SolicitacaoCompraItem> implements Serializable {

    @EJB
    private SolicitacaoCompraItemFacade solicItemFacade;
    private SolicitacaoCompraItem current;
    private Date dataInicial;
    private Date dataFinal;
    
    @Override
    protected void setEntity(SolicitacaoCompraItem t) {
        this.current = t;
    }
    
    private SolicitacaoCompraItemFacade getFacade(){
        return solicItemFacade;
    }

    @Override
    protected SolicitacaoCompraItem getNewEntity() {
        SolicitacaoCompraItem solicItem = new SolicitacaoCompraItem();
        return solicItem;
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
        return null;
    }

    public SolicitacaoCompraItem getCurrent() {
        return current;
    }

    public void setCurrent(SolicitacaoCompraItem current) {
        this.current = current;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    
    
}
