package br.com.dss.modelo;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.dss.controle.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Guia{

	private int id;
	private Lote lote;
	@XmlElement(name="numeroGuiaPrestador")
	private int prestador;
	
	@XmlElement(name="numeroGuiaOperadora")
	private int operadora;
	
	@XmlElement
	private int senha;
	
	private Beneficiario beneficiario;
	
	@XmlElement(name="dataInicioFat")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Calendar dataIni;
	
	@XmlElement
	private int situacaoGuia;
	
	@XmlElement
	private double valorInformadoGuia;
	
	@XmlElement
	private double valorProcessadoGuia;
	
	@XmlElement
	private double valorLiberadoGuia;
	
	@XmlElement(name="detalhesGuia")
	private List<DetalheGuia> detalheGuia;
	
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
	public int getSituacaoGuia() {
		return situacaoGuia;
	}
	public void setSituacaoGuia(int situacaoGuia) {
		this.situacaoGuia = situacaoGuia;
	}
	public double getValorInformadoGuia() {
		return valorInformadoGuia;
	}
	public void setValorInformadoGuia(double valorInformadoGuia) {
		this.valorInformadoGuia = valorInformadoGuia;
	}
	public double getValorProcessadoGuia() {
		return valorProcessadoGuia;
	}
	public void setValorProcessadoGuia(double valorProcessadoGuia) {
		this.valorProcessadoGuia = valorProcessadoGuia;
	}
	public double getValorLiberadoGuia() {
		return valorLiberadoGuia;
	}
	public void setValorLiberadoGuia(double valorLiberadoGuia) {
		this.valorLiberadoGuia = valorLiberadoGuia;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public List<DetalheGuia> getDetalheGuia() {
		return detalheGuia;
	}
	public void setDetalheGuia(List<DetalheGuia> detalheGuia) {
		this.detalheGuia = detalheGuia;
	}
}
