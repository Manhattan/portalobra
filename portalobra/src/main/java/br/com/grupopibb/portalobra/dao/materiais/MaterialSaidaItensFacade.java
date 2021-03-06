/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.materiais;

import br.com.grupopibb.portalobra.business.followup.FollowUpBusiness;
import br.com.grupopibb.portalobra.business.materiais.EstoqueBusiness;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.InsumoSub;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaida;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaidaItens;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class MaterialSaidaItensFacade extends AbstractEntityBeans<MaterialSaidaItens, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    /**/
    @EJB
    private FollowUpBusiness followUpBusiness;
    @EJB
    private EstoqueBusiness estoqueBusiness;

    public MaterialSaidaItensFacade() {
        super(MaterialSaidaItens.class, MaterialSaidaItensFacade.class);
    }

    /**
     * Procura pelos filtros empresaCod, filialCod, centroCod, insumoCod
     *
     * @return List<MaterialSaidaItens>
     */
    public List<MaterialSaidaItens> findParam(final String empresaCod, final String filialCod, final String centroCod, final InsumoSub insumoSub, final Date dataMesRef) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao1(params, empresaCod, filialCod, centroCod, insumoSub, dataMesRef);
        return listPesqParam("MaterialSaidaItens.find", params);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Long> findSaidaNumeroByInsumo(final Long insumoCod, final String insumoEspecificacao, final String empresaCod, final String filialCod, final String centroCod) {
        Map<String, Object> params = getMapParams();
        paramsSaidaNumero(params, insumoCod, insumoEspecificacao, empresaCod, filialCod, centroCod);
        Query q = getEntityManager().createNamedQuery("MaterialSaidaItens.findSaidaNumeroByInsumo");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        return q.getResultList();
    }

    public void createItens(List<MaterialSaidaItens> itens, CentroCusto centro, MaterialSaida materialSaida) {
        if (itens != null && !itens.isEmpty()) {
            for (MaterialSaidaItens item : itens) {
                try {
                    create(item);
                    estoqueBusiness.atualizaSaldoMaterial(centro, materialSaida.getDataSaida(), item.getInsumoSub());
                    //followUpBusiness.atualizaFollowUp(centro, item.getInsumoSub().getInsumo());
                } catch (EntityException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MaterialSaidaItensFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        itens = null;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findEstoqueSaidas(final Long insumoCod, final CentroCusto centro) {
        Map<String, Object> params = getMapParams();
        paramsEstoqueSaidas(params, insumoCod, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        Query q = getEntityManager().createNamedQuery("MaterialSaidaItens.findEstoqueSaidas");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        return (Double) q.getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findEstoqueSaidasSemDevolucoes(final InsumoSub insumoSub, final CentroCusto centro) {
        Map<String, Object> params = getMapParams();
        paramsEstoqueSaidasSemDevolucoes(params, insumoSub, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        Query q = getEntityManager().createNamedQuery("MaterialSaidaItens.findEstoqueSaidasSemDevolucoes");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        return (Double) q.getSingleResult();
    }

    /**
     * Pesquisa os materiais de saida transferidos.
     *
     * @param centroOrigem
     * @param centroDestino
     * @param numeroDocumento
     * @param dataMovimentacao
     * @return
     */
    public List<MaterialSaidaItens> findTransferencias(final CentroCusto centroOrigem, final CentroCusto centroDestino, final String numeroDocumento, final Date dataMovimentacao) {
        Map<String, Object> params = getMapParams();
        paramsTransferencias(params, centroOrigem, centroDestino, numeroDocumento, dataMovimentacao);
        List<MaterialSaidaItens> lista = listPesqParam("MaterialSaidaItens.findTransferencias", params);

        return lista;

    }

    public MaterialSaidaItens find(final Long numeroSaida, final Integer itemNumero) {
        Map<String, Object> params = getMapParams();
        paramsSaidaItem(params, numeroSaida, itemNumero);
        return pesqParam("MaterialSaidaItens.findByItem", params);
    }

    public List<MaterialSaidaItens> findRangeParam(final CentroCusto centro, final String numeroDoc, final Date dataInicial,
            final Date dataFinal, final String insumoCod, final String insumoSubCod, final String insumoEspecificacao, final String tipoMovimento, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, numeroDoc, dataInicial, dataFinal, insumoCod, insumoSubCod, insumoEspecificacao, tipoMovimento);
        return listPesqParamRange("MaterialSaidaItens.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long countParam(final CentroCusto centro, final String numeroDoc, final Date dataInicial, final Date dataFinal, final String insumoCod, final String insumoSubCod, final String insumoEspecificacao, final String tipoMovimento) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, numeroDoc, dataInicial, dataFinal, insumoCod, insumoSubCod, insumoEspecificacao, tipoMovimento);
        return pesqCount("MaterialSaidaItens.countRange", params);
    }

    private void paramsEstoqueSaidas(Map<String, Object> params, final Long insumoCod, final String empresaCod, final String filialCod, final String centroCod) {
        params.put("insumoCod", insumoCod);
        params.put("empresaCod", empresaCod);
        params.put("filialCod", filialCod);
        params.put("centroCod", centroCod);
        params.put("dataInicial", DateUtils.setDay(new Date(), 1));
        params.put("dataFinal", new Date());
    }

    private void paramsEstoqueSaidasSemDevolucoes(Map<String, Object> params, final InsumoSub insumoSub, final String empresaCod, final String filialCod, final String centroCod) {
        params.put("insumoSub", insumoSub);
        params.put("empresaCod", empresaCod);
        params.put("filialCod", filialCod);
        params.put("centroCod", centroCod);
    }
//params, centro, numeroDoc, dataInicial, dataFinal, insumoCod, insumoEspecificacao

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final String numeroDoc, final Date dataInicial, final Date dataFinal, final String insumoCod, final String insumoSubCod, final String insumoEspecificacao, final String tipoMovimento) {
        params.put("empresaCod", centro != null ? centro.getEmpresaCod() : "");
        params.put("filialCod", centro != null ? centro.getFilialCod() : "");
        params.put("centroCod", centro != null ? centro.getCodigo() : "");
        params.put("numeroDoc", numeroDoc);
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("insumoCod", StringBeanUtils.acertaNomeParaLike(insumoCod, StringBeanUtils.LIKE_END));
        params.put("insumoSubCod", StringBeanUtils.acertaNomeParaLike(NumberUtils.removeZeroEsquerda(insumoSubCod, "0"), StringBeanUtils.LIKE_END));
        params.put("insumoEspecificacao", StringBeanUtils.acertaNomeParaLike(insumoEspecificacao, StringBeanUtils.LIKE_END));
        params.put("tipoMovimento", tipoMovimento);

        params.put("numeroDoc2", StringUtils.isBlank(numeroDoc) ? "todos" : "filtro");
        params.put("insumoSubCod2", StringUtils.isBlank(insumoSubCod) ? "todos" : "filtro");
        params.put("insumoCod2", StringUtils.isBlank(insumoCod) ? "todos" : "filtro");
        params.put("insumoEspecificacao2", StringUtils.isBlank(insumoEspecificacao) ? "todos" : "filtro");
        params.put("tipoMovimento2", StringUtils.isBlank(tipoMovimento) ? "todos" : "filtro");
    }

    private void paramsPaginacao1(Map<String, Object> params, final String empresaCod, final String filialCod, final String centroCod, final InsumoSub insumoSub, final Date dataMesRef) {
        params.put("empresaCod", empresaCod);
        params.put("filialCod", filialCod);
        params.put("centroCod", centroCod);
        params.put("insumoSub", insumoSub);
        params.put("dataInicial", DateUtils.toFirstDate(dataMesRef));
        params.put("dataFinal", DateUtils.toLastDate(dataMesRef));
    }

    private void paramsSaidaNumero(Map<String, Object> params, final Long insumoCod, final String insumoEspecificacao, final String empresaCod, final String filialCod, final String centroCod) {
        params.put("insumoCod", insumoCod);
        params.put("insumoEspecificacao", StringBeanUtils.acertaNomeParaLike(insumoEspecificacao, StringBeanUtils.LIKE_MIDDLE));
        params.put("empresaCod", empresaCod);
        params.put("filialCod", filialCod);
        params.put("centroCod", centroCod);
        params.put("insumoCod2", insumoCod == null ? "todos" : "filtro");
        params.put("insumoEspecificacao2", StringUtils.isBlank(insumoEspecificacao) ? "todos" : "filtro");
    }

    private void paramsTransferencias(Map<String, Object> params, final CentroCusto centroOrigem, final CentroCusto centroDestino, final String numeroDocumento, final Date dataMovimentacao) {
        params.put("centroOrigem", centroOrigem);
        params.put("centroDestino", centroDestino);
        params.put("numeroDocumento", numeroDocumento);
        params.put("dataSaida", DateUtils.zerarHora(dataMovimentacao));
    }

    private void paramsSaidaItem(Map<String, Object> params, final Long numeroSaida, final Integer itemNumero) {
        params.put("numeroSaida", numeroSaida);
        params.put("itemNumero", itemNumero);
    }

    public void clearCache() {
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
    }
}
