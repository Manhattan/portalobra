/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.coleta;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.Credor;
import br.com.grupopibb.portalobra.model.tipos.EnumPrazoFora;
import br.com.grupopibb.portalobra.model.tipos.EnumPrazoInicio;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tone.lima
 */
public class ColetaPrecoFornecedor implements EntityInterface<ColetaPrecoFornecedor> {

    @Id
    @NotNull
    @Column(name = "ColForn_Numero", nullable = false)
    private Long numero;
    /**/
    @Id
    @ManyToOne(targetEntity = ColetaPreco.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Coleta_Numero", nullable = false)
    private ColetaPreco coleta;
    /**/
    @Size(max = 2)
    @Column(name = "ColForn_Item")
    private String item;
    /**/
    @OneToOne(targetEntity = Credor.class, fetch = FetchType.EAGER)
    @Column(name="Cre_Cod")
    private Credor credor;
    /**/
    @Size(max=96)
    @Column(name="ColForn_PrazoDias")
    private String prazoDias;
    /**/
    @Size(max=96)
    @Column(name="ColForn_PrazoPerc")
    private String prazoPercentual;
    /**/
    @Enumerated(EnumType.STRING)
    @Column(name="ColForn_PrazoFora")
    private EnumPrazoFora prazoFora;
    /**/
    @Enumerated(EnumType.STRING)
    @Column(name="ColForn_PrazoInicio")
    private EnumPrazoInicio prazoInicio;

    @Override
    public Serializable getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMarcado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(ColetaPrecoFornecedor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
