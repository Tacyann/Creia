package br.com.dss.modelo;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dadosProtocolo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lote {

	private int id;
	
	@XmlElement(name="numeroLotePrestador")
	private int numero;
	
	@XmlElement(name="numeroProtocolo")
	private int protocolo;
	
	@XmlElement(name="dataProtocolo")
	private Calendar data;
	
	@XmlElement(name="situacaoProtocolo")
	private int situacao;
	
	@XmlElement(name="relacaoGuias")
	private List<Guia> guia;
	
	@XmlElement
	private double valorInformadoProtocolo;
	@XmlElement
	private double valorProcessadoProtocolo;
	@XmlElement
	private double valorLiberadoProtocolo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
