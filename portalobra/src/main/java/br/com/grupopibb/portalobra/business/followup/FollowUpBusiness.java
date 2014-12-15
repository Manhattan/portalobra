/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.followup;

import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntradaItens;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntradasESaidas;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaidaItens;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class FollowUpBusiness {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<MaterialEntradasESaidas> mountEntradasESaidas(List<MaterialEntradaItens> entradas, List<MaterialSaidaItens> saidas) {
        List<MaterialEntradasESaidas> materiais = new ArrayList();
        for (MaterialEntradaItens mat : entradas) {
            materiais.add(new MaterialEntradasESaidas(mat.getDataEntrada(), "E", mat.getMaterialEntrada().getTipoMovimento().name(), mat.getMaterialEntrada().getTipoDocumento(), mat.getMaterialEntrada().getNumeroDocumento(), mat.getMaterialEntrada().getNumeroEntrada(), mat.getItemItem(), mat.getQuantidade()));
        }
        for (MaterialSaidaItens mat : saidas) {
            materiais.add(new MaterialEntradasESaidas(mat.getDataSaida(), "S", mat.getMaterialSaida().getTipoMovimento(), mat.getMaterialSaida().getTipoDocumento(), mat.getMaterialSaida().getNumeroDocumento(), mat.getMaterialSaida().getNumeroSaida(), mat.getItemItem(), mat.getQuantidade()));
        }
        Collections.sort(materiais);
        return materiais;
    }

    /**
     * Executa a procedure sp_PO_UpdateFollowUpSolicitacao. Com isso, será
     * atualizado o local do item no FollowUp e a situação do mesmo.
     *
     * @param user
     * @param pass
     * @param centro
     * @param solicitacaoCompra
     * @throws SQLException
     */
    public void atualizaFollowUp(CentroCusto centro, SolicitacaoCompra solicitacaoCompra) throws SQLException {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_UpdateFollowUpSolicitacao ?, ?, ?, ?, ?, ?");
        q.setParameter(1, centro.getEmpresaCod());
        q.setParameter(2, centro.getFilialCod());
        q.setParameter(3, centro.getCodigo());
        q.setParameter(4, solicitacaoCompra.getNumero().intValue());
        q.setParameter(5, null);
        q.setParameter(6, null);

        q.executeUpdate();

    }

    public void atualizaFollowUp(CentroCusto centro) throws SQLException {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_UpdateFollowUpSolicitacao ?, ?, ?, ?, ?, ?");
        q.setParameter(1, centro.getEmpresaCod());
        q.setParameter(2, centro.getFilialCod());
        q.setParameter(3, centro.getCodigo());
        q.setParameter(4, null);
        q.setParameter(5, null);
        q.setParameter(6, null);

        q.executeUpdate();
    }

    public void atualizaFollowUp(CentroCusto centro, Insumo insumo) throws SQLException {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_UpdateFollowUpSolicitacao ?, ?, ?, ?, ?, ?");
        q.setParameter(1, centro.getEmpresaCod());
        q.setParameter(2, centro.getFilialCod());
        q.setParameter(3, centro.getCodigo());
        q.setParameter(4, null);
        q.setParameter(5, null);
        q.setParameter(6, insumo.getCodigo().intValue());

        q.executeUpdate();
    }

    /**
     * Atualiza a quantidade orçada a realizar de todas as incidências de
     * determinado insumo no followup.
     *
     * @param centro
     * @param insumo
     * @throws SQLException
     */
    public void atualizaOrcamentoFollowUp(CentroCusto centro, Insumo insumo) throws SQLException {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_UpdateOrcamentoFollowUpSolicitacao ?, ?, ?, ?");
        q.setParameter(1, centro.getEmpresaCod());
        q.setParameter(2, centro.getFilialCod());
        q.setParameter(3, centro.getCodigo());
        q.setParameter(4, insumo.getCodigo().intValue());

        q.executeUpdate();
    }
}
