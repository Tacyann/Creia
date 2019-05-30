package br.com.dss.modelo;

import java.util.Calendar;

public class Guia {

	private int prestador;
	private int operadora;
	private int senha;
	private String beneficiario;
	private String carteira;
	private Calendar dataIni;
	private int situacao;
	private Calendar dataRealizacao;
	private int procedimento;
	private double valorInformado;
	private double valorProcessado;
	private double valorLiberado;
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
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getCarteira() {
		return carteira;
	}
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	public Calendar getDataIni() {
		return dataIni;
	}
	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	public Calendar getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Calendar dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public int getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(int procedimento) {
		this.procedimento = procedimento;
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
	public DetalheGuia getDetalheGuia() {
		return detalheGuia;
	}
	public void setDetalheGuia(DetalheGuia detalheGuia) {
		this.detalheGuia = detalheGuia;
	}
}
