/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.pedido;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.insumo.CaracterizacaoInsumos;
import br.com.grupopibb.portalobra.model.insumo.ClasseInsumos;
import br.com.grupopibb.portalobra.model.insumo.GrupoInsumos;
import br.com.grupopibb.portalobra.model.pedido.Pedido;
import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tone.lima
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class PedidoItemFacade extends AbstractEntityBeans<PedidoItem, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoItemFacade() {
        super(PedidoItem.class, PedidoItemFacade.class);
    }
    public List<PedidoItem> listPesqParamRange(final CentroCusto centro, final Credor credor, final Date dataInicial, final Date dataFinal,
            final String especificacao, final Integer numeroPedido, final String caracterizacao, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, credor, dataInicial, dataFinal, especificacao, numeroPedido, caracterizacao);
        List<PedidoItem> lista = super.listPesqParamRange("PedidoItem.selectRange", params, range[1] - range[0], range[0]);
        return lista;
    }

    public Long pesqCount(final CentroCusto centro, final Credor credor, final Date dataInicial, final Date dataFinal,
            final String especificacao, final Integer numeroPedido, final String caracterizacao) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, credor, dataInicial, dataFinal, especificacao, numeroPedido, caracterizacao);
        Long quantidade = super.pesqCount("PedidoItem.countRange", params);
        return quantidade;
    }
    
    public List<PedidoItem> findByInsumo(final ClasseInsumos classeInsumo, final GrupoInsumos grupoInsumo, final CaracterizacaoInsumos caracterizacaoInsumo){
        Map<String, Object> params = getMapParams();
        paramsFiltro(params, classeInsumo, grupoInsumo, caracterizacaoInsumo);
        return listPesqParam("PedidoItem.findByInsumo", params);
    }
    
    /**
     * Pesquisa um único item através de seu ID (pedido, itemPedido).
     * @param pedido
     * @param itemNumero
     * @return Item de um pedido.
     */
    public PedidoItem findById(final Pedido pedido, final Integer itemNumero){
        Map<String, Object> params = getMapParams();
        paramsFiltro2(params, pedido, itemNumero);
        return pesqParam("PedidoItem.findOne", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final Credor credor, final Date dataInicial, final Date dataFinal,
            final String especificacao, final Integer numeroPedido, final String caracterizacao) {
        params.put("centro", centro);
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("especificacao", StringBeanUtils.acertaNomeParaLike(especificacao, StringBeanUtils.LIKE_MIDDLE));
        params.put("numeroPedido", numeroPedido);
        params.put("caracterizacao", StringBeanUtils.acertaNomeParaLike(caracterizacao, StringBeanUtils.LIKE_MIDDLE));
        params.put("credor", credor);

        params.put("dataInicial2", dataInicial == null ? "todos" : "filtro");
        params.put("dataFinal2", dataFinal == null ? "todos" : "filtro");
        params.put("especificacao2", especificacao == null ? "todos" : "filtro");
        params.put("numeroPedido2", numeroPedido == null ? "todos" : "filtro");
        params.put("caracterizacao2", caracterizacao == null ? "todos" : "filtro");
        params.put("credor2", credor == null ? "todos" : "filtro");
    }
    
    private void paramsFiltro(Map<String, Object> params,final ClasseInsumos classeInsumo, final GrupoInsumos grupoInsumo, final CaracterizacaoInsumos caracterizacaoInsumo){
        params.put("classeInsumo2", classeInsumo == null ? "todos" : "filtro");
        params.put("grupoInsumo2", grupoInsumo == null ? "todos" : "filtro");
        params.put("caracterizacaoInsumo2", caracterizacaoInsumo == null ? "todos" : "filtro");
        
        params.put("classeInsumo", classeInsumo == null ? null: classeInsumo.getCodigo());
        params.put("grupoInsumo", grupoInsumo == null ? null: grupoInsumo.getCodigo());
        params.put("caracterizacaoInsumo", caracterizacaoInsumo == null ? null: caracterizacaoInsumo.getCodigo());
    }
    
    private void paramsFiltro2(Map<String, Object> params, final Pedido pedido, final Integer itemNumero){
        params.put("pedido", pedido);
        params.put("itemNumero", itemNumero);
    }
}