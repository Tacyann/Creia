package br.com.dss.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;

@Named("relatorio")
@RequestScoped
public class RelatorioBean implements Serializable {
	
	@Inject
	private Flash flash;
	
	@PostConstruct
	public void imprimir() {
	
		var request = flash.get("guias");
		
		@SuppressWarnings("unchecked")
		var guias = (List<GuiaArgument>)request;
		
		System.out.println("Qtd de registros: " + guias.size());

		if(guias.size() > 0) {
			System.out.println("Guias");
		}
	}
}
