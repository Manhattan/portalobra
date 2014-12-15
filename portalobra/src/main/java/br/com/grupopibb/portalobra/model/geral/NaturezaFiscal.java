/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumEntradaSaida;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Natureza_Fiscal")
public class NaturezaFiscal implements EntityInterface<NaturezaFiscal>{

    @Id
    @NotNull
    @Column(name = "Nop_Cod")
    private String codigo;
    /*
     */
    @Column(name = "Nop_Titulo")
    private String titulo;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Nop_ES")
    private EnumEntradaSaida entradaSaida;
    /*
     */
    @Column(name = "Nop_Opcoes")
    private String opcoes;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EnumEntradaSaida getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(EnumEntradaSaida entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public String getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        String cod = StringUtils.substring(codigo, 0, 1) 
                + "." + StringUtils.substring(codigo, 1, 3) 
                + "-" + StringUtils.substring(codigo, 3, 5);     
        return cod + " - " + titulo;
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
    public int compareTo(NaturezaFiscal o) {
        return this.codigo.compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NaturezaFiscal other = (NaturezaFiscal) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    
}
