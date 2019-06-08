package br.com.dss.servico;

import br.com.dss.dao.ProcedimentoDao;

public class ServiceProcedimento implements IListagem {

	@Override
	public Object Lista() {
		
		ProcedimentoDao arquivo = new ProcedimentoDao();
		var procedimentos = arquivo.Listar();
		return procedimentos;
	}

	@Override
	public Object obter(Object objeto) {
		
		ProcedimentoDao arquivo = new ProcedimentoDao();
		var procedimento = arquivo.Obter((int) objeto);
		return procedimento;
	}
}
