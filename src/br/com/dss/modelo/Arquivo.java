package br.com.dss.modelo;

import java.util.Calendar;

public class Arquivo {

	private Integer Id;
	private String NomeArquivo;
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
