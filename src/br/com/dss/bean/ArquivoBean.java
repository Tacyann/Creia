package br.com.dss.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
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
	
	public String Processar() {
		ServiceArquivo arquivo = new ServiceArquivo();
		Service servico = new Service();
		var existe = servico.Existe(arquivo, "");
		var retorno = "Não existe";
		if(existe) {
			retorno = "Existe";
		}
		setExiste(retorno);
		
		flash.put("nomeDoArquivo", "Finalizou com sucesso");
		return "result?faces-redirect=true";
	}
}
