/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.solicitacaocompra;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.dao.geral.SequenciaisFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.Map;
import javax.ejb.EJB;
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
 * @author tone.lima
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class SolicitacaoCompraFacade extends AbstractEntityBeans<SolicitacaoCompra, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private SequenciaisFacade sequenciaisFacade;

    public SolicitacaoCompraFacade() {
        super(SolicitacaoCompra.class, SolicitacaoCompraFacade.class);
    }

    /**
     * Procura o último ID da Solicitação de Compra
     *
     * @return Long numero
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Long findMaxId() {
        Query q = getEntityManager().createNamedQuery("SolicitacaoCompra.selectMaxId");
        try {
            Object result = q.getSingleResult();
            if (result != null && result instanceof Long){
                return (Long) result;
            } else {
                return 0L;
            }
        } catch (NoResultException e) {
            return 0L;
        }
    }

    /**
     * Procura o último centroNumero de cada Centro de Custo nas Solicitações de
     * Compra
     *
     * @return Integer centroNumero
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Integer findMaxCentroNumero(final CentroCusto centro) {
        final Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro);
        Query q = getEntityManager().createNamedQuery("SolicitacaoCompra.selectMaxCentroNumero");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        try {
            Object result = q.getSingleResult();
            if (result != null && result instanceof Integer){
                return (Integer) result;
            } else {
                return 0;
            }
        } catch (NoResultException e) {
            return 0;
        }
    }

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro) {
        params.put("centro", centro);
    }

    public Integer createSolicitacaoCompra(final SolicitacaoCompra entity) throws EntityException, SQLServerException {
        Integer sequencial = sequenciaisFacade.getSequencialSolicitacaoCompra().getNumero().intValue();
        if (sequencial == 0L){
            sequencial = findMaxId().intValue();
        }
        while (entity.getNumero().intValue() <= sequencial) {
            entity.setNumero(sequencial.longValue() + 1L);
            sequencial = NumberUtils.isNull(sequenciaisFacade.getSequencialSolicitacaoCompra().getNumero(), 0L).intValue();
        }
        super.create(entity);
        return entity.getNumero().intValue();
    }

    @Override
    public void remove(SolicitacaoCompra solicitacaoCompra) throws EntityException{
        super.remove(solicitacaoCompra);
    }
}
