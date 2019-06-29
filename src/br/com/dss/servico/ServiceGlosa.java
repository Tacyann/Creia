package br.com.dss.servico;

import br.com.dss.dao.GlosaDao;
import br.com.dss.modelo.Glosa;

public class ServiceGlosa implements IDados, IListagem {

	@Override
	public boolean isExist(String filtro) {
		
		return false;
	}

	@Override
	public Object obter(Object objeto) {
		
		GlosaDao arquivo = new GlosaDao();
		
		var glosa = arquivo.Obter((Glosa) objeto);
		return glosa;
	}

	@Override
	public Object Lista() {
		
		GlosaDao arquivo = new GlosaDao();
		
		var glosa = arquivo.Listar();
		return glosa;
	}
}
