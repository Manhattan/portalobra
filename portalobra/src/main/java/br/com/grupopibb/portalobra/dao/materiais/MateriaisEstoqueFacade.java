/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.dao.materiais;

import br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalobra.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.CaracterizacaoInsumos;
import br.com.grupopibb.portalobra.model.insumo.ClasseInsumos;
import br.com.grupopibb.portalobra.model.insumo.GrupoInsumos;
import br.com.grupopibb.portalobra.model.materiais.MateriaisEstoque;
import br.com.grupopibb.portalobra.model.tipos.EnumOrderMatEstField;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.StringBeanUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MateriaisEstoqueFacade extends AbstractEntityBeans<MateriaisEstoque, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaisEstoqueFacade() {
        super(MateriaisEstoque.class, MateriaisEstoqueFacade.class);
    }

    /**
     * Procura pelos filtros centroCod, insumoCod, anoMes
     *
     * @return List<MateriaisEstoque>
     */
    public MateriaisEstoque findParam(final CentroCusto centro, final Long insumoCod, final String anoMes) {
        Map<String, Object> params = getMapParams();
        paramsPesquisa(params, centro, insumoCod, anoMes);
        return pesqParam("MateriaisEstoque.find", params);
    }

    /**
     * Procura pelos filtros centroCod, insumoCod, anoMes
     *
     * @return List<MateriaisEstoque>
     */
    public Double findSaldo(final CentroCusto centro, final Long insumoCod, final String anoMes) {
        Map<String, Object> params = getMapParams();
        paramsPesquisa(params, centro, insumoCod, anoMes);
        Double saldo = 0.0;
        MateriaisEstoque mat = pesqParam("MateriaisEstoque.find", params);
        if (mat != null) {
            saldo = NumberUtils.isNull(mat.getEstoqueQuantidade(), 0.0);
        }
        return saldo;
    }

    /**
     * Soma o valor total de cada saldo de estoque dos insumos no centro
     * informado e com o mes informado.
     *
     * @param centro
     * @param anoMes
     * @return
     */
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Double findValorTotal(final CentroCusto centro, final Date anoMes) {
        Map<String, Object> params = getMapParams();
        paramsValorTotal(params, centro, DateUtils.getYearMonth(anoMes));
        Query q = getEntityManager().createNamedQuery("MateriaisEstoque.findValorTotal");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        return NumberUtils.isNull((Double) q.getSingleResult(), 0.0);
        /* 
         String sql = " SELECT SUM(Estoque_Valor) FROM MateriaisEstoque "
         + " WHERE Centro_Cod = cast(? as char(4)) "
         + " AND Filial_Cod = cast(? as char(2)) "
         + " AND Empresa_Cod = cast(? as char(3)) "
         + " AND Estoque_AnoMes = cast(? as char(6)) "
         + " AND Insumo_Cod is not null ";

         Query q = getEntityManager().createNativeQuery(sql);
         q.setParameter(1, centro.getCodigo());
         q.setParameter(2, centro.getFilialCod());
         q.setParameter(3, centro.getEmpresaCod());
         q.setParameter(4, DateUtils.getYearMonth(anoMes));
         Double result = NumberUtils.isNull(((BigDecimal) q.getSingleResult()).doubleValue(), 0.0);
         q = null;
         return result;*/
    }

    public Long pesqCount(final CentroCusto centro, final String insumoCod, final Date anoMes, final CaracterizacaoInsumos caracterizacao, final ClasseInsumos classe, final GrupoInsumos grupo) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, insumoCod, DateUtils.getYearMonth(anoMes), caracterizacao, classe, grupo);
        return pesqCount("MateriaisEstoque.countRange", params);
    }

    public List<MateriaisEstoque> listPesqParamRange(final CentroCusto centro, final String insumoCod, final Date anoMes, final CaracterizacaoInsumos caracterizacao, final ClasseInsumos classe, final GrupoInsumos grupo, final EnumOrderMatEstField orderBy, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, centro, insumoCod, DateUtils.getYearMonth(anoMes), caracterizacao, classe, grupo);

        String namedQuery;

        if (orderBy == EnumOrderMatEstField.VALOR_ASC) {
            namedQuery = "MateriaisEstoque.selectRangeValorAsc";
        } else if (orderBy == EnumOrderMatEstField.VALOR_DESC) {
            namedQuery = "MateriaisEstoque.selectRangeValorDesc";
        } else {
            namedQuery = "MateriaisEstoque.selectRange";
        }
        return listPesqParamRange(namedQuery, params, range[1] - range[0], range[0]);
    }

    private void paramsPesquisa(Map<String, Object> params, final CentroCusto centro, final Long insumoCod, final String anoMes) {
        params.put("centro", centro);
        params.put("insumoCod", insumoCod);
        params.put("anoMes", anoMes);
    }

    private void paramsPaginacao(Map<String, Object> params, final CentroCusto centro, final String insumoCod, final String anoMes, final CaracterizacaoInsumos caracterizacao, final ClasseInsumos classe, final GrupoInsumos grupo) {
        params.put("centro", centro);
        params.put("insumoCod", StringBeanUtils.acertaNomeParaLike(insumoCod, StringBeanUtils.LIKE_END));
        params.put("anoMes", anoMes);

        String caracterizacaoCod = caracterizacao == null ? "" : caracterizacao.getCodigo();
        String grupoCod = grupo == null ? "" : grupo.getCodigo();
        String classeCod = classe == null ? "" : classe.getCodigo();

        params.put("caracterizacao", caracterizacaoCod);
        params.put("grupo", grupoCod);
        params.put("classe", classeCod);

        params.put("caracterizacao2", StringUtils.isBlank(caracterizacaoCod) ? "todos" : "filtro");
        params.put("grupo2", StringUtils.isBlank(grupoCod) ? "todos" : "filtro");
        params.put("classe2", StringUtils.isBlank(classeCod) ? "todos" : "filtro");
        params.put("insumoCod2", insumoCod == null ? "todos" : "filtro");
    }

    private void paramsValorTotal(Map<String, Object> params, final CentroCusto centro, final String anoMes) {
        params.put("centro", centro);
        params.put("anoMes", anoMes);
    }
}
