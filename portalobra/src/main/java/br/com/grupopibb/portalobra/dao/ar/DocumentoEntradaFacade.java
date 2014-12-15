/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.ar;

import br.com.grupopibb.portalobra.business.pedido.PedidoItemBusiness;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.dao.geral.SequenciaisFacade;
import br.com.grupopibb.portalobra.dao.pedido.PedidoItemFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntrada;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaTipo;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.insumo.CaracterizacaoInsumos;
import br.com.grupopibb.portalobra.model.insumo.ClasseInsumos;
import br.com.grupopibb.portalobra.model.insumo.GrupoInsumos;
import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class DocumentoEntradaFacade extends AbstractEntityBeans<DocumentoEntrada, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    private PedidoItemFacade pedidoItemFacade;
    @EJB
    SequenciaisFacade sequenciaisFacade;

    public DocumentoEntradaFacade() {
        super(DocumentoEntrada.class, DocumentoEntradaFacade.class);
    }

    public Long createDocumentoEntrada(DocumentoEntrada entity) {
        /*   if (entity.getNumero() == null){
         entity.setNumero(0L);
         }
         Long sequencial = sequenciaisFacade.getSequencialDocumentoEntrada().getNumero().longValue();
         while (entity.getNumero().longValue() <= sequencial) {
         entity.setNumero(sequencial.longValue() + 1L);
         sequencial = sequenciaisFacade.getSequencialDocumentoEntrada().getNumero().longValue();
         }
         */
        try {
            super.create(entity);
        } catch (EntityException ex) {
            Logger.getLogger(DocumentoEntradaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity.getNumero().longValue();
    }

    public List<DocumentoEntrada> findRangeParam(final Date dataInicial, final Date dataFinal, final Integer numeroAr, final DocumentoEntradaTipo docTipo, final String docNumero, final Credor credor, final ClasseInsumos classeInsumo, final GrupoInsumos grupoInsumo, final CaracterizacaoInsumos caracterizacaoInsumo, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramPaginacao(params, dataInicial, dataFinal, numeroAr, docTipo, docNumero, credor);
        return listPesqParamRange("DocumentoEntrada.findParam", params, range[1] - range[0], range[0]);
    }

    public Long countParam(final Date dataInicial, final Date dataFinal, final Integer numeroAr, final DocumentoEntradaTipo docTipo, final String docNumero, final Credor credor, final ClasseInsumos classeInsumo, final GrupoInsumos grupoInsumo, final CaracterizacaoInsumos caracterizacaoInsumo) {
        Map<String, Object> params = getMapParams();
        //    List<PedidoItem> pedidoItens = pedidoItemFacade.findByInsumo(classeInsumo, grupoInsumo, caracterizacaoInsumo);
        //    PedidoItemBusiness piBusiness = new PedidoItemBusiness();
        //    List<Long> numeroPedidos = piBusiness.getNumeroPedidos(pedidoItens);
        paramPaginacao(params, dataInicial, dataFinal, numeroAr, docTipo, docNumero, credor);
        //    pedidoItens.clear();
        //    pedidoItens = null;
        return pesqCount("DocumentoEntrada.countParam", params);
    }
    
    public boolean numeroDocumentoExists(Integer numeroDocumento){
        Map<String, Object> params = getMapParams();
        paramNumeroDocumento(params, numeroDocumento);
        Long qtd = pesqCount("DocumentoEntrada.countNumeroDocumento", params);
        if (qtd == null || qtd == 0){
            return false;
        }else if (qtd > 0){
            return true;
        } else {
            return false;
        }
    }
    
    private void paramNumeroDocumento(Map<String, Object> params, Integer numeroDocumento){
        params.put("numeroDocumento", numeroDocumento);
    }

    private void paramPaginacao(Map<String, Object> params, final Date dataInicial, final Date dataFinal, final Integer numeroAr,
            final DocumentoEntradaTipo docTipo, final String docNumero, final Credor credor) {
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("numeroAr", numeroAr);
        params.put("docTipo", docTipo);
        params.put("docNumero", docNumero);
        params.put("credor", credor);
        //    params.put("numeroPedidos", numeroPedidos);

        params.put("dataInicial2", dataInicial == null ? "todos" : "filtro");
        params.put("dataFinal2", dataFinal == null ? "todos" : "filtro");
        params.put("numeroAr2", numeroAr == null ? "todos" : "filtro");
        params.put("docTipo2", docTipo == null ? "todos" : "filtro");
        params.put("docNumero2", docNumero == null ? "todos" : "filtro");
        params.put("credor2", credor == null ? "todos" : "filtro");
        //   params.put("numeroPedidos2", numeroPedidos == null ? "todos" : "filtro");
    }
}
