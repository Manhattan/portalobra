/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.ar;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.model.ar.AvisoRecebimento;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author administrator
 */
public class AvisoRecebimentoController extends EntityController<AvisoRecebimento> implements Serializable {

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

    @Override
    protected void setEntity(AvisoRecebimento t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AvisoRecebimento getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
