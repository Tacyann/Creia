package br.com.dss.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import br.com.dss.Conexao;
import br.com.dss.modelo.Arquivo;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Glosa;
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Lote;
import br.com.dss.modelo.Procedimento;

public class ArquivoDao {
	private Connection connection;

	public ArquivoDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	private boolean Adicionar(Arquivo arquivo) {
		boolean adicionou;
		String sql = "insert into arquivo(nomearquivo,dtimportacao) values(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, arquivo.getNomeArquivo());
			stmt.setDate(2, new Date(arquivo.getDtImportacao().getTimeInMillis()));

			stmt.execute();
			stmt.close();

			adicionou = true;
			return adicionou;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}

	public String Obter(String filtro) {
		String sql = "select * from arquivo where nomearquivo = '" + filtro + "'";		
		String nome = null;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				nome = rs.getString("nomearquivo");
			}
			stmt.close();
			rs.close();

			return nome;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public void TesteNodo() throws ParserConfigurationException, SAXException, IOException, JAXBException {
		File f = new File("D:/ArquivoXml"); 
		var auxDir = f.list();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		for(var arquivo : auxDir) {
			var extencaoArquivo = arquivo.substring(arquivo.indexOf('.')+1);

			if(extencaoArquivo.equals("xml")) {	
				String file = f.getPath() + "\\" + arquivo;
				Document doc = dBuilder.parse(file);

				NodeList nListLote = doc.getElementsByTagName("dadosProtocolo");

				for(int i = 0; i < nListLote.getLength(); i++) {
					Node nNode = nListLote.item(i);
					JAXBContext context = JAXBContext.newInstance(Lote.class);
					var lote = (Lote) context.createUnmarshaller().unmarshal(nNode);
					
					System.out.println(lote.getNumero());
				}
			}
		}
	}

	public List<Lote> LerXmlGerarLotes() throws Exception {	
		Calendar data = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		data.setTime(d);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Integer numeroLotePrestador = 0;
		Integer numeroProtocolo = 0;
		Calendar dataProtocolo = Calendar.getInstance();
		Integer situacaoProtocolo = 0;
		Integer numeroGuiaPrestador = 0;
		Integer numeroGuiaOperadora = 0;
		Integer senha = 0;
		String nomeBeneficiario = "";
		String numeroCarteira = "";
		Calendar dataInicioFat = Calendar.getInstance();
		Integer situacaoGuia = 0;
		Calendar dataRealizacao = Calendar.getInstance();
		Integer codigoTabela = 0;
		Integer codigoProcedimento = 0;
		String descricaoProcedimento = "";
		Integer grauParticipacao = 0;
		Double valorInformado = 0.0;
		Integer qtdExecutada = 0;
		Double valorProcessado = 0.0;
		Double valorLiberado = 0.0;
		Double valorInformadoGuia = 0.0;
		Double valorProcessadoGuia = 0.0;
		Double valorLiberadoGuia = 0.0;
		Double valorInformadoProtocolo = 0.0;
		Double valorProcessadoProtocolo = 0.0;
		Double valorLiberadoProtocolo = 0.0;
		String item = null;
		Integer codigoGlosa = 0;
		String descricaoGlosa = "";
//		Double valorGlosa = 0.0;
//		Integer tipoGlosa = 0;
		List<Lote> lotes = new ArrayList<Lote>();

		File f = new File("D:/ArquivoXml"); 
		var auxDir = f.list();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		for(var arquivo : auxDir) {
			Arquivo arq = new Arquivo();
			var extencaoArquivo = arquivo.substring(arquivo.indexOf('.')+1);

			if(extencaoArquivo.equals("xml")) {	
				item = Obter(arquivo);

				if(item == null) {
					String file = f.getPath() + "\\" + arquivo;
					Document doc = dBuilder.parse(file);

					NodeList nListLote = doc.getElementsByTagName("dadosProtocolo");

					for(int i = 0; i < nListLote.getLength(); i++) {
						Lote lote = new Lote();
						Node nNode = nListLote.item(i);
						List<Guia> guias = new ArrayList<Guia>();

						if(nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							numeroLotePrestador = Integer.parseInt(eElement.getElementsByTagName("numeroLotePrestador").item(0).getTextContent());
							numeroProtocolo = Integer.parseInt(eElement.getElementsByTagName("numeroProtocolo").item(0).getTextContent());
							var dt = formato.parse(eElement.getElementsByTagName("dataProtocolo").item(0).getTextContent());
							dataProtocolo.setTime(dt);
							situacaoProtocolo = Integer.parseInt(eElement.getElementsByTagName("situacaoProtocolo").item(0).getTextContent());	
							valorInformadoProtocolo = Double.parseDouble(eElement.getElementsByTagName("valorInformadoProtocolo").item(0).getTextContent());
							valorProcessadoProtocolo = Double.parseDouble(eElement.getElementsByTagName("valorProcessadoProtocolo").item(0).getTextContent());
							valorLiberadoProtocolo = Double.parseDouble(eElement.getElementsByTagName("valorLiberadoProtocolo").item(0).getTextContent());
						}

						NodeList nListGuias = doc.getElementsByTagName("relacaoGuias");

						for(int j = 0; j < nListGuias.getLength(); j++) {
							Node nodeGuia = nListGuias.item(j);
							Beneficiario beneficiario = new Beneficiario();
							Guia guia = new Guia();
							List<Glosa> glosas = new ArrayList<>();

							if(nodeGuia.getNodeType() == Node.ELEMENT_NODE) {
								Element eGuia = (Element) nodeGuia;

								numeroGuiaPrestador = Integer.parseInt(eGuia.getElementsByTagName("numeroGuiaPrestador").item(0).getTextContent());
								numeroGuiaOperadora = Integer.parseInt(eGuia.getElementsByTagName("numeroGuiaOperadora").item(0).getTextContent());
								senha = Integer.parseInt(eGuia.getElementsByTagName("senha").item(0).getTextContent());
								nomeBeneficiario = eGuia.getElementsByTagName("nomeBeneficiario").item(0).getTextContent();
								numeroCarteira = eGuia.getElementsByTagName("numeroCarteira").item(0).getTextContent();
								var dt = formato.parse(eGuia.getElementsByTagName("dataInicioFat").item(0).getTextContent());
								dataInicioFat.setTime(dt);
								situacaoGuia = Integer.parseInt(eGuia.getElementsByTagName("situacaoGuia").item(0).getTextContent());
								
								NodeList nListDetalhes = nodeGuia.getChildNodes();
								
								for(int g = 0; g < nListDetalhes.getLength(); g++) {
									Node nodeMotivoGlosa = nListDetalhes.item(g);
									if(nodeMotivoGlosa.getNodeType() == Node.ELEMENT_NODE) {
										Element eMotivoGlosa = (Element) nodeMotivoGlosa;
										
										switch (eMotivoGlosa.getTagName()) {
										case "motivoGlosaGuia":
											Glosa glosa = new Glosa();
											codigoGlosa = Integer.parseInt(eMotivoGlosa.getElementsByTagName("codigoGlosa").item(0).getTextContent());
											descricaoGlosa = eMotivoGlosa.getElementsByTagName("descricaoGlosa").item(0).getTextContent();
											
											glosa.setCodigo(codigoGlosa);
											glosa.setDescricao(descricaoGlosa);
											glosas.add(glosa);
											break;
										}
									}
								}
								
//								StringBuilder separador = new StringBuilder();
//								for(int s = 0; s < 100; s++) {
//									separador.append("-");
//								}
//								System.out.println(separador.toString());
//								System.out.println("Cod Glosa: " + codigoGlosa + ", Descrição: " + descricaoGlosa);
//								System.out.println("Nº prestador: " + numeroGuiaPrestador + ", Nº operadora: " + numeroGuiaOperadora);
//								System.out.println(nomeBeneficiario);

								List<DetalheGuia> detalhes = new ArrayList<DetalheGuia>();
								
								for(int k = 0; k < nListDetalhes.getLength(); k++) {
									Node nodeDetalhesGuia = nListDetalhes.item(k);
									DetalheGuia detalheGuia = new DetalheGuia();
									Procedimento procedimento = new Procedimento();

									if(nodeDetalhesGuia.getNodeType() == Node.ELEMENT_NODE) {
										Element eDetalhes = (Element) nodeDetalhesGuia;

										switch(eDetalhes.getTagName()) {
										case "detalhesGuia":
											
											dt = formato.parse(eDetalhes.getElementsByTagName("dataRealizacao").item(0).getTextContent());
											dataRealizacao.setTime(dt);
											codigoTabela = Integer.parseInt(eDetalhes.getElementsByTagName("codigoTabela").item(0).getTextContent());
											codigoProcedimento = Integer.parseInt(eDetalhes.getElementsByTagName("codigoProcedimento").item(0).getTextContent());
											descricaoProcedimento = eDetalhes.getElementsByTagName("descricaoProcedimento").item(0).getTextContent();
											grauParticipacao = Integer.parseInt(eDetalhes.getElementsByTagName("grauParticipacao").item(0).getTextContent());
											valorInformado = Double.parseDouble(eDetalhes.getElementsByTagName("valorInformado").item(0).getTextContent());
											qtdExecutada = Integer.parseInt(eDetalhes.getElementsByTagName("qtdExecutada").item(0).getTextContent());
											valorProcessado = Double.parseDouble(eDetalhes.getElementsByTagName("valorProcessado").item(0).getTextContent());
											valorLiberado = Double.parseDouble(eDetalhes.getElementsByTagName("valorLiberado").item(0).getTextContent());
											
											detalheGuia.setDataRealizacao(dataRealizacao);
											procedimento.setTabela(codigoTabela);
											procedimento.setProcedimento(codigoProcedimento);
											procedimento.setDescricao(descricaoProcedimento);
											detalheGuia.setProcedimento(procedimento);
											detalheGuia.setGrauParticipacao(grauParticipacao);
											detalheGuia.setValorInformado(valorInformado);
											detalheGuia.setQtdExecutada(qtdExecutada);
											detalheGuia.setValorProcessado(valorProcessado);
											detalheGuia.setValorLiberado(valorLiberado);
											
//											System.out.println("Procedimento: " + descricaoProcedimento);
//											System.out.println("Vlr Informado: " + valorInformado + ", Qtd Exec: " + qtdExecutada + ", Vlr Liberado: " + valorLiberado + ", Vlr Processado: " + valorProcessado);
											
											/**
											 * Trecho que atende o valor da Glosa, comentado aguardando o retorno do Sergio.
											 * */
//											NodeList nListDetalhesFliho = eDetalhes.getChildNodes();
//											for(int r = 0; r < nListDetalhesFliho.getLength(); r++) {
//												Node nodeRelacaoGlosa = nListDetalhesFliho.item(r);
//												
//												if(nodeRelacaoGlosa.getNodeType() == Node.ELEMENT_NODE) {
//													Element eRelacao = (Element) nodeRelacaoGlosa;
//													
//													switch (eRelacao.getTagName()) {
//													case "relacaoGlosa":
//														valorGlosa = Double.parseDouble(eRelacao.getElementsByTagName("valorGlosa").item(0).getTextContent());
//														tipoGlosa = Integer.parseInt(eRelacao.getElementsByTagName("tipoGlosa").item(0).getTextContent());
//														
//														glosa.setTipo(tipoGlosa);
//														glosa.setValor(valorGlosa);
//														
//														detalheGuia.setGlosa(glosa);
////														System.out.println("Vlr Glosa: " + valorGlosa + ", Tipo Glosa: " + tipoGlosa);
//														break;
//													}
//												}
//											}
											detalhes.add(detalheGuia);
											guia.setDetalheGuia(detalhes);
											break;
										}								
									}
								}

								valorInformadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorInformadoGuia").item(0).getTextContent());
								valorProcessadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorProcessadoGuia").item(0).getTextContent());
								valorLiberadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorLiberadoGuia").item(0).getTextContent());	
							}
							guia.setPrestador(numeroGuiaPrestador);
							guia.setOperadora(numeroGuiaOperadora);
							guia.setSenha(senha);
							beneficiario.setNome(nomeBeneficiario);
							beneficiario.setNumeroCarteira(numeroCarteira);
							guia.setBeneficiario(beneficiario);
							guia.setDataIni(dataInicioFat);
							guia.setGlosa(glosas);
							guia.setSituacaoGuia(situacaoGuia);
							guia.setValorInformadoGuia(valorInformadoGuia);
							guia.setValorProcessadoGuia(valorProcessadoGuia);
							guia.setValorLiberadoGuia(valorLiberadoGuia);
							guias.add(guia);

//							System.out.println("--------------");
//							System.out.println("Totais da Guia");
//							System.out.println("Vlr Inf Guia: " + valorInformadoGuia + ", Vlr Process Guia: " + valorProcessadoGuia + ", Vlr Lib Guia: " + valorLiberadoGuia);
						}

						lote.setGuia(guias);
						lote.setNumero(numeroLotePrestador);
						lote.setProtocolo(numeroProtocolo);
						lote.setData(dataProtocolo);
						lote.setSituacao(situacaoProtocolo);
						lote.setValorInformadoProtocolo(valorInformadoProtocolo);
						lote.setValorProcessadoProtocolo(valorProcessadoProtocolo);
						lote.setValorLiberadoProtocolo(valorLiberadoProtocolo);
						lotes.add(lote);	
					}			

					arq.setNomeArquivo(arquivo);
					arq.setDtImportacao(data);
					Adicionar(arq);
				}
			}
		}		

		return lotes;
	}
}
