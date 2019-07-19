package br.com.dss.modelo;

public class Profissional {

	private int id;
	private String nome;
	private String especializacao;
	private String numeroconselho;
	private boolean editar;
	
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
	public String getNumeroconselho() {
		return numeroconselho;
	}
	public void setNumeroconselho(String numeroconselho) {
		this.numeroconselho = numeroconselho;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	@Override
	public String toString() {
		return "Id:" + getId() + "Nome: " + getNome() + ", Especialização: " + getEspecializacao() + ", Nº Conselho: " + getNumeroconselho();
	}
}
