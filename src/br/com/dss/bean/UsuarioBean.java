package br.com.dss.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.dao.UsuarioDao;

@Named("usuario")
@RequestScoped
public class UsuarioBean {

	public String[] getUsuario(String id) {
		UsuarioDao conn = new UsuarioDao();
		var usuario = conn.Obter(id);
		return usuario;
	}
}
