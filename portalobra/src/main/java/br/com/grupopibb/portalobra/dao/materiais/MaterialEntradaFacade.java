/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.materiais;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.dao.geral.SequenciaisFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntrada;
import br.com.grupopibb.portalobra.model.tipos.EnumSistemaOrigemEstoque;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MaterialEntradaFacade extends AbstractEntityBeans<MaterialEntrada, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    SequenciaisFacade sequenciaisFacade;
    @EJB
    MaterialEntradaItensFacade materialEntradaItensFacade;

    public MaterialEntradaFacade() {
        super(MaterialEntrada.class, MaterialEntradaFacade.class);
    }

    /**
     * Procura o último ID da Solicitação de Compra
     *
     * @return Long numero
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Long findMaxId() {
        Query q = getEntityManager().createNamedQuery("MaterialEntrada.selectMaxId");
        try {
            Long t = (Long) q.getSingleResult();
            return t;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Integer createMaterialEntrada(final MaterialEntrada entity) throws EntityException {
        Integer sequencial = sequenciaisFacade.getSequencialMaterialEntrada().getNumero().intValue();
        while (entity.getNumeroEntrada().intValue() <= sequencial) {
            entity.setNumeroEntrada(sequencial.longValue() + 1L);
            sequencial = sequenciaisFacade.getSequencialMaterialEntrada().getNumero().intValue();
        }
        super.create(entity);
        return entity.getNumeroEntrada().intValue();
    }
    /*
     query = " SELECT DISTINCT m FROM MaterialEntrada m "
     + " WHERE (:numeroEntrada2 = :todos OR m.numeroEntrada = :numeroEntrada) "
     + " AND (m.centro = :centro) "
     + " AND (m.dataEntrada BETWEEN :dataIni AND :dataFin) "
     + " AND (m.numeroDocumento = :numeroDocumento) "
     + " AND (m.entradaOrigem = :entradaOrigem) "
     + " AND (:numeroEntrada3 = :todos OR m.numeroEntrada IN :numeros) ")//,
     
     */

    public List<MaterialEntrada> findRangeParam(final CentroCusto centro, final Long numeroEntrada, final Date dataInicial, final Date dataFinal, final EnumSistemaOrigemEstoque origem, final Long insumoCod, final String insumoEspecificacao, final int[] range) {
        Map<String, Object> params = getMapParams();

        String numeroEntrada3 = "filtro";
        List<Long> numeros = null;

        if ((insumoCod != null) || (!StringUtils.isBlank(insumoEspecificacao))) {
            numeros = materialEntradaItensFacade.findEntradaNumeroByInsumo(insumoCod, insumoEspecificacao, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        }

        if (numeros == null || numeros.isEmpty()) {
            numeroEntrada3 = "todos";
            numeros = numeros == null ? new ArrayList<Long>() : numeros;
            numeros.add(0L);
        }

        paramsPaginacao(params, centro, numeroEntrada, dataInicial, dataFinal, origem, numeros, numeroEntrada3);
        return listPesqParamRange("MaterialEntrada.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long countParam(final CentroCusto centro, final Long numeroEntrada, final Date dataInicial, final Date dataFinal, final EnumSistemaOrigemEstoque origem, final Long insumoCod, final String insumoEspecificacao) {
        Map<String, Object> params = getMapParams();

        String numeroEntrada3 = "filtro";
        List<Long> numeros = null;

        if ((insumoCod != null || !StringUtils.isBlank(insumoEspecificacao)) && centro != null) {
            numeros = materialEntradaItensFacade.findEntradaNumeroByInsumo(insumoCod, insumoEspecificacao, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        }

        if (numeros == null || numeros.isEmpty()) {
            numeroEntrada3 = "todos";
            numeros = numeros == null ? new ArrayList<Long>() : numeros;
            numeros.add(0L);
        }

        paramsPaginacao(params, centro, numeroEntrada, dataInicial, dataFinal, origem, numeros, numeroEntrada3);
        return pesqCount("MaterialEntrada.countRange", params);
    }

    public void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final Long numeroEntrada, final Date dataInicial, final Date dataFinal, final EnumSistemaOrigemEstoque origem, final List<Long> numeros, final String numeroEntrada3) {
        params.put("centro", centro);
        params.put("numeroEntrada", numeroEntrada);
        params.put("dataInicial", DateUtils.addDays(dataInicial, -1));
        params.put("dataFinal", DateUtils.addDays(dataFinal, 1));
        params.put("origem", origem);
        params.put("numeros", numeros);
        params.put("numeroEntrada2", numeroEntrada == null ? "todos" : "filtro");
        params.put("numeroEntrada3", numeroEntrada3);
        params.put("origem2", origem == null ? "todos" : "filtro");
    }

    public void clearCache() {
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
    }
}
