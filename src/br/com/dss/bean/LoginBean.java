package br.com.dss.bean;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.dss.ejb.UsuarioLogadoLocal;
import br.com.dss.modelo.Usuario;

@Named("login")
@RequestScoped
public class LoginBean implements Serializable{

	@EJB
	private UsuarioLogadoLocal userLogado;
	
	@Inject
	private FacesContext context;
	
	private String login;
	private String senha;
	private Usuario user;
	
	public void logado() {
		String viewId = context.getViewRoot().getViewId();
		NavigationHandler nh = context.getApplication().getNavigationHandler();
		boolean paginaLogin = viewId.lastIndexOf("login") > -1;
		
		if (existeUsuarioLogado() && paginaLogin) {
			nh.handleNavigation(context, null, "/home?faces-redirect=true");
		} else if (!existeUsuarioLogado() && !paginaLogin) {
			nh.handleNavigation(context, null, "/login?faces-redirect=true");
		}
	}
	
	public String logof() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}
	
	public String logar() {
		try {
			NavigationHandler nh = context.getApplication().getNavigationHandler();
			var ret = userLogado.existeUsuario(login, senha).get();
			
			if (ret != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", ret);
				nh.handleNavigation(context, null, "/home?faces-redirect=true");
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Usuário ou senha inválido!");
				context.addMessage(null, msg);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
