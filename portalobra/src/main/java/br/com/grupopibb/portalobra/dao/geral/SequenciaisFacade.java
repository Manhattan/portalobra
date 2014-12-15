/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.geral;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.Sequenciais;
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
public class SequenciaisFacade extends AbstractEntityBeans<Sequenciais, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public SequenciaisFacade() {
        super(Sequenciais.class, SequenciaisFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void update(Sequenciais seq) {
        try {
            super.update(seq);
        } catch (EntityException ex) {
            Logger.getLogger(SequenciaisFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Sequenciais findByName(final String nomeTabela) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, nomeTabela);
        Sequenciais seq = pesqParam("Sequenciais.findByName", params);
        if (seq == null || seq.getNumero() == null) {
            return new Sequenciais(nomeTabela, 0L);
        }
        return seq;
    }

    public Sequenciais getSequencialMaterialEntrada() {
        return findByName("MATERIAISENTRADA");
    }

    public Sequenciais getSequencialSolicitacaoCompra() {
        return findByName("SOLICITACAODECOMPRA");
    }

    public Sequenciais getSequencialMaterialSaida() {
        return findByName("MATERIAISSAIDA");
    }

    public Sequenciais getSequencialAvisoRecebimento() {
        return findByName("AR");
    }

    public Sequenciais getSequencialDocumentoEntrada() {
        return findByName("DOCUMENTODEENTRADA");
    }

    public Sequenciais getSequencialTituloAPagar() {
        return findByName("TITULOSAPAGAR");
    }

    /**
     * Sequencial do número de depósito da saída de material.
     *
     * @return Sequencial do depositoNumero em MaterialSaida.
     */
    public Sequenciais getSequencialSaidaDeposito() {
        return findByName("MATERIAISSAIDADEPOSITO");
    }

    private void paramsPaginacao(Map<String, Object> params, final String nomeTabela) {
        params.put("nomeTabela", nomeTabela);
    }
}
