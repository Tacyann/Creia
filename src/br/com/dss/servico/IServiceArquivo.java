package br.com.dss.servico;

import java.util.List;

import br.com.dss.modelo.Lote;

public interface IServiceArquivo {

	public List<Lote> getLotes();
	public boolean isExistArquivo();
	public void setLote(Lote lote);
}
