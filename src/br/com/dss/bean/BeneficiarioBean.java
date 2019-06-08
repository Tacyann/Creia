package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;

@Named("cliente")
@RequestScoped
public class BeneficiarioBean implements Serializable{

	private String[] nomeBeneficiario;
	private List<String> nomes = new ArrayList<>();
	private List<Beneficiario> beneficiarios = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		ServiceBeneficiario sb = new ServiceBeneficiario();
		Service servico = new Service();

		@SuppressWarnings("unchecked")
		var listagem = (List<Beneficiario>) servico.Listar(sb);
		
		for(var item : listagem) {
			beneficiarios.add(item);
			nomes.add(item.getNome());
		}
	}
	
	public String[] getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String[] nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}
	public List<String> getNomes() {
		return nomes;
	}
}
