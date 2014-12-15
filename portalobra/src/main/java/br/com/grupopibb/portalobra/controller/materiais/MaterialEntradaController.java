/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.materiais;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.materiais.MaterialEntradaFacade;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.materiais.MaterialEntrada;
import br.com.grupopibb.portalobra.model.tipos.EnumSistemaOrigemEstoque;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class MaterialEntradaController extends EntityController<MaterialEntrada> implements Serializable {

    @EJB
    private MaterialEntradaFacade materialEntradaFacade;
    private MaterialEntrada current;
    private CentroCusto centroSelecionado;
    private Date dataInicial;
    private Date dataFinal;
    private Long numeroEntrada;
    private Long insumoCod;
    private EnumSistemaOrigemEstoque origem = EnumSistemaOrigemEstoque.SIMAT;
    private String especificacao;

    /**
     * Executado após o bean JSF ser criado.
     */
    @PostConstruct
    public void init() {
        initDataInicial();
        initDataFinal();
    }

    /**
     * Executado antes do bean JSF ser destruído.
     */
    @PreDestroy
    public void end() {
    }

    public MaterialEntradaFacade getFacade() {
        return materialEntradaFacade;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().countParam(centroSelecionado, numeroEntrada, dataInicial, dataFinal, origem, insumoCod, especificacao).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRangeParam(centroSelecionado, numeroEntrada, dataInicial, dataFinal, origem, insumoCod, especificacao, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    @Override
    protected void setEntity(MaterialEntrada t) {
        this.current = t;
    }

    @Override
    protected MaterialEntrada getNewEntity() {
        return new MaterialEntrada();
    }

    @Override
    protected void performDestroy() {
    }

    @Override
    protected String create() {
        return "";
    }

    @Override
    protected String update() {
        return "";
    }

    @Override
    public String clean() {
        initDataInicial();
        initDataFinal();
        numeroEntrada = null;
        insumoCod = null;
        origem = null;
        especificacao = null;
        recreateTable();
        return super.clean();
    }

    public void pesquisa() {
        recreateTable();
    }

    /**
     * Inicia a data inicial na pesquisa de entradas de material com o primeiro
     * dia do mês atual.
     */
    private void initDataInicial() {
        if (dataInicial == null) {
            dataInicial = DateUtils.incrementar(new Date(), -3, Calendar.MONTH);
        }
    }

    /**
     * Inicia a data inicial na pesquisa de entradas de material com a data
     * atual.
     */
    private void initDataFinal() {
        if (dataFinal == null) {
            dataFinal = new Date();
        }
    }

    /**
     * Inicia o centro de custo atual utilizado para as consultas e inserções
     * desse controller.
     *
     * @param centroSelecionado Centro de Custo que está selecionado no
     * loginController.
     */
    public void initCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
        recreateTable();
    }

    public SelectItem[] getOrigemSelect() {
        return JsfUtil.getEnumSelectItems(EnumSistemaOrigemEstoque.class, false, FacesContext.getCurrentInstance());
    }

    public MaterialEntrada getCurrent() {
        return current;
    }

    public void setCurrent(MaterialEntrada current) {
        this.current = current;
    }

    public CentroCusto getCentroSelecionado() {
        return centroSelecionado;
    }

    public void setCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(Long numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public Long getInsumoCod() {
        return insumoCod;
    }

    public void setInsumoCod(Long insumoCod) {
        this.insumoCod = insumoCod;
    }

    public EnumSistemaOrigemEstoque getOrigem() {
        return origem;
    }

    public void setOrigem(EnumSistemaOrigemEstoque origem) {
        this.origem = origem;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    /**
     * Limpa o cache da tabela do MaterialEntradaController da request
     * atual.
     */
    public void updateDataModel() {
        clean();
        materialEntradaFacade.clearCache();
    }
}
