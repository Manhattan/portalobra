/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.ar;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Documentos_de_Entrada_Tipo")
public class DocumentoEntradaTipo implements EntityInterface<DocumentoEntradaTipo>{

    @Id
    @NotNull
    @Column(name="EntDocTipo_Cod")
    private String codigo;
    /*
     */
    @Column(name="EntDocTipo_Titulo")
    private String titulo;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name="EntDocTipo_UnicidadeporCentro")
    private EnumsGeral unidadePorCentro;

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

    public EnumsGeral getUnidadePorCentro() {
        return unidadePorCentro;
    }

    public void setUnidadePorCentro(EnumsGeral unidadePorCentro) {
        this.unidadePorCentro = unidadePorCentro;
    }
    
       
    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return codigo + " - " + titulo;
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
    public int compareTo(DocumentoEntradaTipo o) {
        return this.codigo.compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.codigo);
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
        final DocumentoEntradaTipo other = (DocumentoEntradaTipo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    public DocumentoEntradaTipo initClass(String codigo, String titulo){
        this.codigo = codigo;
        this.titulo = titulo;
        return this;
    }
    
}
