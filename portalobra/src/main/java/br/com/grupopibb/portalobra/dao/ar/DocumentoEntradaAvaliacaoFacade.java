/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.ar;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaAvaliacao;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author administrator
 */
public class DocumentoEntradaAvaliacaoFacade extends AbstractEntityBeans<DocumentoEntradaAvaliacao, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoEntradaAvaliacaoFacade() {
        super(DocumentoEntradaAvaliacao.class, DocumentoEntradaAvaliacaoFacade.class);
    }
}
