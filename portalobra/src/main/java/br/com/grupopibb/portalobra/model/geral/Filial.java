/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.geral;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Filial")
public class Filial implements EntityInterface<Filial> {

    @Id
    @Column(name = "Empresa_Cod", length = 3)
    private String empresaCod;
    /*
     */
    @Id
    @Column(name = "Filial_Cod", length = 2)
    private String filialCod;
    /*
     */
    @Column(name = "Filial_Nome")
    private String nome;
    /*
     */
    @Column(name = "Filial_Endereco")
    private String endereco;
    /*
     */
    @Column(name = "Filial_Numero")
    private String numero;
    /*
     */
    @Column(name = "Filial_Complto")
    private String complemento;
    /*
     */
    @Column(name = "Filial_CEP")
    private String cep;
    /*
     */
    @Column(name = "Filial_Bairro")
    private String bairro;
    /*
     */
    @Column(name = "Filial_Cidade")
    private String cidade;
    /*
     */
    @Column(name = "UF_Sigla")
    private String uf;
    /*
     */
    @Column(name = "Filial_CGC")
    private String cnpj;
    /*
     */
    @Column(name="Filial_InscricaoEstadual")
    private String inscricaoEstadual;
    /*
     */
    @OneToMany(targetEntity = FilialComunicacao.class, mappedBy = "filial")
    private List<FilialComunicacao> contatos;

    /**
     * Retorna o telefone do primeiro contato da lista de contatos.
     */
    public String getTelefone() {
        String telefone;
        try {
            if (contatos != null && !contatos.isEmpty()) {
                telefone = contatos.get(0).getTelefone();
            } else {
                telefone = "";
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            telefone = "";
        }
        return telefone;
    }

    /**
     * Retorna o nome do primeiro contato da lista de contatos.
     */
    public String getContato() {
        String contato;
        try {
            if (contatos != null && !contatos.isEmpty()) {
                contato = contatos.get(0).getContato();
            } else {
                contato = "";
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            contato = "";
        }
        return contato;
    }

    /**
     * Retorna o e-mail do primeiro contato da lista de contatos.
     */
    public String getEmail() {
        String email;
        try {
            if (contatos != null && !contatos.isEmpty()) {
                email = contatos.get(0).getEmail();
            } else {
                email = "";
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            email = "";
        }
        return email;
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public String getFilialCod() {
        return filialCod;
    }

    public void setFilialCod(String filialCod) {
        this.filialCod = filialCod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public List<FilialComunicacao> getContatos() {
        return contatos;
    }

    public void setContatos(List<FilialComunicacao> contatos) {
        this.contatos = contatos;
    }

    @Override
    public Serializable getId() {
        return this.empresaCod + this.filialCod;
    }

    @Override
    public String getLabel() {
        return this.nome;
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
    public int compareTo(Filial o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }
}
