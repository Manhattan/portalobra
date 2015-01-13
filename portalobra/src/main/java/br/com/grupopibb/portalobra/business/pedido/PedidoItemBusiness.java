/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.pedido;

import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrator
 */
public class PedidoItemBusiness {

    public static Float somaTotalItens(List<PedidoItem> itens) {
        Float result = 0F;
        for (PedidoItem i : itens) {
            result += i.getValorTotalItem();
        }
        return result;
    }

    public static Float somaTotalItensComIPI(List<PedidoItem> itens) {
        Float result = 0F;
        for (PedidoItem i : itens) {
            result += i.getValorTotalItemComIPI();
        }
        return result;
    }

    /**
     * Transforma a lista de itens do pedido informada em um array com o código
     * de pedidos desses itens. Se tiver dois itens com o mesmo código só vai
     * gravar um código, ou seja, o código dos pedidos não se repete.
     *
     * @param pedidoItens
     * @return List com o código dos pedidos.
     */
    public List<Long> getNumeroPedidos(List<PedidoItem> pedidoItens) {
        List<Long> numeroPedidos = new ArrayList<>();
        for (PedidoItem item : pedidoItens) {
            //Verifica se já existe o número do pedido desse item em numeroPedidos.
            if (!numeroPedidos.contains(item.getPedido().getNumero())) {
                numeroPedidos.add(item.getPedido().getNumero());
            }
        }
        return numeroPedidos;
    }
}
