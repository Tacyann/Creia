package br.com.dss.servico;

import br.com.dss.dao.LoteDao;

public class ServiceLote implements IListagem{

	@Override
	public Object Lista() {
		
		LoteDao arquivo = new LoteDao();
		
		var lotes = arquivo.Listar();
		return lotes;
	}

	@Override
	public Object obter(Object objeto) {
		
		LoteDao arquivo = new LoteDao();
		
		var lote = arquivo.Obter((int) objeto);
		return lote;
	}
}
