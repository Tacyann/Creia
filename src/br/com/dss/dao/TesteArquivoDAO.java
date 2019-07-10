package br.com.dss.dao;

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
//			
//			e.printStackTrace();
//		}
		Processar();
		//Testar();
	}
	
	public static void Testar() {
//		ArquivoDao arquivo = new ArquivoDao();
	}
	
	public static void Processar() {
		ArquivoDao arquivo = new ArquivoDao();
		LoteDao lote = new LoteDao();
		GuiaDao guia = new GuiaDao();
		GlosaDao glosa = new GlosaDao();
		DetalhesGuiaDao detalhe = new DetalhesGuiaDao();
		ProcedimentoDao procedimento = new ProcedimentoDao();
		BeneficiarioDao beneficiario = new BeneficiarioDao();

		try {
			var countLote = 0;
			var countGuia = 0;
			var countDetalhes = 0;
			var listaLote = arquivo.LerXmlGerarLotes();

			if(listaLote != null) {

				for(var itemLote : listaLote) {
					var isLote = lote.Existe(itemLote.getNumero());
					
					StringBuilder separador = new StringBuilder();
					for(int s = 0; s < 100; s++) {
						separador.append("-");
					}
					System.out.println("Nº Lote: " + itemLote.getNumero());
					
					countLote++;
					if(!isLote) {
						lote.Adicionar(itemLote);
						var listaGuia = itemLote.getGuia();
						for(var itemGuia : listaGuia) {
							guia.Adicionar(itemGuia,itemLote.getNumero());
							countGuia++;

							System.out.println(separador.toString());					
							System.out.println("Nº prestador: " + itemGuia.getPrestador());
							
							var listaGlosa = itemGuia.getGlosa();
							
							if(listaGlosa != null) {
								for(var itemGlosa : listaGlosa) {
									var isGlosa = glosa.Existe(itemGlosa);
									
									if(!isGlosa) {
										System.out.println("Glosa: " + itemGlosa.getDescricao());	
										glosa.Adicionar(itemGlosa);
									}
								}								
							}
							
							var isBeneficiario = beneficiario.Existe(itemGuia.getBeneficiario());

							if(!isBeneficiario) {
								beneficiario.Adicionar(itemGuia.getBeneficiario());
								System.out.println("Nome Beneficiario: " + itemGuia.getBeneficiario().getNome());
							}

							var listaDetalhes = itemGuia.getDetalheGuia();
							for(var itemDetalhes : listaDetalhes) {

								countDetalhes++;
								detalhe.Adicionar(itemDetalhes, itemGuia.getPrestador());
								System.out.println("Prestador: " + itemGuia.getPrestador() + ", Vlr Informado: R$ " + itemDetalhes.getValorInformado());

								var proced = itemDetalhes.getProcedimento();
								if(proced != null) {
									var isProcedimento = procedimento.Existe(itemDetalhes.getProcedimento().getProcedimento());

									if(!isProcedimento) {
										procedimento.Adicionar(itemDetalhes.getProcedimento());
										System.out.println("Procedimento: " + itemDetalhes.getProcedimento().getDescricao());
									}
								}
								
								var liberado = itemDetalhes.getValorLiberado();
								var vlrGlosa = 0.0;
								if(liberado == 0.0) {
									vlrGlosa = itemDetalhes.getValorInformado();
								}
								
								var msg = "Vlr Inf: R$ " + itemDetalhes.getValorInformado() + ", Qtd Exec: " + itemDetalhes.getQtdExecutada()
								+ ", Vlr Processado: R$ " + itemDetalhes.getValorProcessado() + ", Vlr Glosa: R$ " + vlrGlosa + ", Vlr Liberado: R$ " + liberado;
								System.out.println(msg);
							}
						}
					}
				}	
			}
			
			System.out.println("Qtd de lotes: " + countLote + ", Qtd de guias: " + countGuia + ", Qtd de detalhes: " + countDetalhes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
