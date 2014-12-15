/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.pagamento;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.pagamento.EspecieTituloAPagar;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.io.Serializable;
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
public class EspecieTituloAPagarFacade extends AbstractEntityBeans<EspecieTituloAPagar, String>{

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EspecieTituloAPagarFacade() {
        super(EspecieTituloAPagar.class, EspecieTituloAPagarFacade.class);
    }
    
}
