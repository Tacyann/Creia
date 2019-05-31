package br.com.dss.servico;

public class Dados {
	
	public boolean Existe(IExisteDados c, String filtro) {
		var ret = c.isExist(filtro);
		return ret;
	}
}
