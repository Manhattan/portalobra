/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.followup;

import br.com.grupopibb.portalobra.acesso.ConnectionFactory;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.followup.LogFollowup;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntrada;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntradaItens;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaida;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaidaItens;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompra;
import br.com.grupopibb.portalobra.model.solicitacaocompra.SolicitacaoCompraItem;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.model.tipos.log_followup.EnumOpeLogFollowup;
import br.com.grupopibb.portalobra.model.tipos.log_followup.EnumTabLogFollowup;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
//@Interceptors({LogTime.class})
public class LogFollowupFacade extends AbstractEntityBeans<LogFollowup, Integer> {

    @EJB
    ConnectionFactory connectionFactory;
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFollowupFacade() {
        super(LogFollowup.class, LogFollowupFacade.class);
    }

    public void createLogSolic(SolicitacaoCompra main) {
        LogFollowup logF;
        for (SolicitacaoCompraItem item : main.getItens()) {
            logF = new LogFollowup();
            logF.setCodigo(5);
            logF.setCentro(main.getCentro());
            logF.setData(main.getDataSolicitacao());
            logF.setFuncionario(main.getSolicitante().getNome());
            logF.setOperacao(EnumOpeLogFollowup.I);
            logF.setTabela(EnumTabLogFollowup.SOLIC);
            logF.setId1(main.getNumero().intValue());
            logF.setId2(item.getNumero().intValue());
            logF.setInsumoCod(item.getInsumo().getCodigo().intValue());
            logF.setLido(EnumsGeral.N);
            createLog(logF);
            logF = null;
        }
    }

    public void createLogMatEnt(MaterialEntrada main, String funcionario, Integer solicNumero, Integer solicItemNumero) {
        LogFollowup logF;
        for (MaterialEntradaItens item : main.getItens()) {
            logF = new LogFollowup();
            logF.setCentro(main.getCentro());
            logF.setData(main.getDataEntrada());
            logF.setFuncionario(funcionario);
            logF.setOperacao(EnumOpeLogFollowup.U);
            logF.setTabela(EnumTabLogFollowup.MAT_ENT);
            logF.setId1(solicNumero);
            logF.setId2(solicItemNumero);
            logF.setInsumoCod(item.getInsumo().getCodigo().intValue());
            logF.setLido(EnumsGeral.N);
            createLog(logF);
            logF = null;
        }
    }

    public void createLogMatSai(MaterialSaida main, String funcionario, Integer solicNumero, Integer solicItemNumero) {
        LogFollowup logF;
        for (MaterialSaidaItens item : main.getItens()) {
            logF = new LogFollowup();
            logF.setCentro(main.getCentro());
            logF.setData(main.getDataSaida());
            logF.setFuncionario(funcionario);
            logF.setOperacao(EnumOpeLogFollowup.U);
            logF.setTabela(EnumTabLogFollowup.MAT_SAI);
            logF.setId1(solicNumero);
            logF.setId2(solicItemNumero);
            logF.setInsumoCod(item.getInsumo().getCodigo().intValue());
            logF.setLido(EnumsGeral.N);
            createLog(logF);
            logF = null;
        }
    }

    private void createLog(LogFollowup logF) {
        try {
            create(logF);
        } catch (EntityException ex) {
            Logger.getLogger(LogFollowupFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLogFollowup() {
        Query q = getEntityManager().createNativeQuery("exec sp_PO_AtualizaLog");
        q.executeUpdate();
    }
}
