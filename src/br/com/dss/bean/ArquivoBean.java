package br.com.dss.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceArquivo;

@Named
@RequestScoped
public class ArquivoBean {
	
	private String filtro;
	@Inject
	private Flash flash;
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String ExisteArquivo() {
		ServiceArquivo s = new ServiceArquivo();
		Service d = new Service();
		var existe = d.Existe(s, filtro);
		var retorno = "Não existe";
		if(existe) {
			retorno = "Existe";
		}
		
		flash.put("nomeDoArquivo","O Arquivo " + filtro);
		flash.put("existe", retorno);
		
		return "result?faces-redirect=true";
	}
}
