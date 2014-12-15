/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.insumo;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.insumo.Unidade;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tone.lima
 */
public class UnidadeFacade extends AbstractEntityBeans<Unidade, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public UnidadeFacade() {
        super(Unidade.class, UnidadeFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}