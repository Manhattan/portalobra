/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso.controller;

import br.com.grupopibb.portalobra.acesso.Monitor;
import br.com.grupopibb.portalobra.acesso.MonitorScheduler;
import br.com.grupopibb.portalobra.acesso.MonitorTask;
import br.com.grupopibb.portalobra.acesso.PerfilAcesso;
import br.com.grupopibb.portalobra.acesso.ValidaLogin;
import br.com.grupopibb.portalobra.acesso.business.MonitorBusiness;
import br.com.grupopibb.portalobra.acesso.dao.MonitorFacade;
import br.com.grupopibb.portalobra.acesso.status.StatusLogin;
import br.com.grupopibb.portalobra.dao.acesso.FuncionarioCentroFacade;
import br.com.grupopibb.portalobra.dao.funcionario.FuncionarioFacade;
import br.com.grupopibb.portalobra.dao.projeto.ProjetoPlanejamentoFacade;
import br.com.grupopibb.portalobra.dao.solicitacaocompra.SolicitanteFacade;
import br.com.grupopibb.portalobra.exceptions.EntityException;
import br.com.grupopibb.portalobra.model.funcionario.Funcionario;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.solicitacaocompra.Solicitante;
import br.com.grupopibb.portalobra.utils.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author administrator
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    FuncionarioFacade funcionarioFacade;
    @EJB
    SolicitanteFacade solicitanteFacade;
    @EJB
    FuncionarioCentroFacade funcionarioCentroFacade;
    @EJB
    ValidaLogin validaLogin;
    @EJB
    private MonitorFacade monitorFacade;
    @EJB
    private MonitorBusiness monitorBusiness;
    @EJB
    private MonitorScheduler monitorScheduler;
    @EJB
    private ProjetoPlanejamentoFacade projPlanFacade;
    private String login = "";
    private String senha = "";
    private String erroLogin = "Erro ao efetuar Login.";
    private Integer statusLogin = 0;
    private CentroCusto centroSelecionado;
    private Funcionario funcionario;
    private Solicitante solicitante;
    private PerfilAcesso perfil;
    private Monitor monitor;
    private MonitorTask monitorTask;
    private final String LOGIN_SENHA_INVALIDOS = "Login e/ou senha inválidos.";
    private final String USUARIO_DESATIVADO = "Usuário desativado.";
    private final String USUARIO_SEM_CENTRO_VINCULADO = "Usuário sem centro(s) de custo vinculado(s).";
    private final String ACESSO_NEGADO = "Acesso negado.";

    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void end() {
        cleanAll();
    }

    /**
     * Fecha a sessão atual, encaminhando para a página de Login.
     *
     * @return Página de login.
     */
    public String logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        session.invalidate();
        return JsfUtil.LOGIN_PAGE;
    }

    private boolean isLoggedUser() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            if (((LoginController) sessionMap.get("loginController")).getFuncionario() != null) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void closeLoggedUser() {
        updateUserExitDate();
        cancelMonitorTask();
        funcionario = null;
    }

    private void cancelMonitorTask() {
        if (monitorTask != null) {
            monitorTask.cancel();
            monitorTask = null;
        }
    }

    private void updateUserExitDate() {
        if (monitor != null) {
            monitor.setDataSaida(new Date());
            try {
                monitorFacade.update(this.monitor);
            } catch (EntityException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Desconecta o funcionário atual e retorna para a página de login.
     *
     * @return Página de login.
     */
    public String cleanAll() {
        login = null;
        senha = null;
        statusLogin = null;
        centroSelecionado = null;
        perfil = null;
        closeLoggedUser();
        return JsfUtil.LOGIN_PAGE;
    }

    public void performLogin() {
        getPerformLogin();
    }

    public SelectItem[] getCentrosFuncionarioSelect() {
        try {
            return JsfUtil.getSelectItems(funcionario.getCentros(), false, FacesContext.getCurrentInstance());
        } catch (NullPointerException e) {
            return new SelectItem[0];
        }
    }

    public Funcionario getPerformLogin() {
        if (isLoggedUser()) {
            closeLoggedUser();
        }
        int result = validaLogin.performLogin(login, senha);
        if (result == StatusLogin.ATIVO) {
            funcionario = funcionarioFacade.findByLogin(login);
            if (!funcionario.isAtivo()) {
                statusLogin = StatusLogin.INATIVO;
                funcionario = null;
                erroLogin = USUARIO_DESATIVADO;
                return null;
            }

            statusLogin = StatusLogin.ATIVO;
            solicitante = solicitanteFacade.findByName(login);

            if (funcionario == null || solicitante == null) {
                statusLogin = StatusLogin.INATIVO;
                erroLogin = LOGIN_SENHA_INVALIDOS;
                return null;
            }

            if (centroSelecionado == null || centroSelecionado.getCodigo() == null) {
                if (funcionario.getCentros() == null || funcionario.getCentros().isEmpty()) {
                    statusLogin = StatusLogin.INATIVO;
                    erroLogin = USUARIO_SEM_CENTRO_VINCULADO;
                    return null;
                }
                centroSelecionado = funcionario.getCentros().get(0);
                pesqProjOrcPlanCentro();
            }

            funcionario.setSenha(senha);
            this.perfil = funcionarioCentroFacade.find(funcionario, centroSelecionado).getPerfil();
            //Cria o registro de acesso para monitoramento e controle de acessos ao sistema.
            this.monitor = monitorBusiness.getNewMonitor(funcionario);
            try {
                monitorFacade.create(monitor);
            } catch (EntityException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cancelMonitorTask();
            monitorTask = new MonitorTask(this.monitor, monitorFacade, funcionario);
            monitorScheduler.init(this.monitorTask);
            monitorFacade.atualizaMonitor();
            return funcionario;
        } else if (result == StatusLogin.INATIVO) {
            statusLogin = StatusLogin.INATIVO;
            erroLogin = LOGIN_SENHA_INVALIDOS;
            return null;
        }
        statusLogin = StatusLogin.INATIVO;
        erroLogin = ACESSO_NEGADO;
        return null;
    }

    public String mudaCentro(CentroCusto c, String destino) {
        this.centroSelecionado = c;
        this.perfil = funcionarioCentroFacade.find(funcionario, centroSelecionado).getPerfil();
        pesqProjOrcPlanCentro();
        return destino;
    }

    /**
     * Inicia o insumoController com o centro de custo atual do loginController.
     *
     * @param centro Centro de custo atual.
     */
    public void pesqProjOrcPlanCentro() {
        try {
            this.centroSelecionado.setObraLinkadaOrcamento(projPlanFacade.isCentroLinkOrcamento(this.centroSelecionado));
            this.centroSelecionado.setProjCod(projPlanFacade.findProjetoCod(this.centroSelecionado).get("ProjCod"));
            this.centroSelecionado.setOrcCod(projPlanFacade.findProjetoCod(this.centroSelecionado).get("OrcCod"));
            this.centroSelecionado.setPlanCod(projPlanFacade.findProjetoCod(this.centroSelecionado).get("PlanCod"));
        } catch (NullPointerException e) {
        }
    }

    /**
     * Define se o usuário tem permissão para incluir nova soliciação de compra.
     *
     * @return
     */
    public boolean isIncluiSolicitacao() {
        return (perfil != null && perfil.getIncluiSolicitacao());
    }

    /**
     * Define se o usuário tem permissão para alterar uma soliciação de compra.
     *
     * @return
     */
    public boolean isAlteraSolicitacao() {
        return (perfil != null && perfil.getAlteraSolicitacao());
    }

    /**
     * Define se o usuário tem permissão para concluir/desconcluir soliciação de
     * compra.
     *
     * @return
     */
    public boolean isConcluiSolicitacao() {
        return (perfil != null && perfil.getConcluiSolicitacao());
    }

    public boolean isRemoveSolicitacao() {
        return (perfil != null && perfil.getRemoveSolicitacao());
    }

    /**
     * Verifica se o usuário tem permissão para inclusão e conclusão da
     * solicitação de compra.
     *
     * @return Verdadeiro se tiver acesso a incluir e concluir.
     */
    public boolean isIncluiConcluiSolicitacao() {
        return (isConcluiSolicitacao() && isIncluiSolicitacao());
    }

    public boolean isIncluiEntradaMaterial() {
        return (perfil != null && perfil.getIncluiEntradaMaterial());
    }

    public boolean isAlteraEntradaMaterial() {
        return (perfil != null && perfil.getAlteraEntradaMaterial());
    }

    public boolean isRemoveEntradaMaterial() {
        return (perfil != null && perfil.getRemoveEntradaMaterial());
    }

    public boolean isIncluiSaidaMaterial() {
        return (perfil != null && perfil.getIncluiSaidaMaterial());
    }

    public boolean isAlteraSaidaMaterial() {
        return (perfil != null && perfil.getAlteraSaidaMaterial());
    }

    public boolean isRemoveSaidaMaterial() {
        return (perfil != null && perfil.getRemoveSaidaMaterial());
    }

    public boolean isAutorizaSolicitacao() {
        return (perfil != null && perfil.getAutorizaSolicitacao());
    }

    public boolean isVerUsuariosConectados() {
        return (perfil != null && perfil.getVerUsuariosConectados());
    }

    public boolean isVerUsuario() {
        return (perfil != null && perfil.getVerUsuario());
    }

    public boolean isIncluiUsuario() {
        return (perfil != null && perfil.getIncluiUsuario());
    }

    public boolean isAlteraUsuario() {
        return (perfil != null && perfil.getAlteraUsuario());
    }

    public boolean isRemoveUsuario() {
        return (perfil != null && perfil.getRemoveUsuario());
    }

    public boolean isVerPerfil() {
        return (perfil != null && perfil.getVerPerfilAcesso());
    }

    public boolean isIncluiPerfil() {
        return (perfil != null && perfil.getIncluiPerfilAcesso());
    }

    public boolean isAlteraPerfil() {
        return (perfil != null && perfil.getAlteraPerfilAcesso());
    }

    public boolean isRemovePerfil() {
        return (perfil != null && perfil.getRemovePerfilAcesso());
    }

    public boolean isRenderMenuSeguranca() {
        return isIncluiPerfil() || isAlteraPerfil() || isRemovePerfil()
                || isIncluiUsuario() || isAlteraUsuario() || isRemoveUsuario()
                || isVerPerfil() || isVerUsuario() || isVerUsuariosConectados();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public CentroCusto getCentroSelecionado() {
        return centroSelecionado;
    }

    public void setCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

    public String getErroLogin() {
        return erroLogin;
    }
}
