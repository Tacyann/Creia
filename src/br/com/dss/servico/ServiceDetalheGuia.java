package br.com.dss.servico;

import br.com.dss.dao.DetalhesGuiaDao;

public class ServiceDetalheGuia implements IListagem{

	@Override
	public Object Lista() {
		
		DetalhesGuiaDao arquivo = new DetalhesGuiaDao();
		
		var detalhesGuias = arquivo.Listar();
		return detalhesGuias;
	}

	@Override
	public Object obter(Object objeto) {
		
		DetalhesGuiaDao arquivo = new DetalhesGuiaDao();
		
		var detalhesGuia = arquivo.Obter((int) objeto);
		return detalhesGuia;
	}
}
