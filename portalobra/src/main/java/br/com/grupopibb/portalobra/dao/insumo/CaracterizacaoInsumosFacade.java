/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.insumo;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.insumo.CaracterizacaoInsumos;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tone.lima
 */
@Stateless
@LocalBean
//@Interceptors({LogTime.class})
public class CaracterizacaoInsumosFacade extends AbstractEntityBeans<CaracterizacaoInsumos, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public CaracterizacaoInsumosFacade() {
        super(CaracterizacaoInsumos.class, CaracterizacaoInsumosFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CaracterizacaoInsumos> findParam(final String grupo, final String classe) {
        Map<String, Object> params = getMapParams();
        paramsFiltro(params, grupo, classe);
        return listPesqParam("CaracterizacaoInsumos.findParam", params);
    }

    public CaracterizacaoInsumos findByClasseGrupoCarac(final String classeGrupoCarac) {
        Map<String, Object> params = getMapParams();
        paramsFiltro2(params, classeGrupoCarac);
        return pesqParam("CaracterizacaoInsumos.findByClasseGrupoCarac", params);
    }

    private void paramsFiltro(Map<String, Object> params, final String grupo, final String classe) {
        params.put("grupo", grupo);
        params.put("classe", classe);
    }

    private void paramsFiltro2(Map<String, Object> params, final String classeGrupoCarac) {
        params.put("classeGrupoCarac", classeGrupoCarac);
    }
}
