/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.solicitacaocompra;

import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class SolicitacaoCompraBusiness {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Verifica se a lista de itens da Solicitação de Compra contém o Insumo
     * passado.
     *
     * @param solic Solicitação de Compra que contém a lista de itens.
     * @param insumo Insumo a ser comparado.
     * @return Verdadeiro ou falso.
     */
    public boolean isContainsInsumo(SolicitacaoCompra solic, Insumo insumo) {
        for (SolicitacaoCompraItem item : solic.getItens()) {
            if (item.getInsumo().getCodigo() == insumo.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica todos os itens da Solicitação de Compra e retorna uma lista dos
     * insumos que são contidos em mais de um item. Porém irá retornar apenas
     * uma instância desse Insumo.
     *
     * @param solic Solicitação de Compra com os itens a serem verificados.
     * @return Lista com os insumos que possuem duplicatas.
     */
    public List<Insumo> getDistinctDuplicatedInsumo(SolicitacaoCompra solic) {
        List<Insumo> insumos = new ArrayList<>();
        for (SolicitacaoCompraItem item : solic.getItens()) {
            int i = 0;
            Insumo insumo = item.getInsumo();
            for (SolicitacaoCompraItem it : solic.getItens()) {
                if (it.getInsumo() == insumo) {
                    i++;
                }
            }
            if (i >= 2 && !insumos.contains(insumo)) {
                insumos.add(insumo);
            }
            i = 0;
        }

        return insumos;
    }

    public Double getTotalComprasInsumo(CentroCusto centro, Insumo insumo) {
        String sql = "exec dbo.sp_PO_TotCompInsumoCentro ?,?,?,?";
        try {
            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter(1, centro.getEmpresaCod());
            q.setParameter(2, centro.getFilialCod());
            q.setParameter(3, centro.getCodigo());
            q.setParameter(4, insumo.getCodigo().intValue());
            Double result = ((BigDecimal) q.getSingleResult()).doubleValue();
            return result;
        } catch (NullPointerException | NumberFormatException | ClassCastException | NoResultException ex) {
            return 0.0;
        }
    }
}
