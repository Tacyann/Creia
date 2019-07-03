package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Profissional;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceProfissional;

@Named("profissional")
@RequestScoped
public class ProfissionalBean implements Serializable{

	private List<Profissional> profissionais = new ArrayList<>();
	private String nome;
	
	@PostConstruct
	public void inicializar() {
		ServiceProfissional sp = new ServiceProfissional();
		Service servico = new Service();

		@SuppressWarnings("unchecked")
		var listagem = (List<Profissional>) servico.Listar(sp);
		
		for(var item : listagem) {
			profissionais.add(item);
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
}