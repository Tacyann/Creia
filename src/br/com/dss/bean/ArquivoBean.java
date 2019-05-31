package br.com.dss.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.servico.Dados;
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
		Dados d = new Dados();
		var existe = d.Existe(s, filtro);
		var retorno = "Não existe";
		if(existe) {
			retorno = "Existe";
		}
		
		flash.put("nomeDoArquivo",filtro);
		flash.put("existe", retorno);
		
		return "result?faces-redirect=true";
	}
}
