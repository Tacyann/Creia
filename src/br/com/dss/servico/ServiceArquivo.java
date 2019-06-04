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
				
					for(var itemGuia : itemLote.getGuia()) {
						beneficiario.Adicionar(itemGuia.getBeneficiario());
						guia.Adicionar(itemGuia);
						detalhe.Adicionar(itemGuia.getDetalheGuia());
						procedimento.Adicionar(itemGuia.getDetalheGuia().getProcedimento());
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
