/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.orcamento;

import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItemOrcPlan;
import br.com.grupopibb.portalobra.model.tipos.EnumOpeEvtOrcamento;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
public class OrcamentoFacade implements Serializable {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Executa a procedure spORC_UpdateItemOrcPlanEvt que gera um código de
     * evento.
     *
     * @param orcItem
     */
    public Integer updateItemOrcPlanEvt(SolicitacaoCompraItemOrcPlan itemPlan, EnumOpeEvtOrcamento ope, int numeroEvento) {
        String opCod = ope.toString();
        Integer numeroEvt = numeroEvento;
        Integer codigoPlanoOrc = itemPlan.getPlanCodOrc();
        Integer codigoPlanoItemOrc = itemPlan.getItemPlanCodOrc();
        Integer insumoCod = itemPlan.getInsumoCod().intValue();
        String tipo = itemPlan.getClasseItem();
        String classe = "SO";
        Date data = new Date();
        Date dataRegistro = new Date();
        Double quantidade = itemPlan.getValorSolic();
        Double valor = 0.0;

        em.getTransaction().begin();
        Connection con = em.unwrap(java.sql.Connection.class);

        String sql = "exec spORC_UpdateItemOrcPlanEvt ?,?,?,?,?,?,?,?,?,?,?";
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall(sql);
            stmt.setEscapeProcessing(true);
            stmt.setQueryTimeout(200);

            stmt.setString(1, opCod);

            if (ope != EnumOpeEvtOrcamento.I) {
                stmt.setInt(2, numeroEvento);
            } else {
                stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            }

            stmt.setInt(3, codigoPlanoOrc);
            stmt.setInt(4, codigoPlanoItemOrc);
            stmt.setInt(5, insumoCod);
            stmt.setString(6, tipo);
            stmt.setString(7, classe);
            stmt.setDate(8, new java.sql.Date(DateUtils.zerarHora(data).getTime()));
            stmt.setDate(9, new java.sql.Date(DateUtils.zerarHora(dataRegistro).getTime()));
            stmt.setDouble(10, quantidade);
            stmt.setDouble(11, valor);
            stmt.executeUpdate();

            if (ope == EnumOpeEvtOrcamento.I) {
                numeroEvt = stmt.getInt(2);
            }
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        em.getTransaction().commit();
        return numeroEvt;
    }
}
