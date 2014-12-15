/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.model.geral.CentroCusto;

/**
 *
 * @author administrator
 */
public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private CentroCusto centro;

    public Usuario() {
    }
    
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }
    
}
