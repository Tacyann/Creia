package br.com.dss.util;

public class Relatorio {

	private String nomeProcedimento;
	private Integer quantidade;
	private Double valorInformado;
	private Double valorGlosa;
	private Double valorProcessado;
	private Double valorLiberado;
	private String periodo;
	
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(Double valorInformado) {
		this.valorInformado = valorInformado;
	}
	public Double getValorGlosa() {
		return valorGlosa;
	}
	public void setValorGlosa(Double valorGlosa) {
		this.valorGlosa = valorGlosa;
	}
	public Double getValorProcessado() {
		return valorProcessado;
	}
	public void setValorProcessado(Double valorProcessado) {
		this.valorProcessado = valorProcessado;
	}
	public Double getValorLiberado() {
		return valorLiberado;
	}
	public void setValorLiberado(Double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Override
	public String toString() {
		return nomeProcedimento + " " + quantidade + " " + valorInformado + " " + valorGlosa + " " + valorProcessado + " " + valorLiberado + " " + periodo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Relatorio)) {
			return false;
		}
		
		Relatorio other = (Relatorio) obj;
		
		if(this.nomeProcedimento == other.nomeProcedimento) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
}
