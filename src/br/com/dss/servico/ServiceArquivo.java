package br.com.dss.servico;

import br.com.dss.dao.ArquivoDao;

public class ServiceArquivo implements IExisteDados {

	@Override
	public boolean isExist(String filtro) {
		ArquivoDao arquivo = new ArquivoDao();
		var existe = arquivo.Obter(filtro);
		
		if(existe != null) {
			return true;
		}
		
		return false;
	}
}
