package br.com.dss.modelo;

import javax.xml.bind.annotation.XmlElement;

public class Beneficiario {

	private int Id;
	
	@XmlElement(name="nomeBeneficiario")
	private String nome;
	
	@XmlElement
	private String numeroCarteira;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroCarteira() {
		return numeroCarteira;
	}
	public void setNumeroCarteira(String numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
}
