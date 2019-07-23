package br.com.dss.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.dss.modelo.Usuario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceUsuario;

@Named("usuario")
@RequestScoped
public class UsuarioBean {

	@Inject
	private FacesContext context;

	private List<Usuario> usuarios;
	private boolean cadastrar;
	private String nomeCompleto;
	private String endereco;
	private String complemento;
	private String numero;
	private String cidade;
	private String estado;
	private String cep;
	private String ddd;
	private String telefone;
	private String email;
	private String usuario;
	private String senha;
	private String nivel;

	@PostConstruct
	public void inicializar() {
		ServiceUsuario su = new ServiceUsuario();
		Service servico = new Service();
		usuarios = new ArrayList<>();

		@SuppressWarnings("unchecked")
		var listagem = (List<Usuario>) servico.Listar(su);

		for(var item : listagem) {
			usuarios.add(item);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Service servico = new Service();
		ServiceUsuario sp = new ServiceUsuario();
		var user = (Usuario) event.getObject();

		var up = servico.Atualizar(sp, user);

		if(up) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro salvo com sucesso.");
			context.addMessage(null, msg);
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Falha ao salvar o cadastro.");
			context.addMessage(null, msg);
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Edição cancelada.");
		context.addMessage(null, msg);
	}

	public void onExcluir(Usuario usuario) {
		Service servico = new Service();
		ServiceUsuario sp = new ServiceUsuario();
		var user = usuario;
		var excluir = servico.Excluir(sp, user.getId());
		if(excluir) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro excluido com sucesso.");
			context.addMessage(null, msg);
			inicializar();
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Falha ao excluir o cadastro.");
			context.addMessage(null, msg);
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public String cadastro() {
		return "viewUsuario";
	}

	public void adicionar() {
		Service servico = new Service();
		ServiceUsuario su = new ServiceUsuario();
		String ufSE = null;

		Usuario user = new Usuario();

		user.setNomeCompleto(nomeCompleto);
		user.setEndereco(endereco);
		user.setComplemento(complemento);
		user.setNumero(numero);
		user.setCidade(cidade);
		if(estado.isEmpty()) {
			ufSE = "25";
		}else {
			ufSE = estado;
		}
		user.setEstado(Integer.parseInt(ufSE));
		user.setCep(cep);
		user.setDdd(ddd);
		user.setTelefone(telefone);
		user.setEmail(email);
		user.setUsuario(usuario);
		user.setSenha(senha);
		var n = "user";
		user.setNivel(n);

		var add = servico.Adicionar(su, user);
		usuarios.add(user);

		if(add) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro adicionado com sucesso.");
			context.addMessage(null, msg);
			this.setNomeCompleto("");
			this.setEndereco("");
			this.setComplemento("");
			this.setNumero("");
			this.setCidade("");
			this.setEstado("");
			this.setCep("");
			this.setDdd("");
			this.setTelefone("");
			this.setEmail("");
			this.setUsuario("");
			this.setSenha("");
			this.setNivel("");

			inicializar();
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Falha ao adicionar o cadastro.");
			context.addMessage(null, msg);
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
