package br.com.dss.dao;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TesteArquivoDAO {

	public static void main(String[] args) {
//		ArquivoDao arquivo = new ArquivoDao();
		
//		try {
//			var lista = arquivo.LerXmlGerarLotes();
//			
//			for(var lote : lista) {
//				System.out.println("Numero do lote: " + lote.getNumero());
//				var listaGuia = lote.getGuia();
//				for(var guia : listaGuia) {
//					System.out.println("Nome do Cliente: "+guia.getBeneficiario().getNome());
//					var listaDetalhes = guia.getDetalheGuia();
//					for(var detalhe : listaDetalhes) {
//						var data = detalhe.getDataRealizacao().getTimeInMillis();
//						var tabela = detalhe.getProcedimento().getTabela();
//						var codigo = detalhe.getProcedimento().getProcedimento();
//						var descricao = detalhe.getProcedimento().getDescricao();
//						System.out.println("DtRealização: "+ data +", Tabela: " + tabela + ", Código: " + codigo + ", Descrição: " + descricao);
//					}
//				}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Processar();
		//Testar();
	}
	
	public static void Testar() {
		ArquivoDao arquivo = new ArquivoDao();
		//arquivo.TesteStream();
		//arquivo.Teste();
		try {
			arquivo.TesteNodo();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Processar() {
		ArquivoDao arquivo = new ArquivoDao();
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
						//lote.Adicionar(itemLote);
						var listaGuia = itemLote.getGuia();
						for(var itemGuia : listaGuia) {
							//guia.Adicionar(itemGuia,itemLote.getNumero());
							countGuia++;

							var isBeneficiario = beneficiario.Existe(itemGuia.getBeneficiario());

							if(!isBeneficiario) {
								//beneficiario.Adicionar(itemGuia.getBeneficiario());
								System.out.println("Nome Beneficiario: " + itemGuia.getBeneficiario().getNome());
							}

							var listaDetalhes = itemGuia.getDetalheGuia();
							for(var itemDetalhes : listaDetalhes) {

								//detalhe.Adicionar(itemDetalhes, itemGuia.getPrestador());
								System.out.println("Prestador: " + itemGuia.getPrestador() + ", ValorInformado: R$ " + itemDetalhes.getValorInformado());

								if(itemDetalhes.getProcedimento().getProcedimento() != 0) {
									var isProcedimento = procedimento.Existe(itemDetalhes.getProcedimento().getProcedimento());

									if(!isProcedimento) {
										//procedimento.Adicionar(itemDetalhes.getProcedimento());
										System.out.println(itemDetalhes.getProcedimento().getProcedimento());
									}
								}
							}
						}
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
