package br.com.dss.argument;

import java.util.Calendar;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;

public class GuiaArgument {

	private int prestador;
	private int operadora;
	private Beneficiario beneficiario;
	private Calendar dataIni;
	private DetalheGuia detalheGuia;
	public int getPrestador() {
		return prestador;
	}
	public void setPrestador(int prestador) {
		this.prestador = prestador;
	}
	public int getOperadora() {
		return operadora;
	}
	public void setOperadora(int operadora) {
		this.operadora = operadora;
	}
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Calendar getDataIni() {
		return dataIni;
	}
	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}
	public DetalheGuia getDetalheGuia() {
		return detalheGuia;
	}
	public void setDetalheGuia(DetalheGuia detalheGuia) {
		this.detalheGuia = detalheGuia;
	}
}
