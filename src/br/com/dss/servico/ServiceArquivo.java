package br.com.dss.servico;

import br.com.dss.dao.ArquivoDao;

public class ServiceArquivo implements IExisteDados {

	@Override
	public boolean isExist(String filtro) {
		ArquivoDao a = new ArquivoDao();
		var existe = a.Obter(filtro);
		
		if(existe != null) {
			return true;
		}
		
		return false;
	}
}
