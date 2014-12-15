/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.solicitacaocompra;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.solicitacaocompra.Solicitante;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
@Stateless
//@Interceptors({LogTime.class})
public class SolicitanteFacade extends AbstractEntityBeans<Solicitante, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public SolicitanteFacade() {
        super(Solicitante.class, SolicitanteFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Solicitante> findSolicitantes(final String nome, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, nome);
        return listPesqParam("Solicitante.selectRange", params, range[1] - range[0], range[0]);
    }

    public Solicitante findByName(String nome) {
        Map<String, Object> params = getMapParams();
        paramsSelectOne(params, nome);
        return pesqParam("Solicitante.findOneByName", params);
    }

    public Long countSolicitantes(final String nome) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, nome);
        return pesqCount("Solicitante.countRange", params);
    }

    public Integer findLastId(int increment) {
        try {
            Query q = getEntityManager().createNamedQuery("Solicitante.findLastId");
            Integer id = Integer.parseInt((String) q.getSingleResult()) + increment;
            return NumberUtils.isNull(id, 0);
        } catch (NullPointerException | NoResultException e) {
            return 0;
        }
    }

    private void paramsPaginacao(Map<String, Object> params, final String nome) {
        params.put("nome2", StringUtils.isBlank(nome) ? "todos" : "filtro");
        params.put("nome", StringBeanUtils.acertaNomeParaLike(nome, StringBeanUtils.LIKE_MIDDLE));
    }

    private void paramsSelectOne(Map<String, Object> params, final String nome) {
        params.put("nome2", StringUtils.isBlank(nome) ? "todos" : "filtro");
        params.put("nome", nome);
    }
}
