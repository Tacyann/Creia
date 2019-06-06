package br.com.dss.servico;

import br.com.dss.dao.BeneficiarioDao;

public class ServiceBeneficiario implements IDados, IListagem {

	@Override
	public boolean isExist(String filtro) {
		
		return false;
	}

	@Override
	public Object obter(Object objeto) {
		
		BeneficiarioDao arquivo = new BeneficiarioDao();
		
		var beneficiario = arquivo.Obter((String) objeto);
		return beneficiario;
	}

	@Override
	public Object Lista() {
		
		BeneficiarioDao arquivo = new BeneficiarioDao();
		
		var beneficiarios = arquivo.Listar();
		return beneficiarios;
	}
}
