/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.geral;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.geral.Empresa;
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
public class EmpresaFacade extends AbstractEntityBeans<Empresa, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public EmpresaFacade() {
        super(Empresa.class, EmpresaFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
