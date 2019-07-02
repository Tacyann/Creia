package br.com.dss.servico;

import java.sql.Date;

public interface IListagemPorData {

	public Object obter(Object objeto1, Object objeto2, Date dtIni, Date dtFim);
}
