package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.ClienteArgument;
import br.com.dss.argument.GuiaArgument;

@Named("relatorio")
@RequestScoped
public class RelatorioBean implements Serializable {
	
	private List<ClienteArgument> clientes;
	private ClienteArgument cliente; 
	private List<GuiaArgument> guias;
	
	@Inject
	private FacesContext context;
	
//	@PostConstruct
//	public void imprimir() {
//		clientes = new ArrayList<ClienteArgument>();
//		
//		if(guias.size() > 0) {
//			System.out.println("guias");
//		}
//				
//		@SuppressWarnings({ "unchecked", "static-access" })
//		var guiasGeren = (List<GuiaArgument>) context.getCurrentInstance().getExternalContext().getSessionMap().get("guias");
//		//var guiasGeren = (List<GuiaArgument>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("guias");
//		System.out.println("chegou");
//		
//		if(guiasGeren != null && guiasGeren.size()>0) {
//			for(var guia :  guiasGeren) {
//				System.out.println("Esta dentro do loop.");
//				cliente = new ClienteArgument();
//				cliente.setBeneficiario(guia.getBeneficiario());
//				cliente.setProcedimento(guia.getDetalheGuia().getProcedimento());
//				cliente.setValorInformado(guia.getDetalheGuia().getValorInformado());
//				cliente.setValorGlosa(guia.getDetalheGuia().getValorGlosa());
//				cliente.setValorProcessado(guia.getDetalheGuia().getValorProcessado());
//				cliente.setValorLiberado(guia.getDetalheGuia().getValorLiberado());
//				
//				clientes.add(cliente);
//			}
//			
//		}
//	}
	
	public List<ClienteArgument> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteArgument> clientes) {
		this.clientes = clientes;
	}
	public ClienteArgument getCliente() {
		return cliente;
	}
	public void setCliente(ClienteArgument cliente) {
		this.cliente = cliente;
	}
	public List<GuiaArgument> getGuias() {
		return guias;
	}
	public void setGuias(List<GuiaArgument> guias) {
		this.guias = guias;
	}
}
