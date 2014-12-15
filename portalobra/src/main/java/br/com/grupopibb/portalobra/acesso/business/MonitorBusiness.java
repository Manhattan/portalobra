/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso.business;

import br.com.grupopibb.portalobra.acesso.Monitor;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.tipos.EnumSistemaMonitor;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MonitorBusiness {

    /**
     * Define um novo registro de acesso para monitoramento.
     *
     * @param funcionario Funcionario logado.
     * @return Novo Monitor com data e hora atuais.
     */
    public Monitor getNewMonitor(Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }
        
        String host = null;
        try {
            host = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        } catch (NullPointerException | ClassCastException e) {
            host = "unknown";
        }
        if (host == null) {
            host = "unknown";
        }
        return new Monitor(funcionario, new Date(), EnumSistemaMonitor.POBRA, host);
    }
}
