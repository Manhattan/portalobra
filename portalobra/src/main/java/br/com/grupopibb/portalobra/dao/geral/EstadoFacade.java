/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.geral;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.geral.Estado;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class EstadoFacade extends AbstractEntityBeans<Estado, String>{
    
     @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class, EstadoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
