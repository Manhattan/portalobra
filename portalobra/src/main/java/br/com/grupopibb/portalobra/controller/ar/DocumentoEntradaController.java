/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.ar;

import br.com.grupopibb.portalobra.acesso.controller.LoginController;
import br.com.grupopibb.portalobra.business.ar.DocumentoEntradaBusiness;
import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.ar.AvisoRecebimentoFacade;
import br.com.grupopibb.portalobra.dao.ar.DocumentoEntradaFacade;
import br.com.grupopibb.portalobra.dao.ar.DocumentoEntradaTipoFacade;
import br.com.grupopibb.portalobra.dao.geral.EstadoFacade;
import br.com.grupopibb.portalobra.dao.geral.NaturezaFiscalFacade;
import br.com.grupopibb.portalobra.dao.geral.SequenciaisFacade;
import br.com.grupopibb.portalobra.dao.pagamento.EspecieTituloAPagarFacade;
import br.com.grupopibb.portalobra.dao.pagamento.TituloAPagarFacade;
import br.com.grupopibb.portalobra.dao.pedido.PedidoItemFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.ar.AvisoRecebimento;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntrada;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaItem;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaTipo;
import br.com.grupopibb.portalobra.model.ar.Imposto;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.insumo.CaracterizacaoInsumos;
import br.com.grupopibb.portalobra.model.insumo.ClasseInsumos;
import br.com.grupopibb.portalobra.model.insumo.GrupoInsumos;
import br.com.grupopibb.portalobra.model.insumo.InsumoSub;
import br.com.grupopibb.portalobra.model.pagamento.TituloAPagar;
import br.com.grupopibb.portalobra.model.pedido.PedidoItem;
import br.com.grupopibb.portalobra.model.tipos.EnumImposto;
import br.com.grupopibb.portalobra.model.tipos.EnumRecolhimentoImposto;
import br.com.grupopibb.portalobra.model.tipos.EnumRecolhimentoSelect;
import br.com.grupopibb.portalobra.model.tipos.EnumSituacaoPagamento;
import br.com.grupopibb.portalobra.model.tipos.EnumTipoEntrada;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import br.com.grupopibb.portalobra.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class DocumentoEntradaController extends EntityController<DocumentoEntrada> implements Serializable {

    @EJB
    private DocumentoEntradaFacade documentoEntradaFacade;
    @EJB
    private DocumentoEntradaTipoFacade documentoEntradaTipoFacade;
    @EJB
    private EstadoFacade estadoFacade;
    @EJB
    private PedidoItemFacade pedidoItemFacade;
    @EJB
    private NaturezaFiscalFacade naturezaFiscalFacade;
    @EJB
    private EspecieTituloAPagarFacade especieTituloAPagarFacade;
    @EJB
    private SequenciaisFacade sequenciaisFacade;
    @EJB
    private AvisoRecebimentoFacade avisoRecebimentoFacade;
    @EJB
    private TituloAPagarFacade tituloAPagarFacade;
    private DocumentoEntrada current;
    private List<DocumentoEntrada> currentList;
    private List<DocumentoEntradaItem> currentItens;
    private AvisoRecebimento avisoRecebimento;
    private List<List<EnumImposto>> impostosDisponiveis;
    private List<Imposto> impostosSelecionados;
    private Imposto imposto;
    private CentroCusto centroSelecionado;
    private Credor credor;
    private Date dataAR;
    private Integer numeroAR;
    private List<PedidoItem> pedidoItens;
    private List<TituloAPagar> titulosAPagar;
    //Filtro de pesquisa dos AR's
    private Date arDataInicial;
    private Date arDataFinal;
    private Integer numeroARFiltro;
    private DocumentoEntradaTipo docTipo;
    private String docNumero;
    private ClasseInsumos classeInsumo;
    private GrupoInsumos grupoInsumo;
    private CaracterizacaoInsumos caracterizacaoInsumo;
    private Integer itemNumero;
    private String itemItem;
    private InsumoSub insumoSub;
    private Float quantidadeE;
    private Float precoUnitarioE;
    private Float totalItem;
    private Float descontoE;
    private Float totalItemE;
    private Float aliqIPIE;
    private Float valorIPIE;
    private Float acrescimoE;
    private Float totalItemComIPIE;
    private Float quantidadeR;
    private Float precoUnitarioR;
    private Float totalItemR;
    private Float aliqIPIR;
    private Float valorIPIR;
    private Float totalItemComIPIR;
    private Float totalItemRp;
    private String assuntoOrigem;
    private String assuntoCod;
    private String grupoOrcCod;
    private String subGrupoOrcCod;
    private Float acrescimoSDoc;
    private Float descontoSDoc;
    
    private Funcionario funcionario;
    //----------------------------------------------
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    /**
     * Executado após o bean JSF ser criado.
     */
    @PostConstruct
    public void init() {
        initCentroSelecionado();
        initAvisoRecebimento();
        initCurrentList();
        incluirCurrent(this.currentList);
        createListImpostos(0);
    }
    

       /**
     * Recebe o centro de custo selecionado no loginController e o inicia no
     * DocumentoEntradaController.
     */ 
    public void initCentroSelecionado() {
        if (funcionario == null || centroSelecionado == null) {
            if (this.loginController != null) {
                funcionario = this.loginController.getFuncionario();
            }
            centroSelecionado = this.loginController.getCentroSelecionado();
        }
    }

    /**
     * Inicia o aviso de recebimento atual se ele for nulo.
     */
    private void initAvisoRecebimento() {
        if (this.avisoRecebimento == null) {
            avisoRecebimento = new AvisoRecebimento(0L, 0, new Date(), loginController.getCentroSelecionado(), credor, EnumsGeral.N, "", "NR", EnumTipoEntrada.SM.toString());
        }
    }

    /**
     * Inicia o DocumentoEntrada current com os valores default a serem
     * utilizados no formulário de cadastro.
     */
    private void initCurrentList() {
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
    }

    /**
     * Inclui um documento de entrada novo na lista de documentos de entrada.
     *
     * @param currentList Lista de documentos de entrada.
     */
    private void incluirCurrent(List<DocumentoEntrada> currentList) {
        current = getNewDocumentoEntrada(this.avisoRecebimento);
        current.setNumero(0L);
        current.setDataRegistro(new Date());
        current.setDataEntrada(new Date());
        current.setDataEmissao(new Date());
        current.setDocUFOrigem("CE");
        current.setItem(String.valueOf(incrementCurrent(currentList)));
        currentList.add(current);
    }
    
    public void novoCurrent(){
        initCurrentList();
        incluirCurrent(currentList);
    }

    /**
     * Cria um novo documento de entrada com referência ao aviso de recebimento.
     *
     * @param avisoRecebimento Aviso de recebimento que contém os documentos de
     * entrada.
     * @return Novo documento de entrada no aviso de recebimento.
     */
    private DocumentoEntrada getNewDocumentoEntrada(AvisoRecebimento avisoRecebimento) {
        return new DocumentoEntrada(0L, avisoRecebimento, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F);
    }

    /**
     * Incrementa o número de item que idenficica o documento de entrada na
     * lista atual.
     *
     * @param currentList Lista com documentos de entrada.
     * @return Número do próximo item.
     */
    private int incrementCurrent(List<DocumentoEntrada> currentList) {
        if (currentList != null && !currentList.isEmpty()) {
            int itemMaior = 1;
            for (DocumentoEntrada doc : currentList) {
                while (doc.getItemNum() >= itemMaior) {
                    itemMaior++;
                }
            }
            return itemMaior;
        } else {
            return 1;
        }
    }

    /**
     * Decrementa o número identificador do documento de entrada na lista de
     * itens.
     *
     * @param currentList Lista atual de itens.
     */
    private void decrementCurrent(List<DocumentoEntrada> currentList) {
        if (currentList != null && !currentList.isEmpty()) {
            int itemAtual = 1;
            for (DocumentoEntrada doc : currentList) {
                if (doc.getItemNum() >= itemAtual) {
                    int index = getCurrentIndex(currentList, doc);

                    if (doc.getItemNum() == 0) {
                        doc.setItem(String.valueOf(itemAtual));
                    }
                    currentList.set(index, doc);
                    itemAtual++;
                }
            }
        }
    }

    /**
     * Pega a posição atual do documento de entrada na lista de documentos
     * passada.
     *
     * @param currentList Lista de documentos a ser analisada.
     * @param current Item a ser procurado na lista de documentos.
     * @return Posição do documento na lista de documentos.
     */
    private int getCurrentIndex(List<DocumentoEntrada> currentList, DocumentoEntrada current) {
        return 0;
    }

    /**
     * Executado antes do bean JSF ser destruído.
     */
    @PreDestroy
    public void end() {
    }

    /**
     * Inicia o Credor a ser gerada a AR.
     *
     * @param c Credor
     */
    public void selecionaCredor(Credor c) {
        this.credor = c;
    }

    /**
     * Preenche a lista de listas de impostos a ser utilizada no cadastro da
     * nota fiscal.
     */
    public void createListImpostos(int index) {
        if (impostosDisponiveis == null) {
            impostosDisponiveis = new ArrayList<>();
            impostosDisponiveis.add(new ArrayList<EnumImposto>());
        }
        impostosDisponiveis.set(index, addNewListImpostos());
    }

    /**
     * Cria uma nova lista com os tipos de impostos.
     *
     * @return List
     */
    public List<EnumImposto> addNewListImpostos() {
        List<EnumImposto> tipos = new ArrayList<>();
        tipos.add(EnumImposto.INSS);
        tipos.add(EnumImposto.ISS);
        tipos.add(EnumImposto.IRRF);
        tipos.add(EnumImposto.PIS);
        tipos.add(EnumImposto.COFINS);
        tipos.add(EnumImposto.CSLL);
        tipos.add(EnumImposto.SEST_SENAT);
        if (impostosSelecionados != null) {
            for (int i = 0; i < impostosSelecionados.size(); i++) {
                tipos.remove(impostosSelecionados.get(i).getCodigo());
            }
        }
        return tipos;
    }

    /**
     * Pesquisa os AR's de acordo com os filtros informados.
     *
     * @param CentroCusto determina centro de custo dos AR's
     */
    public void pesquisar(CentroCusto c, ClasseInsumos classe, GrupoInsumos grupo, CaracterizacaoInsumos caracterizacao) {
        this.centroSelecionado = c;
        this.classeInsumo = classe;
        this.grupoInsumo = grupo;
        this.caracterizacaoInsumo = caracterizacao;
        recreateTable();
    }

    /**
     * Preenche os campos do PedidoItem para ser usado no construtor de
     * PedidoItem.
     *
     * @param item
     */
    public void preencheNovoItem(PedidoItem item) {
        itemNumero = 1;
        itemItem = "001";
        insumoSub = item.getSolicitacaoCompraItem().getInsumoSub();
        //ENTREGUE
        if (item.getDocumentoEntradaItem() == null || item.getDocumentoEntradaItem().getQuantidadeRecebida() == null) {
            quantidadeE = item.getQuantidade();
        } else {
            quantidadeE = item.getQuantidade() - item.getDocumentoEntradaItem().getQuantidadeRecebida();
        }
        precoUnitarioE = item.getPrecoInsumo();
        totalItem = quantidadeE * precoUnitarioE;
        descontoE = 0F;
        totalItemE = totalItem;
        aliqIPIE = 0F;
        valorIPIE = 0F;
        acrescimoE = 0F;
        totalItemComIPIE = totalItem;
        //RECEBIDO
        quantidadeR = quantidadeE;
        precoUnitarioR = item.getPrecoInsumo();
        totalItemR = quantidadeR * precoUnitarioR;
        aliqIPIR = 0F;
        valorIPIR = 0F;
        totalItemComIPIR = totalItemR;
        totalItemRp = 0F;
        assuntoOrigem = "SUP";
        assuntoCod = insumoSub.getInsumo().getClasseCod();
        grupoOrcCod = insumoSub.getInsumo().getClasseCod() + insumoSub.getInsumo().getGrupoCod();
        subGrupoOrcCod = "000" + insumoSub.getInsumo().getCaracterizacaoCod();
        acrescimoSDoc = 0F;
        descontoSDoc = 0F;
    }

    /**
     * Limpa as variáveis do item setando para nulo.
     */
    public void limpaNovoItem() {
        itemNumero = null;
        itemItem = null;
        insumoSub = null;
        //ENTREGUE
        quantidadeE = null;
        precoUnitarioE = null;
        totalItem = null;
        descontoE = null;
        totalItemE = null;
        aliqIPIE = null;
        valorIPIE = null;
        acrescimoE = null;
        totalItemComIPIE = null;
        //RECEBIDO
        quantidadeR = null;
        precoUnitarioR = null;
        totalItemR = null;
        aliqIPIR = null;
        valorIPIR = null;
        totalItemComIPIR = null;
        totalItemRp = null;
        assuntoOrigem = null;
        assuntoCod = null;
        grupoOrcCod = null;
        subGrupoOrcCod = null;
    }

    public void incluirItens(List<PedidoItem> selecionados) {
        if (currentItens == null) {
            currentItens = new ArrayList<>();
        }
        if (selecionados != null && !selecionados.isEmpty()) {
            for (PedidoItem item : selecionados) {
                item = pedidoItemFacade.findById(item.getPedido(), item.getItemNumero());
                preencheNovoItem(item);
                currentItens.get(0).setPedidoItem(item);
                limpaNovoItem();
            }
        }
    }

    /**
     * Atribui o imposto atual ao selecionar o SelectItem no método onfocus da
     * página.
     *
     * @param imp
     */
    public void alteraImpostoAnterior(Imposto imp) {
        this.imposto = imp;
    }

    /**
     * Trata o imposto trocado na página, deixando disponível o imposto
     * anterior, e indisponível o imposto atual selecionado.
     *
     * @param imp
     */
    public void alteraImposto(Imposto imp, int index) {
        removeTipo(imp.getCodigo(), index);
    }

    /**
     * Adiciona novo imposto à lista de impostos selecionados.
     */
    public void novoImposto() {
        if (impostosSelecionados == null) {
            impostosSelecionados = new ArrayList<>();
        }
        impostosDisponiveis.add(new ArrayList<EnumImposto>());
        impostosSelecionados.add(new Imposto(impostosSelecionados.size(), impostosDisponiveis.get(impostosSelecionados.size()).get(0), impostosDisponiveis.get(impostosSelecionados.size()).get(0).getLabel(), 0F, 0F, 0F, 0F, 0F, 0F, EnumRecolhimentoSelect.APRESENTADO));
        createListImpostos(impostosSelecionados.size());
    }

    /**
     * Remove imposto da lista de impostos selecionados. Com isso, o tipo de
     * imposto deve voltar a ficar disponível para re-utilização.
     *
     * @param imp Imposto a ser removido da lista.
     */
    public void removeImposto(Imposto imp, int index) {
        impostosSelecionados.remove(imp);
        impostosDisponiveis.remove(index);
        //addTipo(imp.getNome(), index);
    }

    /**
     * Remove um tipo de imposto informado da lista de impostos disponíveis.
     * Isso ocorre no momento que um tipo de imposto é utilizado, deixando-o
     * indisponível para ser utilizado novamente.
     *
     * @param tipo de imposto a ser removido.
     */
    private void removeTipo(EnumImposto tipo, int index) {
        /*   for (Imposto im : impostosSelecionados) {
         for (List<EnumImposto> lista : impostosDisponiveis) {
         if (im.getIndex() != impostosDisponiveis.indexOf(lista)) {
         impostosDisponiveis.get(impostosDisponiveis.indexOf(lista)).remove(im.getNome());
         }
         }
         }*/
    }

    /**
     * Adiciona um tipo de imposto na lista de impostos disponíveis. Isso ocorre
     * quando um imposto na lista de impostos selecionados é removido. Esse tipo
     * volta a ser utilizável.
     *
     * @param tipo de imposto a ser utilizável novamente.
     */
    private void addTipo(EnumImposto tipo, int index) {
        impostosDisponiveis.get(index).add(tipo);
    }

    /**
     * Pesquisa todos os tipos de documento de entrada cadastrados.
     *
     * @return SelectItem[]
     */
    public SelectItem[] getDocEntTipoSelect() {
        return JsfUtil.getSelectItems(documentoEntradaTipoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getUFSelect() {
        return JsfUtil.getSelectItems(estadoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getNaturezaFiscalSelect() {
        return JsfUtil.getSelectItems(naturezaFiscalFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getTipoImpostoSelect(int index) {
        return JsfUtil.getSelectItemsComparable(impostosDisponiveis.get(index), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getEspecieTituloAPagarSelect() {
        return JsfUtil.getSelectItems(especieTituloAPagarFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getRecolhimentoImpostoSelect() {
        SelectItem[] arrayRecolhimento = new SelectItem[2];
        arrayRecolhimento[0] = new SelectItem(EnumRecolhimentoImposto.R, "Retido");
        arrayRecolhimento[1] = new SelectItem(EnumRecolhimentoImposto.A, "Apresentado");
        return arrayRecolhimento;
        //return JsfUtil.getEnumSelectItems(EnumRecolhimentoSelect.class, false, FacesContext.getCurrentInstance());
    }

    public DocumentoEntradaFacade getFacade() {
        return documentoEntradaFacade;
    }

    @Override
    protected void setEntity(DocumentoEntrada t) {
        this.current = t;
    }

    @Override
    protected DocumentoEntrada getNewEntity() {
        return new DocumentoEntrada();
    }

    @Override
    protected void performDestroy() {
    }

    @Override
    protected String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().countParam(arDataInicial, arDataFinal, numeroAR, docTipo, docNumero, credor, classeInsumo, grupoInsumo, caracterizacaoInsumo).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRangeParam(arDataInicial, arDataFinal, numeroAR, docTipo, docNumero, credor, classeInsumo, grupoInsumo, caracterizacaoInsumo, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    /**
     * Atribui ao Documento de Entrada current os valores de impostos de acordo
     * com a lista impostosSelecionados.
     */
    private void mountCurrentImpostos() {
        if (impostosSelecionados != null && !impostosSelecionados.isEmpty()) {
            for (Imposto imp : impostosSelecionados) {
                if (imp.getCodigo() == EnumImposto.COFINS) {
                    current.setBasePercCOFINS(imp.getPercentBase());
                    current.setBaseCOFINS(imp.getBaseCalculo());
                    current.setAliqCOFINS(imp.getPercentAliquota());
                    current.setCalcCOFINS(imp.getCalculo());
                    current.setMinCOFINS(imp.getMinimo());
                    current.setValorCOFINS(imp.getValor());
                    current.setRecolhimentoCOFINS(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.CSLL) {
                    current.setBasePercCSSL(imp.getPercentBase());
                    current.setBaseCSSL(imp.getBaseCalculo());
                    current.setAliqCSSL(imp.getPercentAliquota());
                    current.setCalcCSSL(imp.getCalculo());
                    current.setMinCSSL(imp.getMinimo());
                    current.setValorCSSL(imp.getValor());
                    current.setRecolhimentoCSSL(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.INSS) {
                    current.setBasePercINSS(imp.getPercentBase());
                    current.setBaseINSS(imp.getBaseCalculo());
                    current.setAliqINSS(imp.getPercentAliquota());
                    current.setCalcINSS(imp.getCalculo());
                    current.setMinINSS(imp.getMinimo());
                    current.setValorINSS(imp.getValor());
                    current.setRecolhimentoINSS(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.IRRF) {
                    current.setBasePercIRRF(imp.getPercentBase());
                    current.setBaseIRRF(imp.getBaseCalculo());
                    current.setAliqIRRF(imp.getPercentAliquota());
                    current.setCalcIRRF(imp.getCalculo());
                    current.setMinIRRF(imp.getMinimo());
                    current.setValorIRRF(imp.getValor());
                    current.setRecolhimentoIRRF(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.ISS) {
                    current.setBasePercISS(imp.getPercentBase());
                    current.setBaseISS(imp.getBaseCalculo());
                    current.setAliqISS(imp.getPercentAliquota());
                    current.setValorISS(imp.getValor());
                    current.setRecolhimentoISS(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.PIS) {
                    current.setBasePercPIS(imp.getPercentBase());
                    current.setBasePIS(imp.getBaseCalculo());
                    current.setAliqPIS(imp.getPercentAliquota());
                    current.setCalcPIS(imp.getCalculo());
                    current.setMinPIS(imp.getMinimo());
                    current.setValorPIS(imp.getValor());
                    current.setRecolhimentoPIS(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
                if (imp.getCodigo() == EnumImposto.SEST_SENAT) {
                    current.setBasePercSS(imp.getPercentBase());
                    current.setBaseSS(imp.getBaseCalculo());
                    current.setAliqSS(imp.getPercentAliquota());
                    current.setValorSS(imp.getValor());
                    current.setRecolhimentoSS(EnumRecolhimentoImposto.getForLabel(imp.getRecolhimento().getLabel()));
                }
            }
        }
    }

    /**
     * Preenche os Títulos a Pagar do current gerando a chave primária a partir
     * da tabela de Sequenciais.
     */
    private void mountCurrentTitulosAPagar() {
        titulosAPagar = new ArrayList<>();
        titulosAPagar.add(new TituloAPagar(0L, credor.getCodigo(), especieTituloAPagarFacade.find("BL"), current.getDocValor(), current.getDespesasAcessorias(), current.getDescontoProduto(), 0F, current, new Date(), "001", "", "c", new Date(), current.getDocNumero(), EnumSituacaoPagamento.A, new Date(), new Date(), new Date(), new Date(), current.getDocValor(), 0, 0F, "NF-e", current.getCentro().getEmpresaCod(), 0F, 0F, 0F, 0F));

        Long sequencial = sequenciaisFacade.getSequencialTituloAPagar().getNumero().longValue();
        while (titulosAPagar.get(0).getNumero() <= sequencial) {
            titulosAPagar.get(0).setNumero(sequencial + 1L);
            sequencial = sequenciaisFacade.getSequencialTituloAPagar().getNumero().longValue();
        }

        titulosAPagar.get(0).setTituloMae(titulosAPagar.get(0));
        //   current.setTitulosAPagar(titulosAPagar);
    }

    /**
     * Preenche o Aviso de Recebimento do current gerando a chave primariá a
     * partir da tabela de Sequenciais.
     */
    private void mountCurrentAR() {
        current.getAvisoRecebimento().rebuildFields(0L, loginController.getCentroSelecionado(), current.getCredor(), EnumsGeral.N, current.getObservacao(), "NR", "SM");
        current.getAvisoRecebimento().setIdSistema(avisoRecebimentoFacade.findMaxCentroNumero(loginController.getCentroSelecionado()));
        Long sequencial = sequenciaisFacade.getSequencialAvisoRecebimento().getNumero().longValue();
        while (current.getAvisoRecebimento().getNumero() <= sequencial) {
            current.getAvisoRecebimento().setNumero(sequencial + 1L);
            sequencial = sequenciaisFacade.getSequencialAvisoRecebimento().getNumero().longValue();
        }
        current.setDataRegistro(current.getAvisoRecebimento().getData());
    }

    private void mountCurrentItens() {
        DocumentoEntradaBusiness.rateiaItensIPI(currentItens, current.getValorIPI());
        current.setItens(currentItens);
    }

    /**
     * Persiste a lista de TituloAPagar no banco de dados.
     *
     * Obs.: A inserção de TituloAPagar em cascata com o DocumentoEntrada não
     * está funcionando.
     */
    private void persisteTitulosAPagar() {
        if (titulosAPagar != null && !titulosAPagar.isEmpty()) {
            for (TituloAPagar tit : titulosAPagar) {
                try {
                    Long sequencial = sequenciaisFacade.getSequencialTituloAPagar().getNumero().longValue();
                    while (tit.getNumero() <= sequencial) {
                        tit.setNumero(sequencial + 1L);
                        sequencial = sequenciaisFacade.getSequencialTituloAPagar().getNumero().longValue();
                    }
                    sequenciaisFacade.update(sequenciaisFacade.getSequencialTituloAPagar().initNumber(tit.getNumero()));

                    tit.setDocumentoEntradaNumero(current.getNumero());
                    tit.setEspecieTituloCodigo(tit.getEspecieTitulo().getCodigo());
                    tituloAPagarFacade.create(tit);
                } catch (EntityException ex) {
                    Logger.getLogger(DocumentoEntradaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void mountCurrentDocEnt() {
        current.setDocNumero(current.getNumeroDocumento().toString());
    }

    /**
     * Persiste o DocumentoEntrada current no banco de dados.
     */
    public void createDocumentoEntrada(CentroCusto centro) {
        /*      if (documentoEntradaFacade.numeroDocumentoExists(current.getNumeroDocumento())) {
         msgAvisoDocNumeroDuplicado();
         } else {
         this.centroSelecionado = centro;
         current.setCentro(centroSelecionado);
         current.setCredor(credor);
         mountCurrentItens();
         mountCurrentImpostos();
         mountCurrentAR();
         mountCurrentDocEnt();
         if (current.getAvisoRecebimento() != null) {
         Long sequencialAR = current.getAvisoRecebimento().getNumero();
         sequenciaisFacade.update(sequenciaisFacade.getSequencialAvisoRecebimento().initNumber(sequencialAR));
         }

         Long sequencialDocEnt = sequenciaisFacade.getSequencialDocumentoEntrada().getNumero().longValue();
         while (current.getNumero().longValue() <= sequencialDocEnt) {
         current.setNumero(sequencialDocEnt.longValue() + 1L);
         sequencialDocEnt = sequenciaisFacade.getSequencialDocumentoEntrada().getNumero().longValue();
         }
         sequenciaisFacade.update(sequenciaisFacade.getSequencialDocumentoEntrada().initNumber(current.getNumero()));
         documentoEntradaFacade.createDocumentoEntrada(current);

         mountCurrentTitulosAPagar();
         persisteTitulosAPagar();

         msgAvisoRecebimentoCreated();
         }*/
    }

    public void msgAvisoRecebimentoCreated() {
        MessageUtils.messageFactoringFull("AvisoRecebimentoCreated",
                new Object[]{current.getAvisoRecebimento().getIdSistema()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    public void msgAvisoDocNumeroDuplicado() {
        MessageUtils.messageFactoringFull("docNumeroDuplicado",
                new Object[]{current.getNumeroDocumento()},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    public DocumentoEntrada getCurrent() {
        return current;
    }

    public void setCurrent(DocumentoEntrada current) {
        this.current = current;
    }

    public List<DocumentoEntradaItem> getCurrentItens() {
        return currentItens;
    }

    public void setCurrentItens(List<DocumentoEntradaItem> currentItens) {
        this.currentItens = currentItens;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public List<TituloAPagar> getTitulosAPagar() {
        return titulosAPagar;
    }

    public void setTitulosAPagar(List<TituloAPagar> titulosAPagar) {
        this.titulosAPagar = titulosAPagar;
    }

    public Date getArDataInicial() {
        return arDataInicial;
    }

    public void setArDataInicial(Date arDataInicial) {
        this.arDataInicial = arDataInicial;
    }

    public Date getArDataFinal() {
        return arDataFinal;
    }

    public void setArDataFinal(Date arDataFinal) {
        this.arDataFinal = arDataFinal;
    }

    public Integer getNumeroAR() {
        return numeroAR;
    }

    public void setNumeroAR(Integer numeroAR) {
        this.numeroAR = numeroAR;
    }

    public DocumentoEntradaTipo getDocTipo() {
        if (docTipo == null) {
            docTipo = new DocumentoEntradaTipo().initClass("", "");
        }
        return docTipo;
    }

    public void setDocTipo(DocumentoEntradaTipo docTipo) {
        this.docTipo = docTipo;
    }

    public String getDocNumero() {
        return docNumero;
    }

    public void setDocNumero(String docNumero) {
        this.docNumero = docNumero;
    }

    public ClasseInsumos getClasseInsumo() {
        return classeInsumo;
    }

    public void setClasseInsumo(ClasseInsumos classeInsumo) {
        this.classeInsumo = classeInsumo;
    }

    public GrupoInsumos getGrupoInsumo() {
        return grupoInsumo;
    }

    public void setGrupoInsumo(GrupoInsumos grupoInsumo) {
        this.grupoInsumo = grupoInsumo;
    }

    public CaracterizacaoInsumos getCaracterizacaoInsumo() {
        return caracterizacaoInsumo;
    }

    public void setCaracterizacaoInsumo(CaracterizacaoInsumos caracterizacaoInsumo) {
        this.caracterizacaoInsumo = caracterizacaoInsumo;
    }

    public List<Imposto> getImpostosSelecionados() {
        return impostosSelecionados;
    }

    public void setImpostosSelecionados(List<Imposto> impostosSelecionados) {
        this.impostosSelecionados = impostosSelecionados;
    }

    public List<DocumentoEntrada> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<DocumentoEntrada> currentList) {
        this.currentList = currentList;
    }

    public AvisoRecebimento getAvisoRecebimento() {
        return avisoRecebimento;
    }

    public void setAvisoRecebimento(AvisoRecebimento avisoRecebimento) {
        this.avisoRecebimento = avisoRecebimento;
    }
}
