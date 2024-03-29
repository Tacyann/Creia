package br.com.dss.servico;

import br.com.dss.dao.UsuarioDao;
import br.com.dss.modelo.Usuario;

public class ServiceUsuario implements IDados, IListagem, IAdiciona, IAtualiza, IExclui {

	@Override
	public boolean isExist(Object filtro) {
		UsuarioDao usuario = new UsuarioDao();
		var existe = usuario.Obter((Usuario) filtro);
		
		if(existe != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object Lista() {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Listar();
		return ret;
	}

	@Override
	public Object obter(Object objeto) {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Obter((Usuario) objeto);
		
		return ret;
	}

	@Override
	public boolean adicionar(Object objeto) {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Adicionar((Usuario) objeto);
		return ret;
	}

	@Override
	public boolean atualizar(Object objeto) {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Atualizar((Usuario) objeto);
		return ret;
	}

	@Override
	public boolean isExcluir(Object objeto) {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Excluir((int) objeto);
		return ret;
	}
}
