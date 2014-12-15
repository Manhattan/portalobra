/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra;

import br.com.grupopibb.portalobra.business.pedido.PedidoBusiness;
import br.com.grupopibb.portalobra.model.tipos.EnumPrazoInicioPedido;

/**
 *
 * @author tone.lima
 */
public class Teste {

    public static void main(String[] args) {
        System.out.println(PedidoBusiness.getFormaPagamento("030060", "050050", EnumPrazoInicioPedido.E));
      //  System.out.println("050030".substring(3, 6));
    }
}
