package br.com.dss.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceArquivo;

@Named
@RequestScoped
public class ArquivoBean implements Serializable{
	
	private String filtro;
	@Inject
	private Flash flash;
	@Inject
	private FacesContext context;
	
	private String existe;
	
	public String getExiste() {
		return existe;
	}

	public void setExiste(String existe) {
		this.existe = existe;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String ExisteArquivo() {
		ServiceArquivo arquivo = new ServiceArquivo();
		Service servico = new Service();
		var existe = servico.Existe(arquivo, filtro);
		var retorno = "Não existe";
		if(existe) {
			retorno = "Existe";
		}
		
		flash.put("nomeDoArquivo","O Arquivo " + filtro);
		flash.put("existe", retorno);
		
		return "result?faces-redirect=true";
	}
	
	public void adicionar() {
		ServiceArquivo arquivo = new ServiceArquivo();
		Service servico = new Service();
		var add = servico.Adicionar(arquivo, "");
	
		if(add) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Importação efetuada com sucesso.");
			context.addMessage(null, msg);
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Não existe arquivos pendentes para importar.");
			context.addMessage(null, msg);
		}
	}
	
	public String Processar() {
		ServiceArquivo arquivo = new ServiceArquivo();
		Service servico = new Service();
		var existe = servico.Existe(arquivo, "");
		var retorno = "Não existe";
		if(existe) {
			retorno = "Existe";
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Processado.");
			context.addMessage(null, msg);			
		}
		setExiste(retorno);
		
		return null;
	}
}
