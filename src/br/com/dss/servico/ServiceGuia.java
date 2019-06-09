package br.com.dss.servico;

import br.com.dss.dao.GuiaDao;

public class ServiceGuia implements IListagem{

	@Override
	public Object Lista() {
		
		GuiaDao arquivo = new GuiaDao();
		
		var guias = arquivo.Listar();
		return guias;
	}

	@Override
	public Object obter(Object objeto) {
		
		GuiaDao arquivo = new GuiaDao();
		
		var guia = arquivo.Obter((String[]) objeto);
		return guia;
	}
}
