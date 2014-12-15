/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.model.tipos;


/**
 *
 * @author administrator
 */
public enum EnumSituacaoSolicitacao{
    
    RS("Registro Solicitacao"),
    CS("Conclusao Solicitacao"),
    AS("Autorizacao Solicitacao"),
    RC("Registro Coleta"),
    CC("Conclusao Coleta"),
    CO("Coleta"),
    RP("Registro Pedido"),
    AP("Autorizacao Pedido"),
    ENF("Emissao Nota Fiscal"),
    EMO("Entrada Material Obra"),
    RA("Registro AR"),
    AA("Autorizacao AR"),
    EP("Em Pagamento"),
    PG("Pagamento"),
    OK("Ok"),
    R("Rejeitado"),
    NENHUM("");
    
    private String label;
    
    private EnumSituacaoSolicitacao(String label){
        this.label = label;
    }
        
    /** Retorna a descrição do Enum.
     */
    public String getLabel(){
        return label;
    }

    public static EnumSituacaoSolicitacao getForLabel(String lab){
        EnumSituacaoSolicitacao enumm;
        
        for (EnumSituacaoSolicitacao e : EnumSituacaoSolicitacao.values()){
            if (e.getLabel().equals(lab)){
                enumm = e;
                return enumm;
            }
        }
        return null;
    }

    
}
