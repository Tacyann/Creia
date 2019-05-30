package br.com.dss.servico;

import java.util.List;

import br.com.dss.modelo.Beneficiario;

public interface IServiceBeneficiario {

	public List<Beneficiario> getLotes();
	public boolean isExistBeneficiario();
	public void setLote(Beneficiario beneficiario);
}
