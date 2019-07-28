package br.com.dss.servico;

import java.sql.Date;

public class Service {
	
	public boolean Adicionar(IAdiciona add, Object objeto) {
		var ret = add.adicionar(objeto);
		return ret;
	}
	
	public boolean Atualizar(IAtualiza update, Object objeto) {
		var ret = update.atualizar(objeto);
		return ret;
	}
	
	public boolean Existe(IDados dados, String filtro) {
		var ret = dados.isExist(filtro);
		return ret;
	}
	
	public boolean Excluir(IExclui exclui, Object objeto) {
		var ret = exclui.isExcluir(objeto);
		return ret;
	}
	
	public Object Obter(IListagem listagem, Object objeto) {
		var ret = listagem.obter(objeto);
		return ret;
	}
	
	public Object Obter(IListagemPorData listagem, Object objeto1, Object objeto2, Date dtIni, Date dtFim) {
		var ret = listagem.obter(objeto1, objeto2, dtIni, dtFim);
		return ret;
	}
	
	public Object Listar(IListagem listagem) {
		var ret = listagem.Lista();
		return ret;
	}
	
	public Object Quantidade(IQuantidade qtd) {
		var ret = qtd.quantidade();
		return ret;
	}
	
	public Object Somar(IObtemSoma soma, Object objeto1, Object objeto2, Date dtIni, Date dtFim) {
		var ret = soma.somar(objeto1, objeto2, dtIni, dtFim);
		return ret;
	}
}
