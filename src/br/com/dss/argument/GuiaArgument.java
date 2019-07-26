package br.com.dss.argument;

import java.util.Calendar;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Procedimento;

public class GuiaArgument {

	private int prestador;
	private int operadora;
	private Beneficiario beneficiario;
	private Procedimento procedimento;
	private Calendar dataIni;
	private Calendar dataRealizacao;
	private double valorInformado;
	private double valorProcessado;
	private double valorLiberado;
	private Integer qtdExecutada;
	private Integer codProced;
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
	public double getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(double valorInformado) {
		this.valorInformado = valorInformado;
	}
	public double getValorProcessado() {
		return valorProcessado;
	}
	public void setValorProcessado(double valorProcessado) {
		this.valorProcessado = valorProcessado;
	}
	public double getValorLiberado() {
		return valorLiberado;
	}
	public void setValorLiberado(double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}
	public Integer getQtdExecutada() {
		return qtdExecutada;
	}
	public void setQtdExecutada(Integer qtdExecutada) {
		this.qtdExecutada = qtdExecutada;
	}
	public Integer getCodProced() {
		return codProced;
	}
	public void setCodProced(Integer codProced) {
		this.codProced = codProced;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	public Calendar getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Calendar dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
}
