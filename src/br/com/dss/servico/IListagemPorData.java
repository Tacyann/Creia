package br.com.dss.servico;

import java.sql.Date;

public interface IListagemPorData {

	public Object obter(Object objeto, Date dtIni, Date dtFim);
}
