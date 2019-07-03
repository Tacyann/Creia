package br.com.dss.servico;

import br.com.dss.dao.ProfissionalDao;
import br.com.dss.modelo.Profissional;

public class ServiceProfissional implements IListagem, IAdiciona {

	@Override
	public Object Lista() {
		
		ProfissionalDao arquivo = new ProfissionalDao();
		var profissionais = arquivo.Listar();
		return profissionais;
	}

	@Override
	public Object obter(Object objeto) {
		
		ProfissionalDao arquivo = new ProfissionalDao();
		var profissional = arquivo.Obter((String) objeto);
		return profissional;
	}

	@Override
	public boolean adicionar(Object objeto) {
		
		ProfissionalDao arquivo = new ProfissionalDao();
		var isAdiciona = arquivo.Adicionar((Profissional) objeto);
		return isAdiciona;
	}
}
