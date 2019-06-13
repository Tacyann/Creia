package br.com.dss.servico;

import java.sql.Date;

public class Service {
	
	public boolean Existe(IDados dados, String filtro) {
		var ret = dados.isExist(filtro);
		return ret;
	}
	
	public Object Obter(IListagem listagem, Object objeto) {
		var ret = listagem.obter(objeto);
		return ret;
	}
	
	public Object Obter(IListagemPorData listagem, Object objeto, Date dtIni, Date dtFim) {
		var ret = listagem.obter(objeto, dtIni, dtFim);
		return ret;
	}
	
	public Object Listar(IListagem listagem) {
		var ret = listagem.Lista();
		return ret;
	}
}
