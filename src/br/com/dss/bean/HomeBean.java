package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Usuario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceUsuario;

@Named("home")
@RequestScoped
public class HomeBean implements Serializable{

	private List<Usuario> usuarios;
	
	@PostConstruct
	public void inicializar() {
		ServiceUsuario su = new ServiceUsuario();
		Service servico = new Service();
		usuarios = new ArrayList<>();

		@SuppressWarnings("unchecked")
		var listagem = (List<Usuario>) servico.Listar(su);
		
		if(listagem.size() == 0) {
			usuario();
		}
	}
	
	public String sobre() {
		return "sobre";
	}
	
	public String cadastrar() {
		return "cadastro";
	}
	
	public String usuario() {
		return "usuario?faces-redirect=true";
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
