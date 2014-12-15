/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.acesso;

import br.com.grupopibb.portalobra.model.common.EntityInterface;
import br.com.grupopibb.portalobra.model.tipos.EnumHabilitado;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Perfil_Acesso")
public class PerfilAcesso implements EntityInterface<PerfilAcesso> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Perfil_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Perfil_Nome", nullable = false)
    private String nome;
    /*
     */
    @Column(name = "Perfil_Descricao")
    private String descricao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Solicitacao")
    private EnumHabilitado incluiSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Solicitacao")
    private EnumHabilitado alteraSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Solicitacao")
    private EnumHabilitado removeSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Conclui_Solicitacao")
    private EnumHabilitado concluiSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Autoriza_Solicitacao")
    private EnumHabilitado autorizaSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Entrada_Material")
    private EnumHabilitado incluiEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Entrada_Material")
    private EnumHabilitado alteraEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Entrada_Material")
    private EnumHabilitado removeEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Saida_Material")
    private EnumHabilitado incluiSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Saida_Material")
    private EnumHabilitado alteraSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Saida_Material")
    private EnumHabilitado removeSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_AR")
    private EnumHabilitado incluiAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_AR")
    private EnumHabilitado alteraAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_AR")
    private EnumHabilitado removeAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Imagem_AR")
    private EnumHabilitado removeImagemAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Fundo_Fixo")
    private EnumHabilitado incluiFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Fundo_Fixo")
    private EnumHabilitado alteraFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Fundo_Fixo")
    private EnumHabilitado removeFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_RH_Ocorrencia")
    private EnumHabilitado incluiRhOcorrencia;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_RH_Ocorrencia")
    private EnumHabilitado alteraRhOcorrencia;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Exclui_RH_Ocorrencia")
    private EnumHabilitado removeRhOcorrencia;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_RH_CadGer")
    private EnumHabilitado incluiRhCadastroGeral;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_RH_CadGer")
    private EnumHabilitado alteraRhCadastroGeral;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Exclui_RH_CadGer")
    private EnumHabilitado removeRhCadastroGeral;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Usuario")
    private EnumHabilitado incluiUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Ver_Usuario")
    private EnumHabilitado verUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Usuario")
    private EnumHabilitado alteraUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Usuario")
    private EnumHabilitado removeUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Ver_Perfil_Acesso")
    private EnumHabilitado verPerfilAcesso;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Perfil_Acesso")
    private EnumHabilitado incluiPerfilAcesso;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Perfil_Acesso")
    private EnumHabilitado alteraPerfilAcesso;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Exclui_Perfil_Acesso")
    private EnumHabilitado removePerfilAcesso;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Ver_Usuarios_Conectados")
    private EnumHabilitado verUsuariosConectados;

    /* ========================= GETTERS E SETTERS ============================ */
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIncluiSolicitacao() {
        return habilitadoInBoolean(incluiSolicitacao);
    }

    public void setIncluiSolicitacao(Boolean incluiSolicitacao) {
        this.incluiSolicitacao = booleanInHabilitado(incluiSolicitacao);
    }

    public Boolean getAlteraSolicitacao() {
        return habilitadoInBoolean(alteraSolicitacao);
    }

    public void setAlteraSolicitacao(Boolean alteraSolicitacao) {
        this.alteraSolicitacao = booleanInHabilitado(alteraSolicitacao);
    }

    public Boolean getRemoveSolicitacao() {
        return habilitadoInBoolean(removeSolicitacao);
    }

    public void setRemoveSolicitacao(Boolean removeSolicitacao) {
        this.removeSolicitacao = booleanInHabilitado(removeSolicitacao);
    }

    public Boolean getConcluiSolicitacao() {
        return habilitadoInBoolean(concluiSolicitacao);
    }

    public void setConcluiSolicitacao(Boolean concluiSolicitacao) {
        this.concluiSolicitacao = booleanInHabilitado(concluiSolicitacao);
    }

    public Boolean getAutorizaSolicitacao() {
        return habilitadoInBoolean(autorizaSolicitacao);
    }

    public void setAutorizaSolicitacao(Boolean autorizaSolicitacao) {
        this.autorizaSolicitacao = booleanInHabilitado(autorizaSolicitacao);
    }

    public Boolean getIncluiEntradaMaterial() {
        return habilitadoInBoolean(incluiEntradaMaterial);
    }

    public void setIncluiEntradaMaterial(Boolean incluiEntradaMaterial) {
        this.incluiEntradaMaterial = booleanInHabilitado(incluiEntradaMaterial);
    }

    public Boolean getAlteraEntradaMaterial() {
        return habilitadoInBoolean(alteraEntradaMaterial);
    }

    public void setAlteraEntradaMaterial(Boolean alteraEntradaMaterial) {
        this.alteraEntradaMaterial = booleanInHabilitado(alteraEntradaMaterial);
    }

    public Boolean getRemoveEntradaMaterial() {
        return habilitadoInBoolean(removeEntradaMaterial);
    }

    public void setRemoveEntradaMaterial(Boolean removeEntradaMaterial) {
        this.removeEntradaMaterial = booleanInHabilitado(removeEntradaMaterial);
    }

    public Boolean getIncluiSaidaMaterial() {
        return habilitadoInBoolean(incluiSaidaMaterial);
    }

    public void setIncluiSaidaMaterial(Boolean incluiSaidaMaterial) {
        this.incluiSaidaMaterial = booleanInHabilitado(incluiSaidaMaterial);
    }

    public Boolean getAlteraSaidaMaterial() {
        return habilitadoInBoolean(alteraSaidaMaterial);
    }

    public void setAlteraSaidaMaterial(Boolean alteraSaidaMaterial) {
        this.alteraSaidaMaterial = booleanInHabilitado(alteraSaidaMaterial);
    }

    public Boolean getRemoveSaidaMaterial() {
        return habilitadoInBoolean(removeSaidaMaterial);
    }

    public void setRemoveSaidaMaterial(Boolean removeSaidaMaterial) {
        this.removeSaidaMaterial = booleanInHabilitado(removeSaidaMaterial);
    }

    public Boolean getIncluiAR() {
        return habilitadoInBoolean(incluiAR);
    }

    public void setIncluiAR(Boolean incluiAR) {
        this.incluiAR = booleanInHabilitado(incluiAR);
    }

    public Boolean getAlteraAR() {
        return habilitadoInBoolean(alteraAR);
    }

    public void setAlteraAR(Boolean alteraAR) {
        this.alteraAR = booleanInHabilitado(alteraAR);
    }

    public Boolean getRemoveAR() {
        return habilitadoInBoolean(removeAR);
    }

    public void setRemoveAR(Boolean removeAR) {
        this.removeAR = booleanInHabilitado(removeAR);
    }

    public Boolean getRemoveImagemAR() {
        return habilitadoInBoolean(removeImagemAR);
    }

    public void setRemoveImagemAR(Boolean removeImagemAR) {
        this.removeImagemAR = booleanInHabilitado(removeImagemAR);
    }

    public Boolean getIncluiFundoFixo() {
        return habilitadoInBoolean(incluiFundoFixo);
    }

    public void setIncluiFundoFixo(Boolean incluiFundoFixo) {
        this.incluiFundoFixo = booleanInHabilitado(incluiFundoFixo);
    }

    public Boolean getAlteraFundoFixo() {
        return habilitadoInBoolean(alteraFundoFixo);
    }

    public void setAlteraFundoFixo(Boolean alteraFundoFixo) {
        this.alteraFundoFixo = booleanInHabilitado(alteraFundoFixo);
    }

    public Boolean getRemoveFundoFixo() {
        return habilitadoInBoolean(removeFundoFixo);
    }

    public void setRemoveFundoFixo(Boolean removeFundoFixo) {
        this.removeFundoFixo = booleanInHabilitado(removeFundoFixo);
    }

    public Boolean getIncluiRhOcorrencia() {
        return habilitadoInBoolean(incluiRhOcorrencia);
    }

    public void setIncluiRhOcorrencia(Boolean incluiRhOcorrencia) {
        this.incluiRhOcorrencia = booleanInHabilitado(incluiRhOcorrencia);
    }

    public Boolean getAlteraRhOcorrencia() {
        return habilitadoInBoolean(alteraRhOcorrencia);
    }

    public void setAlteraRhOcorrencia(Boolean alteraRhOcorrencia) {
        this.alteraRhOcorrencia = booleanInHabilitado(alteraRhOcorrencia);
    }

    public Boolean getRemoveRhOcorrencia() {
        return habilitadoInBoolean(removeRhOcorrencia);
    }

    public void setRemoveRhOcorrencia(Boolean removeRhOcorrencia) {
        this.removeRhOcorrencia = booleanInHabilitado(removeRhOcorrencia);
    }

    public Boolean getIncluiRhCadastroGeral() {
        return habilitadoInBoolean(incluiRhCadastroGeral);
    }

    public void setIncluiRhCadastroGeral(Boolean incluiRhCadastroGeral) {
        this.incluiRhCadastroGeral = booleanInHabilitado(incluiRhCadastroGeral);
    }

    public Boolean getAlteraRhCadastroGeral() {
        return habilitadoInBoolean(alteraRhCadastroGeral);
    }

    public void setAlteraRhCadastroGeral(Boolean alteraRhCadastroGeral) {
        this.alteraRhCadastroGeral = booleanInHabilitado(alteraRhCadastroGeral);
    }

    public Boolean getRemoveRhCadastroGeral() {
        return habilitadoInBoolean(removeRhCadastroGeral);
    }

    public void setRemoveRhCadastroGeral(Boolean removeRhCadastroGeral) {
        this.removeRhCadastroGeral = booleanInHabilitado(removeRhCadastroGeral);
    }

    public Boolean getIncluiUsuario() {
        return habilitadoInBoolean(incluiUsuario);
    }

    public void setIncluiUsuario(Boolean incluiUsuario) {
        this.incluiUsuario = booleanInHabilitado(incluiUsuario);
    }

    public Boolean getVerUsuario() {
        return habilitadoInBoolean(verUsuario);
    }

    public void setVerUsuario(Boolean verUsuario) {
        this.verUsuario = booleanInHabilitado(verUsuario);
    }

    public Boolean getAlteraUsuario() {
        return habilitadoInBoolean(alteraUsuario);
    }

    public void setAlteraUsuario(Boolean alteraUsuario) {
        this.alteraUsuario = booleanInHabilitado(alteraUsuario);
    }

    public Boolean getRemoveUsuario() {
        return habilitadoInBoolean(removeUsuario);
    }

    public void setRemoveUsuario(Boolean removeUsuario) {
        this.removeUsuario = booleanInHabilitado(removeUsuario);
    }

    public Boolean getVerPerfilAcesso() {
        return habilitadoInBoolean(verPerfilAcesso);
    }

    public void setVerPerfilAcesso(Boolean verPerfilAcesso) {
        this.verPerfilAcesso = booleanInHabilitado(verPerfilAcesso);
    }

    public Boolean getIncluiPerfilAcesso() {
        return habilitadoInBoolean(incluiPerfilAcesso);
    }

    public void setIncluiPerfilAcesso(Boolean incluiPerfilAcesso) {
        this.incluiPerfilAcesso = booleanInHabilitado(incluiPerfilAcesso);
    }

    public Boolean getAlteraPerfilAcesso() {
        return habilitadoInBoolean(alteraPerfilAcesso);
    }

    public void setAlteraPerfilAcesso(Boolean alteraPerfilAcesso) {
        this.alteraPerfilAcesso = booleanInHabilitado(alteraPerfilAcesso);
    }

    public Boolean getRemovePerfilAcesso() {
        return habilitadoInBoolean(removePerfilAcesso);
    }

    public void setRemovePerfilAcesso(Boolean removePerfilAcesso) {
        this.removePerfilAcesso = booleanInHabilitado(removePerfilAcesso);
    }

    public Boolean getVerUsuariosConectados() {
        return habilitadoInBoolean(verUsuariosConectados);
    }

    public void setVerUsuariosConectados(Boolean verUsuariosConectados) {
        this.verUsuariosConectados = booleanInHabilitado(verUsuariosConectados);
    }

    /* ============================================================================ */
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
    public int compareTo(PerfilAcesso o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
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
        final PerfilAcesso other = (PerfilAcesso) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    private boolean habilitadoInBoolean(EnumHabilitado enumm) {
        if (enumm == null) {
            return false;
        }
        if (enumm == EnumHabilitado.S) {
            return true;
        }
        return false;
    }
    
    private EnumHabilitado booleanInHabilitado(Boolean bool){
        if (bool){
            return EnumHabilitado.S;
        }
        return EnumHabilitado.N;
    }
}
