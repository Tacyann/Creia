package br.com.dss.servico;

import java.util.List;

import br.com.dss.modelo.Procedimento;

public interface IServiceProcedimento {

	public List<Procedimento> getLotes();
	public boolean isExistProcedimento();
	public void setLote(Procedimento beneficiario);
}
