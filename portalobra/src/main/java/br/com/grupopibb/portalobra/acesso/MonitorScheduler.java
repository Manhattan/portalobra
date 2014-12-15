/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.acesso.dao.MonitorFacade;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import java.util.Date;
import java.util.Timer;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MonitorScheduler {

    @EJB
    private MonitorFacade monitorFacade;
    private Timer timer;
    private Timer timerGeral;

    public void init(MonitorTask monitorTask) {
        timer = new Timer();
        timer.schedule(monitorTask, new Date(), 1000l * 30l);
    }
    
    public void initMonitorGeral(){
        timerGeral = new Timer();
        timerGeral.schedule(new MonitorGeralTask(monitorFacade), new Date(), 1000l * 40l);
    }
}
