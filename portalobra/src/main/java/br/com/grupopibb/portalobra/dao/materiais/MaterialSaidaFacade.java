/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.materiais;

import br.com.grupopibb.portalobra.business.materiais.MaterialBusiness;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.dao.geral.SequenciaisFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaida;
import br.com.grupopibb.portalobra.utils.NumberUtils;
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
public class MaterialSaidaFacade extends AbstractEntityBeans<MaterialSaida, Long> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    SequenciaisFacade sequenciaisFacade;
    @EJB
    MaterialBusiness materialBusiness;
    @EJB
    MaterialSaidaItensFacade materialSaidaItensFacade;

    public MaterialSaidaFacade() {
        super(MaterialSaida.class, MaterialSaidaFacade.class);
    }

    public Integer createMaterialSaida(final MaterialSaida entity) throws EntityException {
        Integer sequencial = sequenciaisFacade.getSequencialMaterialSaida().getNumero().intValue();
        Integer sequencialDep = sequenciaisFacade.getSequencialSaidaDeposito().getNumero().intValue();
        materialBusiness.corrigeMaterialSaida(entity);

        while (entity.getNumeroSaida().intValue() <= sequencial) {
            entity.setNumeroSaida(sequencial.longValue() + 1L);
            sequencial = sequenciaisFacade.getSequencialMaterialSaida().getNumero().intValue();
        }
        if (entity.getTipoMovimento().equals("T")) {
            entity.setNumeroDeposito(findMaxDepositoNumero() + 1);
            while (NumberUtils.isNull(entity.getNumeroDeposito(), 0) <= sequencialDep) {
                entity.setNumeroDeposito(sequencialDep + 1);
                sequencialDep = sequenciaisFacade.getSequencialSaidaDeposito().getNumero().intValue();
            }
        }
        sequenciaisFacade.update(sequenciaisFacade.getSequencialSaidaDeposito().initNumber(sequencialDep));
        super.create(entity);
        return entity.getNumeroSaida().intValue();
    }

    @Override
    public void update(MaterialSaida entity) throws EntityException {
        materialBusiness.corrigeMaterialSaida(entity);
        super.update(entity);
    }

    /**
     * Procura o último número de depósito gerado a partir da saída de material.
     * Esse sequencial é único na tabela MateriaisSaida.
     *
     * @return Integer numeroDeposito.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Integer findMaxDepositoNumero() {
        Query q = getEntityManager().createNamedQuery("MaterialSaida.selectMaxDepositoNumero");
        return (Integer) q.getSingleResult();
    }

    public List<MaterialSaida> findRangeParam(final CentroCusto centro, final Long numeroSaida, final Date dataInicial, final Date dataFinal, final Long insumoCod, final String insumoEspecificacao, final int[] range) {
        Map<String, Object> params = getMapParams();

        String numeroSaida3 = "filtro";
        List<Long> numeros = null;

        if ((insumoCod != null) || (!StringUtils.isBlank(insumoEspecificacao))) {
            numeros = materialSaidaItensFacade.findSaidaNumeroByInsumo(insumoCod, insumoEspecificacao, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        }

        if (numeros == null || numeros.isEmpty()) {
            numeroSaida3 = "todos";
            numeros = numeros == null ? new ArrayList<Long>() : numeros;
            numeros.add(0L);
        }

        paramsPaginacao(params, centro, numeroSaida, dataInicial, dataFinal, numeros, numeroSaida3);
        return listPesqParamRange("MaterialSaida.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long countParam(final CentroCusto centro, final Long numeroSaida, final Date dataInicial, final Date dataFinal, final Long insumoCod, final String insumoEspecificacao) {
        Map<String, Object> params = getMapParams();

        String numeroSaida3 = "filtro";
        List<Long> numeros = null;

        if ((insumoCod != null || !StringUtils.isBlank(insumoEspecificacao)) && centro != null) {
            numeros = materialSaidaItensFacade.findSaidaNumeroByInsumo(insumoCod, insumoEspecificacao, centro.getEmpresaCod(), centro.getFilialCod(), centro.getCodigo());
        }

        if (numeros == null || numeros.isEmpty()) {
            numeroSaida3 = "todos";
            numeros = numeros == null ? new ArrayList<Long>() : numeros;
            numeros.add(0L);
        }

        paramsPaginacao(params, centro, numeroSaida, dataInicial, dataFinal, numeros, numeroSaida3);
        return pesqCount("MaterialSaida.countRange", params);
    }

    public void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final Long numeroSaida, final Date dataInicial, final Date dataFinal, final List<Long> numeros, final String numeroSaida3) {
        params.put("centro", centro);
        params.put("numeroSaida", numeroSaida);
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("numeros", numeros);
        params.put("numeroSaida2", numeroSaida == null ? "todos" : "filtro");
        params.put("numeroSaida3", numeroSaida3);
    }

    public void clearCache() {
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
    }
}
