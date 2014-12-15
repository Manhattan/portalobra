/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso.dao;

import br.com.grupopibb.portalobra.acesso.Monitor;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MonitorFacade extends AbstractEntityBeans<Monitor, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public MonitorFacade() {
        super(Monitor.class, MonitorFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void atualizaMonitor() {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_Atualiza_Monitor");
        q.executeUpdate();
    }
    
    public List<Monitor> findActiveUsers(){
        return listPesq("Monitor.findActiveUsers");
    }
    public Long countActiveUsers(){
        return pesqCount("Monitor.countActiveUsers", null);
    }
}
