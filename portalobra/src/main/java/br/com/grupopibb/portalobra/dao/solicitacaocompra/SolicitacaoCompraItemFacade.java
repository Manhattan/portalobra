/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.solicitacaocompra;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tone.lima
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class SolicitacaoCompraItemFacade extends AbstractEntityBeans<SolicitacaoCompraItem, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public SolicitacaoCompraItemFacade() {
        super(SolicitacaoCompraItem.class, SolicitacaoCompraItemFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*   public List<SolicitacaoCompraItem> findSolicItems(final String codigo, final String empresa) {
     Map<String, Object> params = getMapParams();
     paramsPaginacao(params, codigo, empresa);
     return listPesqParam("SolicitacaoCompraItem.findByCentro", params);
     }
     */
    public SolicitacaoCompraItem find(final SolicitacaoCompra solicNumero, final Integer solicItemNumero) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, solicNumero, solicItemNumero);
        return pesqParam("SolicitacaoCompraItem.find", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final SolicitacaoCompra solicNumero, final Integer solicItemNumero) {
        params.put("solicNumero", solicNumero);
        params.put("solicItemNumero", solicItemNumero);
    }

    /**
     * Remove do banco de dados todos os itens de uma solicitação de compra.
     *
     * @param solicitacao Solicitação de Compra.
     */
    public void removeItens(SolicitacaoCompra solicitacao) {
        Query q = em.createNativeQuery("sp_PO_DeleteSolicitacaoCompraItens ?");
        q.setParameter(1, solicitacao.getNumero());
        q.executeUpdate();
    }

    /**
     * Pega itens de uma solicitação e os persiste no banco.
     *
     * @param itens
     */
    public void createItens(List<SolicitacaoCompraItem> itens) {
        if (itens != null && !itens.isEmpty()) {
            for (SolicitacaoCompraItem item : itens) {
                try {
                    create(item);
                } catch (EntityException ex) {
                    Logger.getLogger(SolicitacaoCompraItemFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //                solicitacaoCompraItemFacade.createItens(solicitacaoCompra.getItens());
}
