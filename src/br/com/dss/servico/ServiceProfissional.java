package br.com.dss.servico;

import br.com.dss.dao.ProfissionalDao;
import br.com.dss.modelo.Profissional;

public class ServiceProfissional implements IListagem, IAdiciona, IAtualiza {

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

	@Override
	public boolean atualizar(Object objeto) {

		ProfissionalDao arquivo = new ProfissionalDao();
		var isAdiciona = arquivo.Atualizar((Profissional) objeto);
		return isAdiciona;
	}
}
