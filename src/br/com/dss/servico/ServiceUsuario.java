package br.com.dss.servico;

import br.com.dss.dao.UsuarioDao;
import br.com.dss.modelo.Usuario;

public class ServiceUsuario implements IDados, IListagem, IAdiciona {

	@Override
	public boolean isExist(String filtro) {
		UsuarioDao usuario = new UsuarioDao();
		var existe = usuario.Obter(filtro);
		
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
		
		return null;
	}

	@Override
	public boolean adicionar(Object objeto) {
		UsuarioDao usuario = new UsuarioDao();
		var ret = usuario.Adicionar((Usuario) objeto);
		return ret;
	}
}
