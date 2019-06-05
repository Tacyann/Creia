package br.com.dss.servico;

import br.com.dss.dao.ArquivoDao;
import br.com.dss.dao.BeneficiarioDao;
import br.com.dss.dao.DetalhesGuiaDao;
import br.com.dss.dao.GuiaDao;
import br.com.dss.dao.LoteDao;
import br.com.dss.dao.ProcedimentoDao;

public class ServiceArquivo implements IDados {

	@Override
	public boolean isExist(String filtro) {
		ArquivoDao arquivo = new ArquivoDao();
		Processar(arquivo);

		var existeArquivo = arquivo.Obter(filtro);

		if(existeArquivo != null) {
			return true;
		}

		return false;
	}

	private void Processar(ArquivoDao arquivo) {

		LoteDao lote = new LoteDao();
		GuiaDao guia = new GuiaDao();
		DetalhesGuiaDao detalhe = new DetalhesGuiaDao();
		ProcedimentoDao procedimento = new ProcedimentoDao();
		BeneficiarioDao beneficiario = new BeneficiarioDao();

		try {
			var listaLote = arquivo.LerXmlGerarLotes();

			if(listaLote != null) {

				for(var itemLote : listaLote) {
					lote.Adicionar(itemLote);
					var num = itemLote.getNumero();

					for(var itemGuia : itemLote.getGuia()) {
						guia.Adicionar(itemGuia, num);
						
						var isBeneficiario = beneficiario.Existe(itemGuia.getBeneficiario());
						
						if(!isBeneficiario) {
							beneficiario.Adicionar(itemGuia.getBeneficiario());
						}
						
						detalhe.Adicionar(itemGuia.getDetalheGuia(), itemGuia.getPrestador());

						var isProcedimento = procedimento.Existe(itemGuia.getDetalheGuia().getProcedimento().getProcedimento());

						if(!isProcedimento) {
							procedimento.Adicionar(itemGuia.getDetalheGuia().getProcedimento());
						}
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
