package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.ejb.DadosLocal;
import br.com.dss.modelo.Beneficiario;

@Named("cliente")
@RequestScoped
public class BeneficiarioBean implements Serializable{

	private String[] nomeBeneficiario;
	private List<Beneficiario> beneficiarios = new ArrayList<>();
	
	@EJB
	private DadosLocal geraDados;
	
	@PostConstruct
	public void inicializar() {
		try {
			beneficiarios = geraDados.pacientes().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
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
}
