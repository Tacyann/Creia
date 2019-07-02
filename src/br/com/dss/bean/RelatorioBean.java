package br.com.dss.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;

@Named("relatorio")
@RequestScoped
public class RelatorioBean implements Serializable{
	
	private List<GuiaArgument> guias;
	private List<Double> valoresLiberados;
	private List<Double> valoresGlosa;
	private String[] clientes;
	private String[] descricao;
	private String profissionais;
	private Double valorTotal;
	private Double valorGlosa;
	private Double valorCreia;
	private Double valorProfissional;
	public List<GuiaArgument> getGuias() {
		return guias;
	}
	public void setGuias(List<GuiaArgument> guias) {
		this.guias = guias;
	}
	public List<Double> getValoresLiberados() {
		return valoresLiberados;
	}
	public void setValoresLiberados(List<Double> valoresLiberados) {
		this.valoresLiberados = valoresLiberados;
	}
	public List<Double> getValoresGlosa() {
		return valoresGlosa;
	}
	public void setValoresGlosa(List<Double> valoresGlosa) {
		this.valoresGlosa = valoresGlosa;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
	public String[] getDescricao() {
		return descricao;
	}
	public void setDescricao(String[] descricao) {
		this.descricao = descricao;
	}
	public String getProfissionais() {
		return profissionais;
	}
	public void setProfissionais(String profissionais) {
		this.profissionais = profissionais;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getValorGlosa() {
		return valorGlosa;
	}
	public void setValorGlosa(Double valorGlosa) {
		this.valorGlosa = valorGlosa;
	}
	public Double getValorCreia() {
		return valorCreia;
	}
	public void setValorCreia(Double valorCreia) {
		this.valorCreia = valorCreia;
	}
	public Double getValorProfissional() {
		return valorProfissional;
	}
	public void setValorProfissional(Double valorProfissional) {
		this.valorProfissional = valorProfissional;
	}
}
