package br.com.dss.modelo;

import java.util.Calendar;
import java.util.List;

public class Lote {

	private int id;
	private int numero;
	private int protocolo;
	private Calendar data;
	private int situacao;
	private List<Guia> guia;
	private double valorInformado;
	private double valorProcessado;
	private double valorLiberado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	public List<Guia> getGuia() {
		return guia;
	}
	public void setGuia(List<Guia> guia) {
		this.guia = guia;
	}
}
