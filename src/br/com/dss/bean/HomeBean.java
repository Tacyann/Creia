package br.com.dss.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Usuario;

@Named("home")
@RequestScoped
public class HomeBean implements Serializable{

	private List<Usuario> usuarios;
	
	public String sobre() {
		return "sobre";
	}
	
	public String cadastrar() {
		return "cadastro";
	}
	
	public String usuario() {
		return "usuario";
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
