package br.com.dss.servico;

import br.com.dss.dao.BeneficiarioDao;

public class ServiceBeneficiario implements IDados, IListagem, IQuantidade {

	@Override
	public boolean isExist(Object filtro) {
		return false;
	}

	@Override
	public Object obter(Object objeto) {
		BeneficiarioDao arquivo = new BeneficiarioDao();
		var ret = arquivo.Obter((String) objeto);
		return ret;
	}

	@Override
	public Object Lista() {
		BeneficiarioDao arquivo = new BeneficiarioDao();
		var ret = arquivo.Listar();
		return ret;
	}

	@Override
	public int quantidade() {
		BeneficiarioDao arquivo = new BeneficiarioDao();
		var ret = arquivo.qtdClientes();
		return ret;
	}
}
