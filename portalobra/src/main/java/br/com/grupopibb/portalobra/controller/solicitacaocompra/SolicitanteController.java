/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.solicitacaocompra;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.solicitacaocompra.SolicitanteFacade;
import br.com.grupopibb.portalobra.model.solicitacaocompra.Solicitante;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class SolicitanteController extends EntityController<Solicitante> implements Serializable {

    /**
     * Registra os eventos para debug em desenvolvimento.
     */
    //private Logger logger = Logger.getLogger(SolicitanteController.class);
    @EJB
    private SolicitanteFacade solicitanteFacade;
    private Solicitante current;
    private String nomeFiltro;

    public String getNomeFiltro() {
        return nomeFiltro;
    }

    public void setNomeFiltro(String nomeFiltro) {
        this.nomeFiltro = nomeFiltro;
    }

    public SolicitanteController() {
    }

    @PostConstruct
    public void init() {
        getNewEntity();
    }

    public SolicitanteFacade getFacade() {
        return solicitanteFacade;
    }

    public Solicitante getCurrent() {
        return current;
    }

    @Override
    protected void setEntity(final Solicitante t) {
        this.current = t;
    }

    @Override
    protected Solicitante getNewEntity() {
        Solicitante sol = new Solicitante();
        return sol;
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
        if (pagination == null) {
            pagination = new EntityPagination() {
                @Override
                public int getItemsCount() {
                    return getFacade().countSolicitantes(nomeFiltro).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findSolicitantes(nomeFiltro,
                            new int[]{getPageFirstItem(), getPageFirstItem()
                        + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    @Override
    public String clean() {
        super.clean();
        recreateTable();
        return JsfUtil.MANTEM;
    }
}
