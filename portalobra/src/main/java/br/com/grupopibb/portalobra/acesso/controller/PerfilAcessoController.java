/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso.controller;

import br.com.grupopibb.portalobra.acesso.PerfilAcesso;
import br.com.grupopibb.portalobra.acesso.business.PerfilAcessoBusiness;
import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.acesso.PerfilAcessoFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import br.com.grupopibb.portalobra.utils.MessageUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class PerfilAcessoController extends EntityController<PerfilAcesso> {

    @EJB
    private PerfilAcessoFacade perfilAcessoFacade;
    @EJB
    private PerfilAcessoBusiness perfilAcessoBusiness;
    private PerfilAcesso current;
    //----------------------------

    @Override
    protected void setEntity(PerfilAcesso t) {
        this.current = t;
    }

    @Override
    protected PerfilAcesso getNewEntity() {
        return new PerfilAcesso();
    }

    @Override
    protected void performDestroy() {
        try {
            getFacade().remove(current);
            msgPerfilAcessoDeleted();
        } catch (EntityException | EJBException ex) {
            msgOperacaoNaoPermitida();
        }
        clean();
    }

    private PerfilAcessoFacade getFacade() {
        return perfilAcessoFacade;
    }
    
    @Override
    protected String create() {
        try {
            getFacade().create(current);
            msgPerfilAcessoCreated();
        } catch (EntityException | EJBException ex) {
            msgErroDesconhecido();
        }
        recreateTable();
        clean();
        return JsfUtil.MANTEM;
    }

    @Override
    protected String update() {
        try {
            getFacade().update(current);
            msgPerfilAcessoUpdated();
        } catch (EntityException | EJBException ex) {
            Logger.getLogger(PerfilAcessoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recreateTable();
        clean();
        return JsfUtil.MANTEM;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public PerfilAcesso getCurrent() {
        return current;
    }

    public void setCurrent(PerfilAcesso current) {
        this.current = current;
    }

    private void msgErroDesconhecido() {
        MessageUtils.messageFactoringFull("erroDesconhcido",
                null,
                FacesMessage.SEVERITY_ERROR,
                FacesContext.getCurrentInstance());
    }

    private void msgPerfilAcessoCreated() {
        MessageUtils.messageFactoringFull("PerfilAcessoCreated",
                new Object[]{current.getNome()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgPerfilAcessoUpdated() {
        MessageUtils.messageFactoringFull("PerfilAcessoUpdated",
                new Object[]{current.getNome()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgPerfilAcessoDeleted() {
        MessageUtils.messageFactoringFull("PerfilAcessoDeleted",
                new Object[]{current.getNome()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgOperacaoNaoPermitida() {
        MessageUtils.messageFactoringFull("operacaoNaoPermitida",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }
}
