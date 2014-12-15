/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalobra.controller.pedido;

import br.com.grupopibb.portalobra.business.pedido.QualidadeBusiness;
import br.com.grupopibb.portalobra.model.ar.DocumentoEntradaAvaliacao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author administrator
 */
@ManagedBean
@RequestScoped
public class QualidadeController implements Serializable {

    @EJB
    private QualidadeBusiness qualidadeBusiness;

    public DocumentoEntradaAvaliacao getPontualidade(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getPontualidade(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getEspecPedido(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getEspecPedido(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getQualidadeIntegridade(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getQualidadeIntegridade(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getDadosNf(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getDadosNf(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getQualidadeIntrinseca(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getQualidadeIntrinseca(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getRequisitosComerciais(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getRequisitosComerciais(itensAvaliacao);
    }

    public DocumentoEntradaAvaliacao getRequisitosSgq(List<DocumentoEntradaAvaliacao> itensAvaliacao) {
        return qualidadeBusiness.getRequisitosSgq(itensAvaliacao);
    }
}
