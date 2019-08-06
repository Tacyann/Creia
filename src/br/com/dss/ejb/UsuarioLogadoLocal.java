package br.com.dss.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

import br.com.dss.modelo.Usuario;

@Local
public interface UsuarioLogadoLocal {
	@Asynchronous
	public Future<Usuario> existeUsuario(String login, String senha);
}
