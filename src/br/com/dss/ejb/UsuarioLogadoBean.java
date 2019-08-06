package br.com.dss.ejb;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Singleton;

import br.com.dss.modelo.Usuario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceUsuario;

/**
 * Session Bean implementation class UsuarioLogado
 */
@Singleton
public class UsuarioLogadoBean implements UsuarioLogadoLocal {

	boolean existeUsuario;
	
	@Override
	public Future<Usuario> existeUsuario(String login, String senha) {
		Service servico = new Service();
		ServiceUsuario su = new ServiceUsuario();
		Usuario user = new Usuario();
		user.setUsuario(login);
		user.setSenha(senha);
		var usuario = (Usuario)servico.Obter(su, user);
		
		if(usuario != null) {
			existeUsuario = true;
		}else {
			existeUsuario = false;
		}
		
		return new AsyncResult<Usuario>(usuario);
	}
}
