package br.com.dss.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.as.controller.client.impl.InputStreamEntry.FileStreamEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.dss.Conexao;
import br.com.dss.modelo.Arquivo;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
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
	
	public List<Lote> Teste(){
		File f = new File("D:/ArquivoXml"); 
		var auxDir = f.list();
		
		for(var arquivo : auxDir) {
			FileReader arq;
			try {
				var extencaoArquivo = arquivo.substring(arquivo.indexOf('.')+1);

				if(extencaoArquivo.equals("xml")) {	
					arq = new FileReader(f.getPath() + "\\" + arquivo);
					BufferedReader br = new BufferedReader(arq);
					String linha = br.readLine();
					
					while(linha != null) {
						System.out.println(linha);
						linha = br.readLine();
					}
					
					arq.close();					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return null;
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
		List<Lote> lotes = new ArrayList<Lote>();
		List<DetalheGuia> detalhes = new ArrayList<DetalheGuia>();

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

								NodeList listaFilhosGuia = eGuia.getChildNodes();
								
								/*
								 * Teste
								 * */
								System.out.println(listaFilhosGuia.getLength());
								
								for(int k = 0; k < listaFilhosGuia.getLength(); k++) {
									Node nodeFilhosGuia = listaFilhosGuia.item(k);
									DetalheGuia detalheGuia = new DetalheGuia();
									Procedimento procedimento = new Procedimento();

									if(nodeFilhosGuia.getNodeType() == Node.ELEMENT_NODE) {
										Element eDetalhes = (Element) nodeFilhosGuia;

										switch(eDetalhes.getTagName()) {
										case "detalhesGuia":
											//NodeList nodeDetalhes = eFilhosGuia.getElementsByTagName("detalhesGuia").item(k);
											System.out.println();
											
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
											break;
										}
									}
									detalheGuia.setDataRealizacao(dataRealizacao);
									procedimento.setTabela(codigoTabela);
									procedimento.setProcedimento(codigoProcedimento);
									procedimento.setDescricao(descricaoProcedimento);
									detalheGuia.setProcedimento(procedimento);
									detalheGuia.setGrauParticipacao(grauParticipacao);
									detalheGuia.setValorInformadoGuia(valorInformado);
									detalheGuia.setQtdExecutada(qtdExecutada);
									detalheGuia.setValorProcessadoGuia(valorProcessado);
									detalheGuia.setValorLiberadoGuia(valorLiberado);

									detalhes.add(detalheGuia);
								}

								valorInformadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorInformadoGuia").item(0).getTextContent());
								valorProcessadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorProcessadoGuia").item(0).getTextContent());
								valorLiberadoGuia = Double.parseDouble(eGuia.getElementsByTagName("valorLiberadoGuia").item(0).getTextContent());
							}
							guia.setPrestador(numeroGuiaPrestador);
							guia.setOperadora(numeroGuiaOperadora);
							guia.setSenha(senha);
							beneficiario.setNome(nomeBeneficiario);
							beneficiario.setNumerocarteira(numeroCarteira);
							guia.setBeneficiario(beneficiario);
							guia.setDataIni(dataInicioFat);
							guia.setSituacaoGuia(situacaoGuia);
							guia.setDetalheGuia(detalhes);
							guia.setValorInformadoGuia(valorInformadoGuia);
							guia.setValorProcessadoGuia(valorProcessadoGuia);
							guia.setValorLiberadoGuia(valorLiberadoGuia);
							guias.add(guia);
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
