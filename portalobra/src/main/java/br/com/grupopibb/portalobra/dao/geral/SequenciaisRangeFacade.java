/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.geral;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.SequenciaisRange;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SequenciaisRangeFacade extends AbstractEntityBeans<SequenciaisRange, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public SequenciaisRangeFacade() {
        super(SequenciaisRange.class, SequenciaisRangeFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void update(SequenciaisRange seq) {
        try {
            super.update(seq);
        } catch (EntityException ex) {
            Logger.getLogger(SequenciaisFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SequenciaisRange findByName(final String nomeTabela, final String range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, nomeTabela, range);
        return pesqParam("SequenciaisRange.findByName", params);
    }

    public SequenciaisRange getSequencialSolicitacaoCompra(final CentroCusto centro) {
        String range = centro.getIdCompleto();
        SequenciaisRange seq = findByName("SOLICITACAODECOMPRA", range);

        if (seq != null) {
            return seq;
        } else {
            return createSequencialSolicitacaoCompra(range);
        }
    }

    private SequenciaisRange createSequencialSolicitacaoCompra(final String range) {
        try {
            SequenciaisRange seq = new SequenciaisRange();
            seq.setNomeTabela("SOLICITACAODECOMPRA");
            seq.setNumero(0L);
            seq.setRange(range);
            create(seq);
            return seq;
        } catch (EntityException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void paramsPaginacao(Map<String, Object> params, final String nomeTabela, final String range) {
        params.put("nomeTabela", nomeTabela);
        params.put("range", range);
    }
}
