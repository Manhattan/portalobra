/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.coleta;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.coleta.ColetaPreco;
import br.com.grupopibb.portalobra.model.coleta.ColetaPrecoItem;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Map;
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
//@Interceptors({LogTime.class})
public class ColetaPrecoItemFacade  extends AbstractEntityBeans<ColetaPrecoItem, Long> {
    
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public ColetaPrecoItemFacade() {
        super(ColetaPrecoItem.class, ColetaPrecoItemFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

        public ColetaPrecoItem find(final ColetaPreco coletaNumero, final Integer coletaItemNumero) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, coletaNumero, coletaItemNumero);
        return pesqParam("ColetaPrecoItem.find", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final ColetaPreco coletaNumero, final Integer coletaItemNumero) {
        params.put("coletaNumero", coletaNumero);
        params.put("coletaItemNumero", coletaItemNumero);
    }

    
}
