/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.insumo;

import br.com.grupopibb.portalobra.model.insumo.Insumo;
import java.util.List;

/**
 *
 * @author administrator
 */
public class InsumoBusiness {

    /**
     * Verifica se um Insumo está dentro de uma lista de insumos informada.
     * Utiliza o campo código para comparação.
     *
     * @param insumos Lista de insumos a ser percorrida.
     * @param insumo Insumo a ser pesquisado na lista.
     * @return Verdadeiro ou falso se o Insumo está ou não na lista.
     */
    public static boolean isInsumoInList(List<Insumo> insumos, Insumo insumo) {
        if (insumos == null || insumo == null) {
            return false;
        }
        for (Insumo in : insumos) {
            if (in.getCodigo() == insumo.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pesquisa a posição do Insumo informado em uma lista de insumos. Utiliza o
     * campo código para comparação.
     *
     * @param insumos Lista de insumos a ser percorrida.
     * @param insumo Insumo a ser pesquisado na lista.
     * @return Posição do Insumo na lista.
     */
    public static int getIndexInsumo(List<Insumo> insumos, Insumo insumo) {
        if (insumos == null || insumo == null){
            return -1;
        }
        for (Insumo in : insumos) {
            if (insumo.getCodigo() == in.getCodigo()) {
                return insumos.indexOf(in);
            }
        }
        return -1;
    }

    /**
     * Substitui um Insumo em uma lista de insumos.
     *
     * @param insumo
     */
    public static void substituteInsumo(Insumo insumo, List<Insumo> insumos) {
        if (isInsumoInList(insumos, insumo)) {
            int i = getIndexInsumo(insumos, insumo);
            insumos.set(i, insumo);
        }
    }
    
}
