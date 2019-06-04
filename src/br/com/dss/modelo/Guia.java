package br.com.dss.modelo;

import java.util.Calendar;

public class Guia extends Lote{

	private int id;
	private int prestador;
	private int operadora;
	private int senha;
	private Beneficiario beneficiario;
	private Calendar dataIni;
	private int situacao;
	private double valorInformado;
	private double valorProcessado;
	private double valorLiberado;
	private DetalheGuia detalheGuia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
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
