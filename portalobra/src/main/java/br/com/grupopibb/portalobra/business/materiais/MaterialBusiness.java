/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.business.materiais;

import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntrada;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntradaItens;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaida;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaidaItens;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import br.com.grupopibb.portalobra.utils.UtilBeans;
import java.math.BigDecimal;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MaterialBusiness {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void corrigeDiaMovimento(MaterialEntrada materialEntrada) {
        materialEntrada.setDiaEntrada(NumberUtils.preencheZeroEsquerda(materialEntrada.getDiaEntrada(), 2));
    }

    private void corrigeDiaMovimento(MaterialSaida materialSaida) {
        materialSaida.setDiaSaida(NumberUtils.preencheZeroEsquerda(materialSaida.getDiaSaida(), 2));
    }

    private void corrigeCentroDestino(MaterialSaida materialSaida) {
        if (materialSaida.getTipoMovimento().equals("T")) {
            materialSaida.setCentroCodDest(materialSaida.getCentroDestino().getCodigo());
            materialSaida.setFilialCodDest(materialSaida.getCentroDestino().getFilialCod());
            materialSaida.setEmpresaCodDest(materialSaida.getCentroDestino().getEmpresaCod());
        } else {
            materialSaida.setCentroCodDest("");
            materialSaida.setFilialCodDest("");
            materialSaida.setEmpresaCodDest("");
        }
    }

    /**
     * Prepara o Material de Saida para ser criado ou atualizado.
     *
     * @param materialSaida Material de Saida a ser corrigido.
     */
    public void corrigeMaterialSaida(MaterialSaida materialSaida) {
        corrigeDiaMovimento(materialSaida);
        corrigeCentroDestino(materialSaida);
    }

    /**
     * Prepara o Material de Entrada para ser criado ou atualizado.
     *
     * @param materialEntrada Material de Entrada a ser corrigido.
     */
    public void corrideMaterialEntrada(MaterialEntrada materialEntrada) {
        corrigeDiaMovimento(materialEntrada);
    }

    /**
     * Verifica se a lista de itens do Material de Entrada contém o Insumo
     * passado.
     *
     * @param matEnt Material de Entrada que contém a lista de itens.
     * @param insumo Insumo a ser comparado.
     * @return Verdadeiro ou falso.
     */
    public boolean isContainsInsumo(MaterialEntrada matEnt, Insumo insumo) {
        for (MaterialEntradaItens item : matEnt.getItens()) {
            if (item.getInsumo().getCodigo() == insumo.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converte um MaterialSaidaItens para um MaterialEntradaItens.
     *
     * @param itemSaida Item de saída a ser convertido.
     * @return Item de entrada.
     */
    public MaterialEntradaItens convertItemSaidaToItemEntrada(MaterialSaidaItens itemSaida, MaterialEntrada materialEntrada) {
        if (materialEntrada != null && itemSaida != null) {
            Integer itemNum = incrementItemMatEntrada(materialEntrada);
            String itemItem = "000" + String.valueOf(itemNum);
            itemItem = StringUtils.substring(itemItem, (itemItem.length() - 3));
            return new MaterialEntradaItens(materialEntrada, itemNum, itemItem, 
                    itemSaida.getInsumo(), itemSaida.getQuantidade(), itemSaida.getValor(), 
                    materialEntrada.getDataEntrada(), materialEntrada.getCentro().getEmpresaCod(), 
                    materialEntrada.getCentro().getFilialCod(), materialEntrada.getCentro().getCodigo(), 
                    itemSaida.getObservacao(), itemSaida.getMaterialSaida().getNumeroSaida(), itemSaida.getNumero());
        } else {
            return null;
        }
    }

    /**
     * Retorna o número do item a ser incrementado nos itens do Material de
     * Entrada.
     *
     * @param materialEntrada
     * @return
     */
    private Integer incrementItemMatEntrada(MaterialEntrada materialEntrada) {
        if (materialEntrada != null && materialEntrada.getItens() != null) {
            Integer itemMaior = 1;
            for (MaterialEntradaItens item : materialEntrada.getItens()) {
                while (item.getNumero() >= itemMaior) {
                    itemMaior++;
                }
            }
            return itemMaior;
        } else {
            return 1;
        }
    }

    public Double getEntradaUltimoPreco(CentroCusto centro, Long insumoCod) {
        try {
            Query q = getEntityManager().createNativeQuery("exec sp_PO_EntradaUltimoPreco ?, ?, ?, ?");
            q.setParameter(1, centro.getEmpresaCod());
            q.setParameter(2, centro.getFilialCod());
            q.setParameter(3, centro.getCodigo());
            q.setParameter(4, insumoCod.intValue());
            Object value = q.getSingleResult();
            if (value == null) {
                return 0.0;
            }
            return ((BigDecimal) value).doubleValue();
        } catch (NullPointerException | NumberFormatException | NoResultException e) {
            return 0.0;
        }
    }
}
