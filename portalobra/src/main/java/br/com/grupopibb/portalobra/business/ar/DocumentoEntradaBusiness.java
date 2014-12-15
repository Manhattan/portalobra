/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.ar;

import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaItem;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.util.List;

/**
 *
 * @author administrator
 */
public final class DocumentoEntradaBusiness {

    /**
     * Rateria o valor de IPI passado para os itens do Documento de Entrada.
     *
     * @param itens que irão receber os valores rateados.
     * @param valorIPI que será rateado.
     */
    public static void rateiaItensIPI(List<DocumentoEntradaItem> itens, Float valorIPI) {
        Integer qtd = itens.size();
        Float part = NumberUtils.arredondarHalfUp(valorIPI / qtd.floatValue());
        Float resto = valorIPI - (part * qtd.floatValue());

        for (int i = 0; i < qtd; i++) {
            if ((i + 1) == qtd) {
                itens.get(i).setValorIPI(part + resto);
                itens.get(i).setAliqIPI(NumberUtils.arredondarHalfUp(((part+resto) / itens.get(i).getTotalItemPedido()) * 100));
            }
            itens.get(i).setValorIPI(part);
            itens.get(i).setAliqIPI(NumberUtils.arredondarHalfUp((part / itens.get(i).getTotalItemPedido()) * 100));
        }
    }
}
