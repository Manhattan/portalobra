/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.ar;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.ar.AvisoRecebimento;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class AvisoRecebimentoFacade extends AbstractEntityBeans<AvisoRecebimento, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvisoRecebimentoFacade() {
        super(AvisoRecebimento.class, AvisoRecebimentoFacade.class);
    }

    /**
     * Procura o último centroNumero de cada Centro de Custo nos Avisos de
     * Recebimento.
     *
     * @return Long centroNumero
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Integer findMaxCentroNumero(final CentroCusto centro) {
        final Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro);
        Query q = getEntityManager().createNamedQuery("AvisoRecebimento.selectMaxCentroNumero");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        try {
            return (Integer) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro) {
        params.put("centro", centro);
    }
}
