/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.funcionario;

import br.com.grupopibb.portalobra.acesso.FuncionarioCentro;
import br.com.grupopibb.portalobra.acesso.PerfilAcesso;
import br.com.grupopibb.portalobra.controller.common.EntityController;
import br.com.grupopibb.portalobra.controller.common.EntityPagination;
import br.com.grupopibb.portalobra.dao.acesso.PerfilAcessoFacade;
import br.com.grupopibb.portalobra.dao.funcionario.FuncionarioFacade;
import br.com.grupopibb.portalobra.dao.geral.CentroCustoFacade;
import br.com.grupopibb.portalobra.dao.geral.EmpresaFacade;
import br.com.grupopibb.portalobra.dao.solicitacaocompra.SolicitanteFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Empresa;
import br.com.grupopibb.portalobra.model.solicitacaocompra.Solicitante;
import br.com.grupopibb.portalobra.model.solicitacaocompra.UsuarioRestricaoSolicitante;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import br.com.grupopibb.portalobra.utils.MessageUtils;
import br.com.grupopibb.portalobra.utils.NumberUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.NoResultException;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class FuncionarioController extends EntityController<Funcionario> implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB
    private PerfilAcessoFacade perfilAcessoFacade;
    @EJB
    private EmpresaFacade empresaFacade;
    @EJB
    private CentroCustoFacade centroCustoFacade;
    @EJB
    private SolicitanteFacade solicitanteFacade;
    private Funcionario current;
    private FuncionarioCentro funcionarioCentro;
    private PerfilAcesso perfilAcesso;
    //Filtros
    private String login;
    private String nome;
    private Empresa empresa;
    private CentroCusto centro;
    private PerfilAcesso perfil;
    private boolean ativo = true;

    @Override
    protected void setEntity(Funcionario t) {
        this.current = t;
    }

    @Override
    protected Funcionario getNewEntity() {
        return new Funcionario(new ArrayList<FuncionarioCentro>());
    }

    @Override
    protected void performDestroy() {
        try {
            getFacade().remove(current);
            deleteSolicitante(current);
            msgFuncionarioDeleted();
        } catch (EntityException | EJBException ex) {
            msgOperacaoNaoPermitida();
        }
        clean();
    }

    public void removeCentro(FuncionarioCentro funcCentro) {
        try {
            this.current.getFuncionarioCentros().remove(funcCentro);
        } catch (NullPointerException e) {
            msgErroDesconhecido();
        }
    }

    public void incluirCentro() {
        this.current.getFuncionarioCentros().add(new FuncionarioCentro(current));
    }

    /**
     * Define se a edição do campo login será habilitada ou não. Os usuários já
     * existentes não podem ter seu login alterado.
     *
     * @return True ou false.
     */
    public boolean isEnabledLogin() {
        return newEntity;
    }

    /**
     * Verifica se há um solicitante cadastrado com o mesmo login do funcionário
     * passado por parâmetro.
     *
     * @param funcionario Funcionario a ser comparado.
     * @return True ou false.
     */
    public boolean isSolicitanteExists(Funcionario funcionario) {
        try {
            return (solicitanteFacade.findByName(funcionario.getLogin()) != null);
        } catch (NullPointerException | NoResultException e) {
            return false;
        }
    }

    public void createSolicitante(Funcionario funcionario) {
        if (!isSolicitanteExists(funcionario)) {
            try {
                solicitanteFacade.create(getSolicitanteCompleto(funcionario));
            } catch (EntityException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteSolicitante(Funcionario funcionario) {
        if (isSolicitanteExists(funcionario)) {
            try {
                solicitanteFacade.remove(solicitanteFacade.findByName(funcionario.getLogin()));
            } catch (EntityException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Solicitante getNewSolicitante(Funcionario funcionario) {
        try {
            String id = NumberUtils.preencheZeroEsquerda(solicitanteFacade.findLastId(1).toString(), 3);
            EnumsGeral atv = funcionario.isAtivo() ? EnumsGeral.S : EnumsGeral.N;
            return new Solicitante(id, funcionario.getLogin(), atv);
        } catch (NullPointerException e) {
            return null;
        }
    }
    
    private Solicitante getSolicitanteCompleto(Funcionario funcionario){
        Solicitante solic = getNewSolicitante(funcionario);
        solic.setUsuariosLiberados(new ArrayList<UsuarioRestricaoSolicitante>());
        solic.getUsuariosLiberados().add(new UsuarioRestricaoSolicitante(solic, solic));
        return solic;
    }

    @Override 
    protected String create() {
        try {
            getFacade().create(current);
            createSolicitante(current);
            msgFuncionarioCreated();
        } catch (EntityException | EJBException ex) {
            msgErroDesconhecido();
        }
        recreateTable();
        clean();
        return JsfUtil.MANTEM;
    }

    @Override
    protected String update() {
        try {
            List<FuncionarioCentro> funcCentros = current.getFuncionarioCentros();
            current.setFuncionarioCentros(null);
            getFacade().update(current);
            current.setFuncionarioCentros(funcCentros);
            getFacade().update(current);
            msgFuncionarioUpdated();
            current = null;
        } catch (EntityException | EJBException ex) {
            msgErroDesconhecido();
        }
        recreateTable();
        clean();
        return JsfUtil.MANTEM;
    }

    /**
     * Retorna uma lista com todos os perfis de acesso cadastrados.
     *
     * @return SelectItem[] com todos os perfis de acesso.
     */
    public SelectItem[] getPerfilAcessoSelect() {
        return JsfUtil.getSelectItems(perfilAcessoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().countParam(login, nome, empresa, centro, ativo).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRangeParam(login, nome, empresa, centro, ativo, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    @Override
    public void pesquisar() {
        recreateTable();
    }

    public FuncionarioFacade getFacade() {
        return funcionarioFacade;
    }

    public SelectItem[] getEmpresaSelect() {
        return JsfUtil.getSelectItems(empresaFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getCentroSelect() {
        return JsfUtil.getSelectItems(centroCustoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getPerfilSelect() {
        return JsfUtil.getSelectItems(perfilAcessoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public Funcionario getCurrent() {
        return current;
    }

    public void setCurrent(Funcionario current) {
        this.current = current;
    }

    public FuncionarioCentro getFuncionarioCentro() {
        return funcionarioCentro;
    }

    public void setFuncionarioCentro(FuncionarioCentro funcionarioCentro) {
        this.funcionarioCentro = funcionarioCentro;
    }

    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private void msgFuncionarioCreated() {
        MessageUtils.messageFactoringFull("UsuarioCreated",
                new Object[]{current.getLogin()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioUpdated() {
        MessageUtils.messageFactoringFull("UsuarioUpdated",
                new Object[]{current.getLogin()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioDeleted() {
        MessageUtils.messageFactoringFull("UsuarioDeleted",
                new Object[]{current.getLogin()},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgErroDesconhecido() {
        MessageUtils.messageFactoringFull("erroDesconhcido",
                null,
                FacesMessage.SEVERITY_ERROR,
                FacesContext.getCurrentInstance());
    }

    private void msgOperacaoNaoPermitida() {
        MessageUtils.messageFactoringFull("operacaoNaoPermitida",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }
}
