package br.com.dss.servico;

import java.sql.Date;

import br.com.dss.dao.GuiaDao;

public class ServiceGuia implements IListagem, IListagemPorData, IObtemSoma{

	@Override
	public Object Lista() {
		return null;
	}

	@Override
	public Object obter(Object objeto) {
		return null;		
	}

	@Override
	public Object obter(Object objeto1, Object objeto2, Date dtIni, Date dtFim) {
		GuiaDao arquivo = new GuiaDao();		
		var guia = arquivo.Obter((String[]) objeto1, (String[]) objeto2, dtIni, dtFim);
		return guia;
	}

	@Override
	public Object somar(Object objeto1, Object objeto2, Date dtIni, Date dtFim) {
		GuiaDao arquivo = new GuiaDao();
		var guia = arquivo.ObtemSoma((String[]) objeto1, (String[]) objeto2, dtIni, dtFim);
		return guia;
	}
}
