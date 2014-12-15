/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.ar;

import br.com.grupopibb.portalobra.model.tipos.EnumImposto;
import br.com.grupopibb.portalobra.model.tipos.EnumRecolhimentoSelect;
import java.io.Serializable;

/**
 *
 * @author administrator
 */
public class Imposto implements Serializable, Comparable<Imposto> {

    private Integer index;
    private EnumImposto codigo;
    private String nome;
    private Float percentBase;
    private Float baseCalculo;
    private Float percentAliquota;
    private Float calculo;
    private Float minimo;
    private Float valor;
    private EnumRecolhimentoSelect recolhimento;

    public Imposto() {
    }

    /**
     * Construtor para criação de um imposto completo.
     *
     * @param nome
     * @param percentBase
     * @param baseCalculo
     * @param percentAliquota
     * @param calculo
     * @param minimo
     * @param valor
     * @param recolhimento
     */
    public Imposto(Integer index, EnumImposto codigo, String nome, Float percentBase, Float baseCalculo, Float percentAliquota,
            Float calculo, Float minimo, Float valor, EnumRecolhimentoSelect recolhimento) {
        this.index = index;
        this.codigo = codigo;
        this.nome = nome;
        this.percentBase = percentBase;
        this.baseCalculo = baseCalculo;
        this.percentAliquota = percentAliquota;
        this.calculo = calculo;
        this.minimo = minimo;
        this.valor = valor;
        this.recolhimento = recolhimento;
    }

    /**
     * Construtor para criação de Imposto com o valor utilizado em compareTo,
     * simulando a chave primária de uma entidade.
     *
     * @param nome
     */
    public Imposto(EnumImposto codigo) {
        this.codigo = codigo;
    }

    public void calcBaseCalculo(Float total, Float percent) {
        if (percent != null && total != null) {
            baseCalculo = (percent * total) / 100;
            //Calcula o valor de Cálculo
            calcCalculo(baseCalculo, percentAliquota);
        }
    }

    public void calcPercentBase(Float total, Float val) {
        if (val != null && total != null) {
            percentBase = (val / total) * 100;
            //Calcula o valor de Cálculo
            calcCalculo(baseCalculo, percentAliquota);
        }
    }

    public void calcCalculo(Float total, Float percent) {
        if (percent != null && total != null) {
            calculo = (percent * total) / 100;
            valor = calculo;
        }
    }

    public void calcPercentAliq(Float total, Float val) {
        if (val != null && total != null) {
            percentAliquota = (val / total) * 100;
            valor = calculo;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public EnumImposto getCodigo() {
        return codigo;
    }

    public void setCodigo(EnumImposto codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPercentBase() {
        return percentBase;
    }

    public void setPercentBase(Float percentBase) {
        this.percentBase = percentBase;
    }

    public Float getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Float baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public Float getPercentAliquota() {
        return percentAliquota;
    }

    public void setPercentAliquota(Float percentAliquota) {
        this.percentAliquota = percentAliquota;
    }

    public Float getCalculo() {
        return calculo;
    }

    public void setCalculo(Float calculo) {
        this.calculo = calculo;
    }

    public Float getMinimo() {
        return minimo;
    }

    public void setMinimo(Float minimo) {
        this.minimo = minimo;
    }

    public Float getValor() {
        if (minimo != null && valor < minimo) {
            valor = 0F;
        }
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public EnumRecolhimentoSelect getRecolhimento() {
        return recolhimento;
    }

    public void setRecolhimento(EnumRecolhimentoSelect recolhimento) {
        this.recolhimento = recolhimento;
    }

    @Override
    public int compareTo(Imposto o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public String toString() {
        return nome.toString();
    }

    public String getLabel() {
        return nome.toString();
    }
}
