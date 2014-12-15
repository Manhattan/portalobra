/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.materiais;

import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.geral.CentroCustoFacade;
import br.com.grupopibb.portalobra.dao.materiais.MateriaisEstoqueFacade;
import br.com.grupopibb.portalobra.dao.materiais.MaterialSaidaItensFacade;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.insumo.Insumo;
import br.com.grupopibb.portalobra.model.materiais.MaterialSaidaItens;
import br.com.grupopibb.portalobra.utils.DateUtils;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class MaterialSaidaItensController extends EntityController<MaterialSaidaItens> implements Serializable {

    @EJB
    private MaterialSaidaItensFacade materialSaidaItensFacade;
    @EJB
    private CentroCustoFacade centroCustoFacade;
    @EJB
    private MateriaisEstoqueFacade materiaisEstoqueFacade;
    private MaterialSaidaItens current;
    private CentroCusto centroSelecionado;
    private Date dataInicial;
    private Date dataFinal;
    private String numeroDoc;
    private Long insumoCod;
    private String especificacao;
    private Integer registrosPorPagina = 20;
//-------------------------------

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

    public MaterialSaidaItensFacade getFacade() {
        return materialSaidaItensFacade;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(registrosPorPagina) {
                @Override
                public int getItemsCount() {
                    return getFacade().countParam(centroSelecionado, numeroDoc, dataInicial, dataFinal, insumoCod, especificacao).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRangeParam(centroSelecionado, numeroDoc, dataInicial, dataFinal, insumoCod, especificacao, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    @Override
    protected void setEntity(MaterialSaidaItens t) {
        this.current = t;
    }

    @Override
    protected MaterialSaidaItens getNewEntity() {
        return new MaterialSaidaItens();
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
        numeroDoc = null;
        insumoCod = null;
        especificacao = null;
        recreateTable();
        return super.clean();
    }

    @Override
    public void pesquisar() {
        recreateTable();
    }

    /**
     * Inicia a data inicial na pesquisa de saidas de material com o primeiro
     * dia do mês atual.
     */
    private void initDataInicial() {
        if (dataInicial == null) {
            dataInicial = new Date();
        }
    }

    /**
     * Inicia a data inicial na pesquisa de saidas de material com a data atual.
     */
    private void initDataFinal() {
        if (dataFinal == null) {
            dataFinal = new Date();
        }
    }

    public Double getEstoqueAtual(Insumo insumo) {
        String anoMes = DateUtils.getDataFormatada("YYYYMM", new Date());
        return materiaisEstoqueFacade.findSaldo(centroSelecionado, insumo != null ? insumo.getCodigo() : 0L, anoMes);
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

    /**
     * Traz todos os Centros de Custo cadastrados no sistema.
     *
     * @return SelectItem[] dos Centros de Custo.
     */
    public SelectItem[] getCentroCustoSelect() {
        List<CentroCusto> centro = centroCustoFacade.findAll();
        return JsfUtil.getSelectItems(centro, false, FacesContext.getCurrentInstance());
    }

    public MaterialSaidaItens getCurrent() {
        return current;
    }

    public void setCurrent(MaterialSaidaItens current) {
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

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Long getInsumoCod() {
        return insumoCod;
    }

    public void setInsumoCod(Long insumoCod) {
        this.insumoCod = insumoCod;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public Integer getRegistrosPorPagina() {
        return registrosPorPagina;
    }

    public void setRegistrosPorPagina(Integer registrosPorPagina) {
        this.registrosPorPagina = registrosPorPagina;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }
}
