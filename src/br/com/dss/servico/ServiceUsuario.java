package br.com.dss.servico;

import br.com.dss.dao.UsuarioDao;

public class ServiceUsuario implements IDados {

	@Override
	public boolean isExist(String filtro) {
		UsuarioDao usuario = new UsuarioDao();
		var existe = usuario.Obter(filtro);
		
		if(existe != null) {
			return true;
		}
		
		return false;
	}

	public Object getEntidade(Object entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
