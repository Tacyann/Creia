package br.com.dss.modelo;

import java.util.Calendar;
import java.util.List;

public class Guia{

	private int id;
	private Lote lote;
	private int prestador;
	private int operadora;
	private int senha;
	private Beneficiario beneficiario;
	private Calendar dataIni;
	private int situacaoGuia;
	private double valorInformadoGuia;
	private double valorProcessadoGuia;
	private double valorLiberadoGuia;
	private List<Glosa> glosa;
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
	public List<Glosa> getGlosa() {
		return glosa;
	}
	public void setGlosa(List<Glosa> glosa) {
		this.glosa = glosa;
	}
}
