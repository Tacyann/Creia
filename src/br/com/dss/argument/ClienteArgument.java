package br.com.dss.argument;

public class ClienteArgument {

	private String beneficiario;
	private String procedimento;
	private Integer quantidade;
	private Double valorInformado;
	private Double valorLiberado;
	private Double valorProcessado;
	private Double valorGlosa;
	
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
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
	public Double getValorLiberado() {
		return valorLiberado;
	}
	public void setValorLiberado(Double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}
	public Double getValorProcessado() {
		return valorProcessado;
	}
	public void setValorProcessado(Double valorProcessado) {
		this.valorProcessado = valorProcessado;
	}
	public Double getValorGlosa() {
		return valorGlosa;
	}
	public void setValorGlosa(Double valorGlosa) {
		this.valorGlosa = valorGlosa;
	}
}
