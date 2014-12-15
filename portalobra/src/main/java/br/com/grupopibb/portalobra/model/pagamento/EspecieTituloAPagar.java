/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.pagamento;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
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
@Table(name="Especie_de_Titulos_Apagar")
public class EspecieTituloAPagar implements EntityInterface<EspecieTituloAPagar>{
    
    @Id
    @NotNull
    @Column(name="EspTit_Cod")
    private String codigo;
    /*
     */
    @Column(name="EspTit_Titulo", nullable = false)
    private String titulo;

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
    public int compareTo(EspecieTituloAPagar o) {
        return this.codigo.compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.codigo);
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
        final EspecieTituloAPagar other = (EspecieTituloAPagar) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
