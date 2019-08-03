package br.com.dss.servico;

import br.com.dss.dao.ArquivoDao;
import br.com.dss.dao.BeneficiarioDao;
import br.com.dss.dao.DetalhesGuiaDao;
import br.com.dss.dao.GuiaDao;
import br.com.dss.dao.LoteDao;
import br.com.dss.dao.ProcedimentoDao;

public class ServiceArquivo implements IDados, IAdiciona {

	@Override
	public boolean isExist(Object filtro) {
		ArquivoDao arquivo = new ArquivoDao();
		Processar(arquivo);

		var existeArquivo = arquivo.Obter((String) filtro);

		if(existeArquivo != null) {
			return true;
		}

		return false;
	}

	private boolean Processar(ArquivoDao arquivo) {

		boolean importar = false;
		LoteDao lote = new LoteDao();
		GuiaDao guia = new GuiaDao();
		DetalhesGuiaDao detalhe = new DetalhesGuiaDao();
		ProcedimentoDao procedimento = new ProcedimentoDao();
		BeneficiarioDao beneficiario = new BeneficiarioDao();

		try {
			var countLote = 0;
			var countGuia = 0;
			var listaLote = arquivo.LerXmlGerarLotes();

			if(listaLote != null) {

				for(var itemLote : listaLote) {
					var isLote = lote.Existe(itemLote.getNumero());
					countLote++;
					if(!isLote) {
						lote.Adicionar(itemLote);

						for(var itemGuia : itemLote.getGuia()) {
							guia.Adicionar(itemGuia,itemLote.getNumero());
							countGuia++;

							var isBeneficiario = beneficiario.Existe(itemGuia.getBeneficiario());

							if(!isBeneficiario) {
								beneficiario.Adicionar(itemGuia.getBeneficiario());
							}

							for(var itemDetalhes : itemGuia.getDetalheGuia()) {

								detalhe.Adicionar(itemDetalhes, itemGuia.getPrestador());

								if(itemDetalhes.getProcedimento().getProcedimento() != 0) {
									var isProcedimento = procedimento.Existe(itemDetalhes.getProcedimento().getProcedimento());

									if(!isProcedimento) {
										procedimento.Adicionar(itemDetalhes.getProcedimento());
									}
								}
							}
						}
					}
				}	

				System.out.println("No ServiceArquivo Qtd de lotes: " + countLote + " Qtd de guias: " + countGuia);
				if(countLote > 0) {
					importar = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return importar;
	}

	@Override
	public boolean adicionar(Object objeto) {
		ArquivoDao arquivo = new ArquivoDao();
		var add = Processar(arquivo);

		if(add) {
			return true;
		}

		return false;
	}
}
