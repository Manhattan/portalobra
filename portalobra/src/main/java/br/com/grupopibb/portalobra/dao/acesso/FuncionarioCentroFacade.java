/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.acesso;

import br.com.grupopibb.portalobra.acesso.FuncionarioCentro;
import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.Map;
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
public class FuncionarioCentroFacade extends AbstractEntityBeans<FuncionarioCentro, Integer> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public FuncionarioCentroFacade() {
        super(FuncionarioCentro.class, FuncionarioCentroFacade.class);
    }
    
    public FuncionarioCentro find(final Funcionario funcionario, final CentroCusto centro){
        Map<String,Object> params = getMapParams();
        paramsFiltro(params, funcionario, centro);
        return pesqParam("FuncionarioCentro.find", params);
    }
    
    private void paramsFiltro(Map<String,Object> params, final Funcionario funcionario, final CentroCusto centro){
        params.put("funcionario", funcionario);
        params.put("centro", centro);
    }
}
