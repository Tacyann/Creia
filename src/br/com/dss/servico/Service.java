package br.com.dss.servico;

public class Service {
	
	public boolean Existe(IDados existeDados, String filtro) {
		var ret = existeDados.isExist(filtro);
		return ret;
	}
}
