package br.com.dss.modelo;

public class Procedimento {

	private int id;
	private int tabela;
	private int procedimento;
	private String descricao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTabela() {
		return tabela;
	}
	public void setTabela(int tabela) {
		this.tabela = tabela;
	}
	public int getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(int procedimento) {
		this.procedimento = procedimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
