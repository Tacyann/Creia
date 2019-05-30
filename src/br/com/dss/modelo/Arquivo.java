package br.com.dss.modelo;

import java.util.Calendar;

public class Arquivo {

	private Integer Id;
	private String NomeArquivo;
	private String Xml;
	private boolean Processado;
	private Calendar DtImportacao;
	
	public Integer getId() {
		return Id;
	}
	public String getNomeArquivo() {
		return NomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		NomeArquivo = nomeArquivo;
	}
	public String getXml() {
		return Xml;
	}
	public void setXml(String xml) {
		Xml = xml;
	}
	public boolean isProcessado() {
		return Processado;
	}
	public void setProcessado(boolean processado) {
		Processado = processado;
	}
	public Calendar getDtImportacao() {
		return DtImportacao;
	}
	public void setDtImportacao(Calendar dtImportacao) {
		DtImportacao = dtImportacao;
	}
}
