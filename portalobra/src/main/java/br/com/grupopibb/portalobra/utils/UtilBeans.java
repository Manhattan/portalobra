/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.utils;

import java.util.logging.Level;

/**
 *
 * @author tone.lima
 */
public class UtilBeans {
    /**
     * Definie o nível de log para toda aplicação
     */
    public static final Level LEVEL_LOG = Level.INFO;
    /**
     * Define qual Unidade de Persistência a aplicação está utilizando.
     */
    public static final String PERSISTENCE_UNIT = "portalPU";
    /**
     * Define qual Banco de Dados aplicação está utilizando.
     */
    public static final String DATABASE_NAME = "DATA";
    /**
     * Define qual o nome do servidor de Banco de Dados a aplicação está utilizando.
     */
    public static final String SERVER_NAME = "ONLINE";
    /**
     * Define qual Usuário tem acesso ao banco de dados.
     */
    public static final String DATABASE_USER = "EU";
    /**
     * Define qual a porta de conexão do Banco de Dados.
     */
    public static final String PORT = "DOS FUNDOS";
    /**
     * Define a Senha para conexão com o banco de dados. Senha referente ao
     * DATABASE_USER.
     */ 
    public static final String DATABASE_PASS = "KD O DEVMEDIA";
}
