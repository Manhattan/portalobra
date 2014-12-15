/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.insumo;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.insumo.GrupoInsumos;
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
public class GrupoInsumosFacade extends AbstractEntityBeans<GrupoInsumos, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public GrupoInsumosFacade() {
        super(GrupoInsumos.class, GrupoInsumosFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<GrupoInsumos> findByClasse(final String classe){
        Map<String, Object> params = getMapParams();
        paramsFiltro(params, classe);
        return listPesqParam("GrupoInsumos.findByClasse", params);
    }

    public GrupoInsumos findByClasseGrupo(final String classeGrupo){
        Map<String, Object> params = getMapParams();
        paramsFiltro2(params, classeGrupo);
        return pesqParam("GrupoInsumos.findByClasseGrupo", params);
    }
    
    /**
     * Params do FindByClasse
     * @param params
     * @param classe 
     */
    private void paramsFiltro(Map<String, Object> params, final String classe){
        params.put("classe", classe);
    }
    
    /**
     * Params do FindByClasseGrupo
     * @param params
     * @param classeGrupo
     */
    private void paramsFiltro2(Map<String, Object> params, final String classeGrupo){
        params.put("classeGrupo", classeGrupo);
    }
}
