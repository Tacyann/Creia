package br.com.dss.modelo;

public class Procedimento {

	private Integer id;
	private Integer tabela;
	private int procedimento;
	private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTabela() {
		return tabela;
	}
	public void setTabela(Integer tabela) {
		this.tabela = tabela;
	}
	public Integer getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Integer procedimento) {
		this.procedimento = procedimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
