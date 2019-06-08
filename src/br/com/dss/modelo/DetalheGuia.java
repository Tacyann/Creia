package br.com.dss.modelo;

import java.util.Calendar;

public class DetalheGuia extends Guia{

	private Calendar dataRealizacao;
	private Procedimento procedimento;
	private int grauParticipacao;
	private double valorInformado;
	private int qtdExecutada;
	private double valorProcessado;
	private double valorLiberado;
	public Calendar getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Calendar dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	public int getGrauParticipacao() {
		return grauParticipacao;
	}
	public void setGrauParticipacao(int grauParticipacao) {
		this.grauParticipacao = grauParticipacao;
	}
	public double getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(double valorInformado) {
		this.valorInformado = valorInformado;
	}
	public int getQtdExecutada() {
		return qtdExecutada;
	}
	public void setQtdExecutada(int qtdExecutada) {
		this.qtdExecutada = qtdExecutada;
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
}
