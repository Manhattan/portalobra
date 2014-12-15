/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.pedido;

import br.com.grupopibb.portalobra.model.tipos.EnumPrazoInicioPedido;
import br.com.grupopibb.portalobra.utils.NumberUtils;

/**
 *
 * @author administrator
 */
public class PedidoBusiness {

    /**
     * Formata as condições de pagamentos a serem exibidas no relatório do
     * Pedido de Compras.
     *
     * @param prazoDias String com prazo em dias. Esse dado vem diretamente do
     * Pedido.
     * @param prazoPercentual String com percentual para cada prazo. Esse dado
     * vem diretamente do Pedido.
     * @param prazoInicio Enum com o tipo de inicio de pagamento.
     * @return Texto completo descrevendo as formas de pagamento.
     */
    public static String getFormaPagamento(String prazoDias, String prazoPercentual, EnumPrazoInicioPedido prazoInicio) {
        int size = prazoDias.length();
        String texto = "";
        try {
            for (int i = 0; i < size;) {
                float percent = Float.parseFloat(prazoPercentual.substring(i, i + 3));
                int prazo = Integer.parseInt(prazoDias.substring(i, i + 3));

                texto += NumberUtils.formatPercent(percent) + " com " + prazo + " dias, ";
                i += 3;
            }
            texto += prazoInicio.getLabel();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            texto = "";
        }
        return texto;
    }
}
