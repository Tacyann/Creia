package br.com.dss.modelo;

public class Profissional {

	private int id;
	private String nome;
	private String especializacao;
	private int numeroconselho;
	private boolean cadastrar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	public int getNumeroconselho() {
		return numeroconselho;
	}
	public void setNumeroconselho(int numeroconselho) {
		this.numeroconselho = numeroconselho;
	}
	public boolean isCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
}
