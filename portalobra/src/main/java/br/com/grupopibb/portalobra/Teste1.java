/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra;

/**
 *
 * @author administrator
 */
public class Teste1 {

    public Teste1() {
    }

    public Teste1(String nome) {
        this.nome = nome;
    }
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void alterar(Teste1 teste) {
        teste.setNome("PAULO");
    }
    
    @Override
    public String toString(){
        return "OK";
    }
}
