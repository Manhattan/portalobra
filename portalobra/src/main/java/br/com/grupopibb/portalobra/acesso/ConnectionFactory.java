/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/** 
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class ConnectionFactory {

    public Connection getConnection(String user, String pass) {
        try {
            return DriverManager.getConnection("jdbc:sqlserver://"
                    +UtilBeans.SERVER_NAME+":"
                    +UtilBeans.PORT
                    +";databaseName=" 
                    + UtilBeans.DATABASE_NAME,
                    user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public Connection getConnectionOpen(){
        return getConnection(UtilBeans.DATABASE_USER, UtilBeans.DATABASE_PASS);
    }
}
