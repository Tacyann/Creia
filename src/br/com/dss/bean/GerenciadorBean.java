package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceDetalheGuia;
import br.com.dss.servico.ServiceGuia;

@Named("gerenciador")
@RequestScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext facescontext;
	private List<Guia> guias = new ArrayList<>();
	private String[] clientes;
	private String[] descricao;
		
	public void processar() {
		StringBuilder sb = new StringBuilder("Clientes: ");
		
		for(var cliente : clientes) {
			sb.append(cliente);
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, sb.toString());
		facescontext.addMessage(null, msg);
	}
	
	public void registros() {
		
		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		ServiceDetalheGuia sdg = new ServiceDetalheGuia();
		
		@SuppressWarnings("unchecked")
		var listagemGuia = (List<Guia>) servico.Obter(sg, clientes);
		
		for(var item : listagemGuia) {
			var guia = new Guia();
			guia.setPrestador(item.getPrestador());
			guia.setOperadora(item.getOperadora());
			guia.setSenha(item.getSenha());
			guia.setBeneficiario(item.getBeneficiario());
			guia.setDataIni(item.getDataIni());
			guia.setSituacaoGuia(item.getSituacaoGuia());
			guia.setValorInformadoGuia(item.getValorInformadoGuia());
			guia.setValorProcessadoGuia(item.getValorProcessadoGuia());
			guia.setValorLiberadoGuia(item.getValorProcessadoGuia());
			var detalheGuia = (DetalheGuia) servico.Obter(sdg, guia.getPrestador());			
			guia.setDetalheGuia(detalheGuia);
			
			guias.add(guia);
		}
	}

	public List<String> nomeBeneficiario(){
		
		List<String> nomes = new ArrayList<>();
		Service servico = new Service();
		ServiceBeneficiario sb = new ServiceBeneficiario();
		
		@SuppressWarnings("unchecked")
		var listagem = (List<Beneficiario>) servico.Listar(sb);
		
		for(var item : listagem) {
			nomes.add(item.getNome());
		}
		return nomes;
	}

	public FacesContext getFacescontext() {
		return facescontext;
	}

	public void setFacescontext(FacesContext facescontext) {
		this.facescontext = facescontext;
	}

	public List<Guia> getGuias() {
		return guias;
	}

	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}

	public String[] getClientes() {
		return clientes;
	}

	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}

	public String[] getDescricao() {
		return descricao;
	}

	public void setDescricao(String[] descricao) {
		this.descricao = descricao;
	}
}
