/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Movimento_Financeiro_Natureza")
public class NaturezaFinanceira implements EntityInterface<NaturezaFinanceira>{

    @Id
    @NotNull
    @Column(name = "MovFinNatureza_Cod")
    private String codigo;
    /*
     */
    @Column(name="MovFin_Ordem")
    private String ordem;
    /*
     */
    @Column(name = "MovFinNatureza_Titulo")
    private String titulo;
    /*
     */
    @Column(name="MovFin_OpcoesEntrada")
    private String opcoesEntrada;
    /*
     */
    @Column(name="MovFin_OpcoesAssunto")
    private String opcoesAssunto;
    /*
     */
    @Column(name="MovFin_OpcoesDocTipo")
    private String docTipo;
    /*
     */
    @Column(name="MovFin_OpcoesEspCentro")
    private String opcoesEspecieCentro;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return titulo;
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
    public int compareTo(NaturezaFinanceira o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
