package br.com.dss.util;

public class RelatorioProcedimento {

	private Integer codProcedimento;
	private String nomeProcedimento;
	private String clientes;
	private Integer quantidade;
	private Double vlrInformado;
	private Double vlrGlosa;
	private Double vlrProcessado;
	private Double vlrLiberado;
	
	public Integer getCodProcedimento() {
		return codProcedimento;
	}
	public void setCodProcedimento(Integer codProcedimento) {
		this.codProcedimento = codProcedimento;
	}
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	public String getClientes() {
		return clientes;
	}
	public void setClientes(String clientes) {
		this.clientes = clientes;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getVlrInformado() {
		return vlrInformado;
	}
	public void setVlrInformado(Double vlrInformado) {
		this.vlrInformado = vlrInformado;
	}
	public Double getVlrGlosa() {
		return vlrGlosa;
	}
	public void setVlrGlosa(Double vlrGlosa) {
		this.vlrGlosa = vlrGlosa;
	}
	public Double getVlrProcessado() {
		return vlrProcessado;
	}
	public void setVlrProcessado(Double vlrProcessado) {
		this.vlrProcessado = vlrProcessado;
	}
	public Double getVlrLiberado() {
		return vlrLiberado;
	}
	public void setVlrLiberado(Double vlrLiberado) {
		this.vlrLiberado = vlrLiberado;
	}
}
