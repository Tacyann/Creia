package br.com.dss.modelo;

import java.util.Calendar;
import java.util.List;

public class Lote {

	private int id;
	private Calendar dataEmissao;
	private int numero;
	private int protocolo;
	private Calendar data;
	private int situacao;
	private List<Guia> guia;
	private double valorInformadoProtocolo;
	private double valorProcessadoProtocolo;
	private double valorLiberadoProtocolo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
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
	public double getValorInformadoProtocolo() {
		return valorInformadoProtocolo;
	}
	public void setValorInformadoProtocolo(double valorInformadoProtocolo) {
		this.valorInformadoProtocolo = valorInformadoProtocolo;
	}
	public double getValorProcessadoProtocolo() {
		return valorProcessadoProtocolo;
	}
	public void setValorProcessadoProtocolo(double valorProcessadoProtocolo) {
		this.valorProcessadoProtocolo = valorProcessadoProtocolo;
	}
	public double getValorLiberadoProtocolo() {
		return valorLiberadoProtocolo;
	}
	public void setValorLiberadoProtocolo(double valorLiberadoProtocolo) {
		this.valorLiberadoProtocolo = valorLiberadoProtocolo;
	}
}
