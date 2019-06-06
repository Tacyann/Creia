package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;

@Named("beneficiario")
@RequestScoped
public class BeneficiarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeBeneficiario;
	private List<String> beneficiarios = new ArrayList<>();
	
	public BeneficiarioBean() {
		Listar();
	}
	
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	
	public String Listar() {
		ServiceBeneficiario sb = new ServiceBeneficiario();
		Service servico = new Service();
		@SuppressWarnings("unchecked")
		var listagem = (List<Beneficiario>) servico.Listar(sb);
		
		for(var item : listagem) {
			beneficiarios.add(item.getNome());
		}
		
		return null;
	}
	
	public String obter(String nome) {
		
		ServiceBeneficiario sb = new ServiceBeneficiario();
		Service servico = new Service();
		var ret = (Beneficiario) servico.Obter(sb, nome);
		
		setNomeBeneficiario(ret.getNome());
		return null;
	}
	
	public List<String> getBeneficiarios() {
		return beneficiarios;
	}
}
