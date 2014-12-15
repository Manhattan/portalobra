/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Especie_de_Credor")
public class EspecieCredor implements EntityInterface<EspecieCredor> {

    @Id
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "Esp_Cod")
    private String codigo;
    /**/
    @Size(max = 35)
    @Column(name = "Esp_Titulo")
    private String titulo;
    /**/
    @Size(max = 1)
    @Column(name = "CGC_CPF")
    private String cgccpf;
    /**/
    @Size(max = 15)
    @Column(name = "DepartamentoPrincipal")
    private String departamentoPrincipal;

    public EspecieCredor initClasse(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        return this;
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
    public int compareTo(EspecieCredor o) {
        return this.codigo.compareTo(o.getCodigo());
    }

    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codigo);
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
        final EspecieCredor other = (EspecieCredor) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

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

    public String getCgccpf() {
        return cgccpf;
    }

    public void setCgccpf(String cgccpf) {
        this.cgccpf = cgccpf;
    }

    public String getDepartamentoPrincipal() {
        return departamentoPrincipal;
    }

    public void setDepartamentoPrincipal(String departamentoPrincipal) {
        this.departamentoPrincipal = departamentoPrincipal;
    }
}
