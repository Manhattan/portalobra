/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.insumo;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.insumo.ClasseInsumos;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tone.lima
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class ClasseInsumosFacade extends AbstractEntityBeans<ClasseInsumos, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public ClasseInsumosFacade() {
        super(ClasseInsumos.class, ClasseInsumosFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ClasseInsumos findFirst(){
        return findAll().get(0);
    }
    
}
