package br.com.dss.modelo;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.dss.controle.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DetalheGuia {

	@XmlElement
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Calendar dataRealizacao;
	
	@XmlElement
	private Procedimento procedimento;
	
	@XmlElement
	private int grauParticipacao;
	
	@XmlElement
	private double valorInformado;
	
	@XmlElement
	private int qtdExecutada;
	
	@XmlElement
	private double valorProcessado;
	
	@XmlElement
	private double valorLiberado;
	
	@XmlElement(name="relacaoGlosa")
	private Glosa glosa;
	
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
