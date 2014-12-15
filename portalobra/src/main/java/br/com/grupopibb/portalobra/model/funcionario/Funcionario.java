/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.funcionario;

import br.com.grupopibb.portalobra.acesso.FuncionarioCentro;
import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.geral.CentroCusto;
import br.com.grupopibb.portalobra.model.geral.Empresa;
import br.com.grupopibb.portalobra.model.tipos.EnumHabilitado;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findByLogin",
            query = " SELECT DISTINCT f FROM Funcionario f "
            + " WHERE f.login = :login"),
    @NamedQuery(name = "Funcionario.findParam",
            query = " SELECT DISTINCT f FROM Funcionario f LEFT JOIN f.centros c  "
            + " WHERE ( :login2 = 'todos' OR f.login LIKE :login ) "
            + " AND ( :nome2 = 'todos' OR ( f.nome + f.sobrenome ) LIKE :nome ) "
            + " AND ( :empresa2 = 'todos' OR f.empresa = :empresa ) "
            + " AND ( f.ativo = :ativo)"
            + " AND ( :centro2 = 'todos' OR :centro = (c.empresaCod + c.filialCod + c.codigo) ) "),
    @NamedQuery(name = "Funcionario.countParam",
            query = " SELECT COUNT(DISTINCT f) FROM Funcionario f LEFT JOIN f.centros c "
            + " WHERE ( :login2 = 'todos' OR f.login LIKE :login ) "
            + " AND ( :nome2 = 'todos' OR ( f.nome + f.sobrenome ) LIKE :nome ) "
            + " AND ( :empresa2 = 'todos' OR f.empresa = :empresa ) "
            + " AND ( f.ativo = :ativo)"
            + " AND ( :centro2 = 'todos' OR :centro = (c.empresaCod + c.filialCod + c.codigo) ) "),})
public class Funcionario implements EntityInterface<Funcionario> {

    public Funcionario() {
    }

    public Funcionario(List<FuncionarioCentro> funcionarioCentros) {
        this.funcionarioCentros = funcionarioCentros;
        this.ativo = EnumHabilitado.S;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fun_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Fun_Nome")
    private String nome;
    /* 
     */
    @Column(name = "Fun_Sobrenome")
    private String sobrenome;
    /*
     */
    @OneToOne(targetEntity = Empresa.class)
    @JoinColumn(name = "Empresa_Cod")
    private Empresa empresa;
    /*
     */
    @Column(name = "Fun_Login")
    private String login;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Fun_Ativo")
    private EnumHabilitado ativo;
    /*
     */
    @Transient
    private String senha;
    /*
     */
    @ManyToMany(targetEntity = CentroCusto.class, mappedBy = "funcionarios", fetch = FetchType.EAGER)
    private List<CentroCusto> centros;
    /*
     */
    @OneToMany(targetEntity = FuncionarioCentro.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "Fun_Cod", referencedColumnName = "Fun_Cod")
    private List<FuncionarioCentro> funcionarioCentros;

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public List<CentroCusto> getCentros() {
        return centros;
    }

    public void setCentros(List<CentroCusto> centros) {
        this.centros = centros;
    }

    public List<FuncionarioCentro> getFuncionarioCentros() {
        return funcionarioCentros;
    }

    public void setFuncionarioCentros(List<FuncionarioCentro> funcionarioCentros) {
        this.funcionarioCentros = funcionarioCentros;
    }

    public boolean isAtivo() {
        if (this.ativo == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public void setAtivo(boolean ativo) {
        if (ativo) {
            this.ativo = EnumHabilitado.S;
        } else {
            this.ativo = EnumHabilitado.N;
        }
    }

    @Override
    public Serializable getId() {
        return nome;
    }

    @Override
    public String getLabel() {
        return sobrenome;
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
    public int compareTo(Funcionario o) {
        return this.getCodigo().compareTo(o.getCodigo());
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login;
    }
}
