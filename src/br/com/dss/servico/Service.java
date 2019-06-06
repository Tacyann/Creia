package br.com.dss.servico;

public class Service {
	
	public boolean Existe(IDados dados, String filtro) {
		var ret = dados.isExist(filtro);
		return ret;
	}
	
	public Object Obter(IListagem listagem, Object objeto) {
		var ret = listagem.obter(objeto);
		return ret;
	}
	
	public Object Listar(IListagem listagem) {
		var ret = listagem.Lista();
		return ret;
	}
}
