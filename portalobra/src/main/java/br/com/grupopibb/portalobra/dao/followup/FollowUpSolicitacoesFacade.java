/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.followup;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.followup.FollowUpSolicitacoes;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.insumo.InsumoSub;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.model.solicitacaocompra.Solicitante;
import br.com.grupopibb.portalobra.model.tipos.EnumSituacaoSolicitacao;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class FollowUpSolicitacoesFacade extends AbstractEntityBeans<FollowUpSolicitacoes, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FollowUpSolicitacoesFacade() {
        super(FollowUpSolicitacoes.class, FollowUpSolicitacoesFacade.class);
    }

    @Override
    public FollowUpSolicitacoes pesqParam(String namedQuery) {
        return super.pesqParam(namedQuery); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Pesquisa a quantidade em estoque de determinado Insumo em um Centro de
     * Custo.
     *
     * @param centro
     * @param insumo
     * @return Double estoqueAtual
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findEstoqueByInsumo(final CentroCusto centro, final InsumoSub insumoSub) {
        Map<String, Object> params = getMapParams();
        paramsInsumo(params, centro, insumoSub);
        Query q = getEntityManager().createNamedQuery("FollowUpSolicitacoes.findEstoqueByInsumo");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        try {
            return NumberUtils.isNull((Double) q.getSingleResult(), 0.0);
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findEstoqueUsinaByInsumoSub(final CentroCusto centro, final InsumoSub insumoSub) {
        Map<String, Object> params = getMapParams();
        paramsInsumo(params, centro, insumoSub);
        Query q = getEntityManager().createNamedQuery("FollowUpSolicitacoes.findEstoqueUsinaByInsumo");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        try {
            return NumberUtils.isNull((Double) q.getSingleResult(), 0.0);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Pesquisa a quantidade orçada a realizar de um determinado Insumo em um
     * Centro de Custo.
     *
     * @param centro Centro de custo atual.
     * @param insumo Insumo que contém saldo no centro.
     * @return Quantidade orçada a realizar.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findQuantidadeOrcamentoByInsumo(final CentroCusto centro, final Long insumoCod) {
        Map<String, Object> params = getMapParams();
        paramsInsumoOrc(params, centro, insumoCod);
        Query q = getEntityManager().createNamedQuery("FollowUpSolicitacoes.findQuantidadeOrcamento");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        try {
            return NumberUtils.isNull((Double) q.getSingleResult(), 0.0);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Procura pelos filtros centro, empresa, dataInicial, dataFinal
     *
     * @return List<FollowUpSolicitacoes>
     */
    public List<FollowUpSolicitacoes> findParam(final CentroCusto centro, final Date dataInicial, final Date dataFinal, final List<EnumSituacaoSolicitacao> situacaoFiltro, final String filtroInsumoCod, final String filtroInsumoSubCod, final String filtroInsumoEspecificacao, final String solicId, final boolean solicMaiorEstoque) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, solicId, solicMaiorEstoque);
        return listPesqParam("FollowUpSolicitacoes.selectRange", params);
    }

    /**
     * Seleciona o primeiro item de followup da solicitação de compra passada
     * como parâmetro.
     *
     * @return um objeto do tipo FollowUpSolicitacoes
     */
    public List<FollowUpSolicitacoes> findBySolicitacao(final SolicitacaoCompra solicitacaoCompra) {
        Map<String, Object> params = getMapParams();
        paramsSolicitacao(params, solicitacaoCompra);
        return listPesqParam("FollowUpSolicitacoes.findBySolicitacao", params);
    }

    /**
     * Seleciona o primeiro item de followup da solicitação de compra passada
     * como parâmetro.
     *
     * @return um objeto do tipo FollowUpSolicitacoes
     */
    public FollowUpSolicitacoes findBySolicItem(final SolicitacaoCompraItem solicitacaoCompraItem) {
        Map<String, Object> params = getMapParams();
        paramsSolicItem(params, solicitacaoCompraItem);
        return pesqParam("FollowUpSolicitacoes.findBySolicItem", params);

    }

    public boolean isUsingFilters(final Solicitante solicitante, final String codigoCredor, final String razaoSocialCredor, final String nomeFantasiaCredor, final String cpfCnpjCredor) {
        if (solicitante != null || !StringUtils.isBlank(codigoCredor) || !StringUtils.isBlank(razaoSocialCredor) || !StringUtils.isBlank(nomeFantasiaCredor) || !StringUtils.isBlank(cpfCnpjCredor)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Procura pelos filtros centro, empresa, dataInicial, dataFinal
     *
     * @return List<FollowUpSolicitacoes>
     */
    public List<FollowUpSolicitacoes> findRangeParam(final CentroCusto centro, final Date dataInicial, final Date dataFinal,
            final List<EnumSituacaoSolicitacao> situacaoFiltro, final boolean mesclarEstoque, final boolean mesclarEstoqueUsina,
            final String filtroInsumoCod, final String filtroInsumoSubCod, final String filtroInsumoEspecificacao, final String solicId, final Solicitante solicitante,
            final String codigoCredor, final String razaoSocialCredor, final String nomeFantasiaCredor, final String cpfCnpjCredor,
            final Integer numeroPedido, final Integer numeroAR, final int[] range, boolean marcado,
            final boolean solicMaiorEstoque) {

        Map<String, Object> params = getMapParams();
        String namedQuery = "";
        boolean isUsingFilters = isUsingFilters(solicitante, codigoCredor, razaoSocialCredor, nomeFantasiaCredor, cpfCnpjCredor);

        if (!isUsingFilters && (mesclarEstoque == true || mesclarEstoqueUsina == true)) {
            if (mesclarEstoque == true) {
                if (mesclarEstoqueUsina == true) {
                    namedQuery = "FollowUpSolicitacoes.selectRangeEstoqueUsina";  //Estoque da obra + estoque da usina
                } else {
                    namedQuery = "FollowUpSolicitacoes.selectRangeEstoque"; //Estoque da obra
                }
            } else if (mesclarEstoqueUsina == true) {
                namedQuery = "FollowUpSolicitacoes.selectRangeUsina"; //Estoque da usina
            }
            paramsPaginacao(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, solicId, solicMaiorEstoque);
        } else {
            namedQuery = "FollowUpSolicitacoes.selectRange"; //Apenas solicitações de compra
            paramsPaginacaoSolic(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, solicId, 
                    solicitante, codigoCredor, razaoSocialCredor, nomeFantasiaCredor, cpfCnpjCredor, numeroPedido, solicMaiorEstoque);
        }
        List<FollowUpSolicitacoes> lista = listPesqParamRange(namedQuery, params, range[1] - range[0], range[0]);
        if (marcado == true) {
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setMarcado(marcado);
            }
        }
        return lista;
    }

    /**
     * Conta a quantidade de registros pelos filtros centro, empresa,
     * dataInicial, dataFinal
     *
     * @return Long
     */
    public Long countParam(final CentroCusto centro, final Date dataInicial, final Date dataFinal,
            final List<EnumSituacaoSolicitacao> situacaoFiltro, final Boolean mesclarEstoque,
            final Boolean mesclarEstoqueUsina, final String filtroInsumoCod,  final String filtroInsumoSubCod, final String filtroInsumoEspecificacao,
            final String solicId, final Solicitante solicitante, final String codigoCredor, final String razaoSocialCredor,
            final String nomeFantasiaCredor, final String cpfCnpjCredor, final Integer numeroPedido, final Integer numeroAR,
            final boolean solicMaiorEstoque) {

        Map<String, Object> params = getMapParams();
        String namedQuery = "";
        boolean isUsingFilters = isUsingFilters(solicitante, codigoCredor, razaoSocialCredor, nomeFantasiaCredor, cpfCnpjCredor);
        if (!isUsingFilters && (mesclarEstoque == true || mesclarEstoqueUsina == true)) {
            if (mesclarEstoque == true) {
                if (mesclarEstoqueUsina == true) {
                    namedQuery = "FollowUpSolicitacoes.countRangeEstoqueUsina";  //Estoque da obra + estoque da usina
                } else {
                    namedQuery = "FollowUpSolicitacoes.countRangeEstoque"; //Estoque da obra
                }
            } else if (mesclarEstoqueUsina == true) {
                namedQuery = "FollowUpSolicitacoes.countRangeUsina"; //Estoque da usina
            }
            paramsPaginacao(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, solicId, solicMaiorEstoque);
        } else {
            namedQuery = "FollowUpSolicitacoes.countRange"; //Apenas solicitações de compra
            paramsPaginacaoSolic(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, 
                    solicId, solicitante, codigoCredor, razaoSocialCredor, nomeFantasiaCredor, cpfCnpjCredor, numeroPedido, solicMaiorEstoque);
        }
        paramsPaginacao(params, centro, dataInicial, dataFinal, situacaoFiltro, filtroInsumoCod, filtroInsumoSubCod, filtroInsumoEspecificacao, solicId, solicMaiorEstoque);
        return pesqCount(namedQuery, params);
    }

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final Date dataInicial, final Date dataFinal, 
            final List<EnumSituacaoSolicitacao> situacaoFiltro, final String filtroInsumoCod, final String filtroInsumoSubCod, final String filtroInsumoEspecificacao, 
            final String solicId, final boolean solicMaiorEstoque) {
        params.put("centro", centro);
        //params.put("centro2", centro == null ? "todos" : "filtro");
        params.put("dataInicial", DateUtils.addDays(dataInicial, -1));
        params.put("dataFinal", DateUtils.addDays(dataFinal, 1));
        params.put("situacaoFiltro", situacaoFiltro);
        params.put("insumoCod", StringBeanUtils.acertaNomeParaLike(filtroInsumoCod, StringBeanUtils.LIKE_END));
        params.put("insumoSubCod", StringBeanUtils.acertaNomeParaLike(NumberUtils.removeZeroEsquerda(filtroInsumoSubCod), StringBeanUtils.LIKE_END));
        params.put("insumoEspecificacao", StringBeanUtils.acertaNomeParaLike(filtroInsumoEspecificacao, StringBeanUtils.LIKE_MIDDLE));
        params.put("solicId", StringBeanUtils.acertaNomeParaLike(solicId, StringBeanUtils.LIKE_END));
        params.put("solicMaiorEstoque", !solicMaiorEstoque ? "todos" : "filtro");
        
        params.put("insumoCod2", StringUtils.isBlank(filtroInsumoCod) ? "todos" : "filtro");
        params.put("insumoSubCod2", StringUtils.isBlank(filtroInsumoSubCod) ? "todos" : "filtro");
        params.put("insumoEspecificacao2", StringUtils.isBlank(filtroInsumoEspecificacao) ? "todos" : "filtro");
        params.put("solicId2", StringUtils.isBlank(solicId) ? "todos" : "filtro");
    }

    private void paramsPaginacaoSolic(Map<String, Object> params, final CentroCusto centro, final Date dataInicial, final Date dataFinal, 
            final List<EnumSituacaoSolicitacao> situacaoFiltro, final String filtroInsumoCod, final String filtroInsumoSubCod, final String filtroInsumoEspecificacao, 
            final String solicId, final Solicitante solicitante, final String codigoCredor, final String razaoSocialCredor, 
            final String nomeFantasiaCredor, final String cpfCnpjCredor, final Integer numeroPedido, final boolean solicMaiorEstoque) {
        params.put("centro", centro);
        //params.put("centro2", centro == null ? "todos" : "filtro");
        params.put("dataInicial", DateUtils.addDays(dataInicial, -1));
        params.put("dataFinal", DateUtils.addDays(dataFinal, 1));
        params.put("situacaoFiltro", situacaoFiltro);
        params.put("insumoEspecificacao2", StringUtils.isBlank(filtroInsumoEspecificacao) ? "todos" : "filtro");
        params.put("insumoCod", StringBeanUtils.acertaNomeParaLike(NumberUtils.removeZeroEsquerda(filtroInsumoCod), StringBeanUtils.LIKE_END));
        params.put("insumoCod2", StringUtils.isBlank(filtroInsumoCod) ? "todos" : "filtro");
        params.put("insumoSubCod", StringBeanUtils.acertaNomeParaLike(NumberUtils.removeZeroEsquerda(filtroInsumoSubCod, "0"), StringBeanUtils.LIKE_END));
        params.put("insumoSubCod2", StringUtils.isBlank(filtroInsumoSubCod) ? "todos" : "filtro");
        params.put("insumoEspecificacao", StringBeanUtils.acertaNomeParaLike(filtroInsumoEspecificacao, StringBeanUtils.LIKE_MIDDLE));
        params.put("solicitante", solicitante);
        params.put("solicitante2", solicitante == null ? "todos" : "filtro");

        params.put("codigoCredor", codigoCredor);
        params.put("razaoSocialCredor", StringBeanUtils.acertaNomeParaLike(razaoSocialCredor, StringBeanUtils.LIKE_MIDDLE));
        params.put("nomeFantasiaCredor", StringBeanUtils.acertaNomeParaLike(nomeFantasiaCredor, StringBeanUtils.LIKE_MIDDLE));
        params.put("cpfCnpjCredor", StringBeanUtils.acertaNomeParaLike(cpfCnpjCredor, StringBeanUtils.LIKE_MIDDLE));

        params.put("numeroPedido", numeroPedido);
        params.put("numeroPedido2", numeroPedido == null ? "todos" : "filtro");

        params.put("codigoCredor2", StringUtils.isBlank(codigoCredor) ? "todos" : "filtro");
        params.put("razaoSocialCredor2", StringUtils.isBlank(razaoSocialCredor) ? "todos" : "filtro");
        params.put("nomeFantasiaCredor2", StringUtils.isBlank(nomeFantasiaCredor) ? "todos" : "filtro");
        params.put("cpfCnpjCredor2", StringUtils.isBlank(cpfCnpjCredor) ? "todos" : "filtro");

        params.put("solicId2", StringUtils.isBlank(solicId) ? "todos" : "filtro");
        params.put("solicId", StringBeanUtils.acertaNomeParaLike(solicId, StringBeanUtils.LIKE_END));
        
        params.put("solicMaiorEstoque", !solicMaiorEstoque ? "todos" : "filtro");
    }

    private void paramsInsumo(Map<String, Object> params, final CentroCusto centro, final InsumoSub insumoSub) {
        params.put("centro", centro);
        params.put("insumoSub", insumoSub);
    }
    
    private void paramsInsumoOrc(Map<String, Object> params, final CentroCusto centro, final Long insumoCod) {
        params.put("centro", centro);
        params.put("insumoCod", insumoCod);
    }

    private void paramsSolicitacao(Map<String, Object> params, final SolicitacaoCompra solicitacaoCompra) {
        params.put("solicitacaoCompra", solicitacaoCompra);
    }

    private void paramsSolicItem(Map<String, Object> params, final SolicitacaoCompraItem solicitacaoCompraItem) {
        params.put("solicitacaoCompraItem", solicitacaoCompraItem);
    }

    public void clearCache() {
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
    }
}
