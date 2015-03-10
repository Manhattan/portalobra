/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.materiais;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "MateriaisEntrada_Itens")
@NamedQueries({
    @NamedQuery(name = "MaterialEntradaItens.find",
            query = " SELECT DISTINCT mi FROM MaterialEntradaItens mi "
            + " WHERE (mi.insumo.codigo = :insumoCod) "
            + " AND (mi.empresaCod = :empresaCod) "
            + " AND (mi.centroCod = :centroCod) "
            + " AND (mi.filialCod = :filialCod) "
            + " AND (mi.dataEntrada BETWEEN :dataInicial AND :dataFinal) "
            + " ORDER BY mi.dataEntrada, mi.numero "),
    @NamedQuery(name = "MaterialEntradaItens.findEntradaNumeroByInsumo",
            query = " SELECT DISTINCT (mi.materialEntrada.numeroEntrada) FROM MaterialEntradaItens mi"
            + " WHERE (:insumoCod2 = 'todos' OR mi.insumo.codigo = :insumoCod) "
            + " AND (:insumoEspecificacao2 = 'todos' OR mi.insumo.especificacao LIKE :insumoEspecificacao) "
            + " AND (mi.empresaCod = :empresaCod) "
            + " AND (mi.centroCod = :centroCod) "
            + " AND (mi.filialCod = :filialCod) "),
        @NamedQuery(name = "MaterialEntradaItens.findEstoqueEntradas",
            query = " SELECT SUM(mi.quantidade) FROM MaterialEntradaItens mi"
            + " WHERE (mi.insumo.codigo = :insumoCod) "
            + " AND (mi.dataEntrada BETWEEN :dataInicial AND :dataFinal) "
            + " AND (mi.empresaCod = :empresaCod) "
            + " AND (mi.centroCod = :centroCod) "
            + " AND (mi.filialCod = :filialCod) ")
})
public class MaterialEntradaItens implements EntityInterface<MaterialEntradaItens> {

    public MaterialEntradaItens() {
    }

    public MaterialEntradaItens(MaterialEntrada materialEntrada, Integer numero, String itemItem,
            Insumo insumo, Double quantidade, Double valor, Date dataEntrada, String empresaCod, String filialCod,
            String centroCod, String observacao) {
        this.materialEntrada = materialEntrada;
        this.numero = numero;
        this.itemItem = itemItem;
        this.insumo = insumo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.empresaCod = empresaCod;
        this.filialCod = filialCod;
        this.centroCod = centroCod;
        this.observacao = observacao;
    }
    
    public MaterialEntradaItens(MaterialEntrada materialEntrada, Integer numero, String itemItem,
            Insumo insumo, Double quantidade, Double valor, Date dataEntrada, String empresaCod, String filialCod,
            String centroCod, String observacao, Long saidaNumeroT, Integer saidaItemNumeroT) {
        this.materialEntrada = materialEntrada;
        this.numero = numero;
        this.itemItem = itemItem;
        this.insumo = insumo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.empresaCod = empresaCod;
        this.filialCod = filialCod;
        this.centroCod = centroCod;
        this.observacao = observacao;
        this.saidaNumeroT = saidaNumeroT;
        this.saidaItemNumeroT = saidaItemNumeroT;
    }
    @Id
    @ManyToOne(targetEntity = MaterialEntrada.class)
    @JoinColumn(name = "Entrada_Numero")
    private MaterialEntrada materialEntrada;
    /*
     */
    @Id
    @Column(name = "EntradaItem_Numero")
    private Integer numero;
    /*
     */
    @Column(name = "EntradaItem_Item")
    private String itemItem;
    /*
     */
    @OneToOne(targetEntity = Insumo.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Insumo_Cod")
    private Insumo insumo;
    /*
     */
    @Column(name = "EntradaItem_Quantidade")
    private Double quantidade;
    /*
     */
    @Column(name = "EntradaItem_Valor")
    private Double valor;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "Entrada_Data")
    private Date dataEntrada;
    /*
     */
    @Column(name = "Empresa_Cod")
    private String empresaCod;
    /*
     */
    @Column(name = "Filial_Cod")
    private String filialCod;
    /*
     */
    @Column(name = "Centro_Cod")
    private String centroCod;
    /*
     */
    @Column(name = "EntradaItem_Obs")
    private String observacao;
    /*
     */
    @Column(name = "Transf_Saida_Numero")
    private Long saidaNumeroT;
    /*
     */
    @Column(name = "Transf_SaidaItem_Numero")
    private Integer saidaItemNumeroT;
    /*
     */
    @Override
    public Serializable getId() {
        return materialEntrada.getNumeroEntrada().toString() + '-' + numero.toString();
    }

    @Override
    public String getLabel() {
        return "Insumo: " + insumo;
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return false;
    }

    @Override
    public int compareTo(MaterialEntradaItens o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    public MaterialEntrada getMaterialEntrada() {
        return materialEntrada;
    }

    public void setMaterialEntrada(MaterialEntrada materialEntrada) {
        this.materialEntrada = materialEntrada;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getItemItem() {
        return itemItem;
    }

    public void setItemItem(String itemItem) {
        this.itemItem = itemItem;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public String getFilialCod() {
        return filialCod;
    }

    public void setFilialCod(String filialCod) {
        this.filialCod = filialCod;
    }

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getSaidaNumeroT() {
        return saidaNumeroT;
    }

    public void setSaidaNumeroT(Long saidaNumeroT) {
        this.saidaNumeroT = saidaNumeroT;
    }

    public Integer getSaidaItemNumeroT() {
        return saidaItemNumeroT;
    }

    public void setSaidaItemNumeroT(Integer saidaItemNumeroT) {
        this.saidaItemNumeroT = saidaItemNumeroT;
    }
}