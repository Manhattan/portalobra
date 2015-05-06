/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.projeto;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.projeto.ProjetoPlanejamento;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class ProjetoPlanejamentoFacade extends AbstractEntityBeans<ProjetoPlanejamento, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public ProjetoPlanejamentoFacade() {
        super(ProjetoPlanejamento.class, ProjetoPlanejamentoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ProjetoPlanejamento> findByCentro(final CentroCusto centro) {
        Map<String, Object> params = getMapParams();
        paramsFiltroCentro(params, centro);
        return listPesqParam("ProjetoPlanejamento.findByCentro", params);
    }

    public Map<String, Integer> findProjetoCod(final CentroCusto centro) {
        Map<String, Integer> codigos = new HashMap<>();
        if (centro != null) {
            Query q = getEntityManager().createNativeQuery("exec sp_PO_getProjetoCod ?, ?, ?");
            q.setParameter(1, centro.getEmpresaCod());
            q.setParameter(2, centro.getFilialCod());
            q.setParameter(3, centro.getCodigo());


            try {
                Object[] resultList = q.getResultList().toArray();
                if (resultList != null && resultList.length > 0) {
                    Object[] obj = (Object[]) resultList[0];
                    if (obj.length > 0) {
                        codigos.put("ProjCod", (Integer) obj[0]);
                        codigos.put("OrcCod", (Integer) obj[1]);
                        codigos.put("PlanCod", (Integer) obj[2]);
                    }
                }
            } catch (Exception e) {
                codigos.clear();
                codigos.put("ProjCod", 0);
                codigos.put("OrcCod", 0);
                codigos.put("PlanCod", 0);
            }
        }

        if (codigos.isEmpty()) {
            codigos.put("ProjCod", 0);
            codigos.put("OrcCod", 0);
            codigos.put("PlanCod", 0);
        }

        return codigos;
    }

    /**
     * Pesquisa o valor orçado de determinado insumo em um centro de custo.
     *
     * @param centro Centro de custo com orçamento.
     * @param insumoCod Insumo a ser analisado.
     * @return Quantidade de insumo a realizar.
     */
    public Double getValorOrc(final int projCod, final int orcCod, final int planCod, final Integer insumoCod) {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_getValorOrcado ?, ?, ?, ?");
        q.setParameter(1, projCod);
        q.setParameter(2, orcCod);
        q.setParameter(3, planCod);
        q.setParameter(4, insumoCod);

        Double resultado = ((BigDecimal) q.getSingleResult()).doubleValue();
        return resultado;
    }

    /**
     * Verifica se um centro de custo possui link com orçamento.
     *
     * @param centro Centro de custo a ser analisado.
     * @return Verdadeiro ou falso.
     */
    public boolean isCentroLinkOrcamento(CentroCusto centro) {
        List<ProjetoPlanejamento> lista;
        lista = findByCentro(centro);
        if (lista == null || lista.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void paramsFiltroCentro(Map<String, Object> params, final CentroCusto centro) {
        params.put("centro", centro);
    }
}
