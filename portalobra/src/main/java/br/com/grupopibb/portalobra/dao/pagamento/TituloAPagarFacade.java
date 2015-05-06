/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.pagamento;

import br.com.grupopibb.portalobra.acesso.ConnectionFactory;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.pagamento.TituloAPagar;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class TituloAPagarFacade extends AbstractEntityBeans<TituloAPagar, Long> {

    @EJB
    private ConnectionFactory connectionFactory;
    
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TituloAPagarFacade() {
        super(TituloAPagar.class, TituloAPagarFacade.class);
    }

    public boolean isTituloConciliado(Integer numeroTitulo) {
        String sql = "select top 1 MovFin_Conciliado from Titulos_a_Pagar TP "
                + "inner join Pagamentos P on (P.Pag_Numero = TP.Pag_Numero) "
                + "inner join Movimento_Financeiro MF on (P.MovFin_Numero = MF.MovFin_Numero)"
                + "where TP.TitaPag_Numero = " + numeroTitulo;
        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (rs.getString(1).equals("C"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloAPagarFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Date getDataConcilicacao(int numeroTitulo) {
        String sql = "select top 1 MovFin_DataConciliado from Titulos_a_Pagar TP "
                + "inner join Pagamentos P on (P.Pag_Numero = TP.Pag_Numero) "
                + "inner join Movimento_Financeiro MF on (P.MovFin_Numero = MF.MovFin_Numero)"
                + "where TP.TitaPag_Numero = " + numeroTitulo;
        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (rs.getDate(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloAPagarFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
