package br.com.dss.argument;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.Procedimento;

public class ClienteArgument {

	private Beneficiario beneficiario;
	private Procedimento procedimento;
	private Double valorInformado;
	private Double valorLiberado;
	private Double valorProcessado;
	private Double valorGlosa;
	
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
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
