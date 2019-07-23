package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.dss.modelo.Profissional;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceProfissional;

@Named("profissional")
@RequestScoped
public class ProfissionalBean implements Serializable{

	private List<Profissional> profissionais = new ArrayList<>();
	private Profissional profissional;
	private String nome;
	private String especializacao;
	private String numeroConselho;
	private boolean cadastrar;
	
	@Inject
	private FacesContext context;
	
	@PostConstruct
	public void inicializar() {
		ServiceProfissional sp = new ServiceProfissional();
		Service servico = new Service();
		profissionais = new ArrayList<>();

		@SuppressWarnings("unchecked")
		var listagem = (List<Profissional>) servico.Listar(sp);
		
		for(var item : listagem) {
			profissionais.add(item);
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		Service servico = new Service();
		ServiceProfissional sp = new ServiceProfissional();
		var p = (Profissional) event.getObject();
		
		var up = servico.Atualizar(sp, p);

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
	
	public void onExcluir(Profissional profissional) {
		Service servico = new Service();
		ServiceProfissional sp = new ServiceProfissional();
		var p = profissional;
		var excluir = servico.Excluir(sp, p.getId());
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
		return "viewProfissional";
	}
	
	public void adicionar() {
		Service servico = new Service();
		ServiceProfissional sp = new ServiceProfissional();
		
		Profissional prof = new Profissional();
		
		prof.setNome(nome);
		prof.setEspecializacao(especializacao);
		prof.setNumeroconselho(numeroConselho);
		
		var add = servico.Adicionar(sp, prof);
		profissionais.add(prof);
		
		if(add) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro adicionado com sucesso.");
			context.addMessage(null, msg);
			this.setNome("");
			this.setEspecializacao("");
			this.setNumeroConselho("");
			inicializar();
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Falha ao adicionar o cadastro.");
			context.addMessage(null, msg);
		}
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}
	public boolean isCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public String getNumeroConselho() {
		return numeroConselho;
	}

	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}
}
