/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.geral;

import br.com.grupopibb.portalobra.model.geral.PosicaoCredor;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class PosicaoCredorBusiness implements Serializable {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<PosicaoCredor> getParcelas(Date data, String empresaCod, String nucleoCod, String credorCod) {
        return getParcelasCredor(data, empresaCod, nucleoCod, credorCod, false);
    }

    public List<PosicaoCredor> getPrevisoes(Date data, String empresaCod, String nucleoCod, String credorCod) {
        return getParcelasCredor(data, empresaCod, nucleoCod, credorCod, true);
    }

    /**
     * Chama a stored procedure com as parcelas do Credor, retornando as
     * parcelas liquidadas ou previsões de acordo com o filtro "pendente".
     *
     * @param data Date referente a data de vencimento do titulo a pagar.
     * @param empresaCod String com o código da empresa.
     * @param nucleoCod String com o código do núcleo.
     * @param credorCod String com o código do credor.
     * @param pendente booleano que define o tipo de parcela a retornar, caso
     * false, traz as parcelas liquidadas, caso true, as previsões.
     * @return List<PosicaoCredor> com as parcelas liquidadas ou previsões.
     */
    private List<PosicaoCredor> getParcelasCredor(Date data, String empresaCod, String nucleoCod, String credorCod, boolean pendente) {
        try {
            String sql = "exec dbo.sp_PO_Posicao_Credor ?, ?, ?, ?, ?";
            String pendenteSN = pendente ? "S" : "N";
            String dataFmt = "";
            if (data != null) {
                dataFmt = DateUtils.getDataFormatada("yyyy-MM-dd", DateUtils.zerarHora(data));
            }

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter(1, java.sql.Date.valueOf(dataFmt));
            q.setParameter(2, empresaCod);
            q.setParameter(3, nucleoCod);
            q.setParameter(4, credorCod);
            q.setParameter(5, pendenteSN);
            Object[] resultList = q.getResultList().toArray();

            List<PosicaoCredor> posCredList = new ArrayList<>();
            for (int cont = 0; cont < resultList.length; cont++) {
                Object[] obj = (Object[]) resultList[cont];
                PosicaoCredor posCred;
                posCred = new PosicaoCredor(
                        (Integer) obj[0],
                        (String) obj[1],
                        (Date) obj[2],
                        ((BigDecimal) obj[3]).floatValue(),
                        ((BigDecimal) obj[4]).floatValue(),
                        ((BigDecimal) obj[5]).floatValue(),
                        ((BigDecimal) obj[6]).floatValue(),
                        ((BigDecimal) obj[7]).floatValue(),
                        ((BigDecimal) obj[8]).floatValue(),
                        ((BigDecimal) obj[9]).floatValue(),
                        ((BigDecimal) obj[10]).floatValue(),
                        (String) obj[11],
                        (String) obj[12],
                        (String) obj[13],
                        (String) obj[14],
                        (String) obj[15],
                        (String) obj[16],
                        (String) obj[17],
                        (String) obj[18],
                        (String) obj[19],
                        (String) obj[20],
                        (String) obj[21],
                        (Integer) obj[22],
                        (Integer) obj[23],
                        (Date) obj[24],
                        (String) obj[25],
                        (String) obj[26],
                        (Date) obj[27],
                        (Date) obj[28]);

                posCredList.add(posCred);
            }
            return posCredList;
        } catch (NullPointerException | IllegalArgumentException e) {
            return null;
        }
    }
}
