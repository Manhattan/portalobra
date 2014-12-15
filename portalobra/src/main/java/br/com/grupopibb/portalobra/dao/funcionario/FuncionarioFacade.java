/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.funcionario;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Empresa;
import br.com.grupopibb.portalobra.model.tipos.EnumHabilitado;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class FuncionarioFacade extends AbstractEntityBeans<Funcionario, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionarioFacade() {
        super(Funcionario.class, FuncionarioFacade.class);
    }

    public Funcionario findByLogin(final String login) {
        Map<String, Object> params = getMapParams();
        paramsLogin(params, login);
        return pesqParam("Funcionario.findByLogin", params);
    }

    private void paramsLogin(Map<String, Object> params, final String login) {
        params.put("login", login);
    }

    public List<Funcionario> findRangeParam(final String login, final String nome, final Empresa empresa, final CentroCusto centro, final boolean ativo, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, login, nome, empresa, centro, ativo);
        return listPesqParamRange("Funcionario.findParam", params, range[1] - range[0], range[0]);
    }
    
    public Long countParam(final String login, final String nome, final Empresa empresa, final CentroCusto centro, final boolean ativo) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, login, nome, empresa, centro, ativo);
        return pesqCount("Funcionario.countParam", params);
    }
 

    private void paramsPaginacao(Map<String, Object> params, final String login, final String nome, final Empresa empresa, final CentroCusto centro, final boolean ativo) {
        params.put("login", StringBeanUtils.acertaNomeParaLike(login, StringBeanUtils.LIKE_MIDDLE));
        params.put("nome", StringBeanUtils.acertaNomeParaLike(nome, StringBeanUtils.LIKE_WHITESPACE));
        params.put("empresa", empresa);
        params.put("centro", centro != null ? centro.getIdCompleto() : null);
        params.put("ativo", ativo ? EnumHabilitado.S : EnumHabilitado.N);
        params.put("login2", StringUtils.isBlank(login) ? "todos" : "filtro");
        params.put("nome2", StringUtils.isBlank(nome) ? "todos" : "filtro");
        params.put("empresa2", empresa == null ? "todos" : "filtro");
        params.put("centro2", centro == null ? "todos" : "filtro");
    }
}
