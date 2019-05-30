package br.com.dss.modelo;

public class Beneficiario {

	private int Id;
	private String nome;
	private String numerocarteira;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumerocarteira() {
		return numerocarteira;
	}
	public void setNumerocarteira(String numerocarteira) {
		this.numerocarteira = numerocarteira;
	}
	public int getId() {
		return Id;
	}
}
