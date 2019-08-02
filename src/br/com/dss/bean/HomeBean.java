package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ListenerFor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.dss.modelo.Usuario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceUsuario;

@Named("home")
@RequestScoped
public class HomeBean implements Serializable{

	private List<Usuario> usuarios;
	
	@Inject
	private FacesContext context;
	
//	@PostConstruct
//	public void inicializar() {
//		ServiceUsuario su = new ServiceUsuario();
//		Service servico = new Service();
//		usuarios = new ArrayList<>();
//
//		@SuppressWarnings("unchecked")
//		var listagem = (List<Usuario>) servico.Listar(su);
//		
//		if(listagem.size() == 0) {
//			usuario();
//		}
//	}

	public void logado() {
		String viewId = context.getViewRoot().getViewId();
		System.out.println("teste de login");
		NavigationHandler nh = context.getApplication().getNavigationHandler();
		boolean paginaLogin = viewId.lastIndexOf("login") > -1;

		if (existeUsuarioLogado() && paginaLogin) {
			nh.handleNavigation(context, null, "/home?faces-redirect=true");
		} else if (!existeUsuarioLogado() && !paginaLogin) {
			nh.handleNavigation(context, null, "/login?faces-redirect=true");
		}
	}
	
	public boolean existeUsuarioLogado() {
		return (((Usuario) getAtributoSessao("usuario")) != null);
	}

	public Object getAtributoSessao(String attributeName) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			return session.getAttribute(attributeName);
		}
		return null;
	}
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
