/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.solicitacaocompra;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumsGeral;
import java.util.Objects;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tone.lima
 */
@Entity
@Table(name = "Solicitante")
@NamedQueries({
    @NamedQuery(name = "Solicitante.selectRange",
            query = " SELECT DISTINCT s FROM Solicitante s "
            + " WHERE (:nome2 = 'todos' OR s.nome LIKE :nome) "),
    @NamedQuery(name = "Solicitante.countRange",
            query = " SELECT COUNT (DISTINCT s) FROM Solicitante s "
            + " WHERE (:nome2 = 'todos' OR s.nome LIKE :nome) "),
    @NamedQuery(name = "Solicitante.findOneByName",
            query = "SELECT DISTINCT s FROM Solicitante s "
            + " WHERE (:nome2 = 'todos' OR s.nome = :nome) "),
    @NamedQuery(name="Solicitante.findLastId",
        query=" SELECT MAX(s.codigo) FROM Solicitante s")
})
public class Solicitante implements EntityInterface<Solicitante> {

    public Solicitante() {
    }

    public Solicitante(String codigo, String nome, EnumsGeral ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.ativo = ativo;
    }

    @Id
    @NotNull
    @Column(name = "Solicitante_Cod")
    private String codigo;
    /*
     */
    @Size(min = 1, max = 35)
    @Column(name = "Solicitante_Nome")
    private String nome;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Solicitante_Ativo")
    private EnumsGeral ativo;
    /*
     */
    @OneToMany(mappedBy = "solicitanteParent", cascade = CascadeType.ALL)
    @JoinColumn(name="Solicitante_Nome", referencedColumnName = "UserName")
    private List<UsuarioRestricaoSolicitante> usuariosLiberados;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return nome;
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
    public int compareTo(Solicitante o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.codigo);
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
        final Solicitante other = (Solicitante) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumsGeral getAtivo() {
        return ativo;
    }

    public void setAtivo(EnumsGeral ativo) {
        this.ativo = ativo;
    }

    public List<UsuarioRestricaoSolicitante> getUsuariosLiberados() {
        return usuariosLiberados;
    }

    public void setUsuariosLiberados(List<UsuarioRestricaoSolicitante> usuariosLiberados) {
        this.usuariosLiberados = usuariosLiberados;
    }
    
}
