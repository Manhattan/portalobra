/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso.business;

import br.com.grupopibb.portalobra.acesso.PerfilAcesso;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class PerfilAcessoBusiness {

    public void checkAllSolicitacao(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiSolicitacao(check);
            perfil.setAlteraSolicitacao(check);
            perfil.setRemoveSolicitacao(check);
            perfil.setConcluiSolicitacao(check);
            perfil.setAutorizaSolicitacao(check);
        }
    }

    public void checkAllEntradaMaterial(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiEntradaMaterial(check);
            perfil.setAlteraEntradaMaterial(check);
            perfil.setRemoveEntradaMaterial(check);
        }
    }

    public void checkAllSaidaMaterial(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiSaidaMaterial(check);
            perfil.setAlteraSaidaMaterial(check);
            perfil.setRemoveSaidaMaterial(check);
        }
    }

    public void checkAllAR(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiAR(check);
            perfil.setAlteraAR(check);
            perfil.setRemoveAR(check);
        }
    }

    public void checkAllFundoFixo(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiFundoFixo(check);
            perfil.setAlteraFundoFixo(check);
            perfil.setRemoveFundoFixo(check);
        }
    }

    public void checkAllRhCadastroGeral(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiRhCadastroGeral(check);
            perfil.setAlteraRhCadastroGeral(check);
            perfil.setRemoveRhCadastroGeral(check);
        }
    }

    public void checkAllRhOcorrencia(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiRhOcorrencia(check);
            perfil.setAlteraRhOcorrencia(check);
            perfil.setRemoveRhOcorrencia(check);
        }
    }

    public void checkAllUsuario(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiUsuario(check);
            perfil.setAlteraUsuario(check);
            perfil.setRemoveUsuario(check);
            perfil.setVerUsuario(check);
        }
    }

    public void checkAllPerfilAcesso(PerfilAcesso perfil, boolean check) {
        if (perfil != null) {
            perfil.setIncluiPerfilAcesso(check);
            perfil.setAlteraPerfilAcesso(check);
            perfil.setRemovePerfilAcesso(check);
            perfil.setVerPerfilAcesso(check);
        }
    }
}
