/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.acesso.status.StatusLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */ 
@Stateless
@LocalBean
public class ValidaLogin {
    
    @EJB
    ConnectionFactory cf;

    public int performLogin(String user, String pass) {
        Connection con;
        int i = 0;
        try {
            do {
                con = cf.getConnection(user, pass);
                i++;
            } while (con == null && i <= 3);

            if (con == null) {
                return StatusLogin.INATIVO;
            }
            try (PreparedStatement stmt = con.prepareStatement("select top 1 UserName from UserObjectAccess")) {
                stmt.execute();
                stmt.close();
            }
            con.close();
            return StatusLogin.ATIVO;
        } catch (SQLException e) {
            return StatusLogin.INATIVO;
        }
    }
}
