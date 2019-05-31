package br.com.dss.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.dss.Conexao;
import br.com.dss.modelo.Arquivo;
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
		String sql = "insert into arquivo(nomearquivo,xml,dtimportacao)"+
				"values(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, arquivo.getNomeArquivo());
			stmt.setString(2, arquivo.getXml());
			stmt.setDate(3, new Date(arquivo.getDtImportacao().getTimeInMillis()));

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

	public List<Lote> ListarLotes() throws Exception {		
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
		String grauParticipacao = "";
		Double valorInformado = 0.0;
		Integer qtdExecutada = 0;
		Double valorProcessado = 0.0;
		Double valorLiberado = 0.0;
		Double valorInformadoGuia = 0.0;
		Double valorProcessadoGuia = 0.0;
		Double valorLiberadoGuia = 0.0;
		String item = null;
		List<Lote> lotes = new ArrayList<Lote>();
		List<Guia> guias = new ArrayList<Guia>();
		DetalheGuia detalheGuia = new DetalheGuia();
		Procedimento procedimento = new Procedimento();

		File f = new File("D:/ArquivoXml");
		var auxDir = f.list();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		for(var arquivo : auxDir) {
			item = Obter(arquivo);

			if(item == null) {
				String file = f.getPath() + "\\" + arquivo;
				Document doc = dBuilder.parse(file);


				NodeList nListGuias = doc.getElementsByTagName("relacaoGuias");

				for(int i = 0; i < nListGuias.getLength(); i++) {
					Node nNode = nListGuias.item(i);
					Guia guia = new Guia();

					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;

						numeroGuiaPrestador = Integer.parseInt(eElement.getElementsByTagName("numeroGuiaPrestador").item(0).getTextContent());
						numeroGuiaOperadora = Integer.parseInt(eElement.getElementsByTagName("numeroGuiaOperadora").item(0).getTextContent());
						senha = Integer.parseInt(eElement.getElementsByTagName("senha").item(0).getTextContent());
						nomeBeneficiario = eElement.getElementsByTagName("nomeBeneficiario").item(0).getTextContent();
						numeroCarteira = eElement.getElementsByTagName("numeroCarteira").item(0).getTextContent();
						var dt = formato.parse(eElement.getElementsByTagName("dataInicioFat").item(0).getTextContent());
						dataInicioFat.setTime(dt);
						situacaoGuia = Integer.parseInt(eElement.getElementsByTagName("situacaoGuia").item(0).getTextContent());
						dt = formato.parse(eElement.getElementsByTagName("dataRealizacao").item(0).getTextContent());
						dataRealizacao.setTime(dt);
						codigoTabela = Integer.parseInt(eElement.getElementsByTagName("codigoTabela").item(0).getTextContent());
						codigoProcedimento = Integer.parseInt(eElement.getElementsByTagName("codigoProcedimento").item(0).getTextContent());
						descricaoProcedimento = eElement.getElementsByTagName("descricaoProcedimento").item(0).getTextContent();
						grauParticipacao = eElement.getElementsByTagName("grauParticipacao").item(0).getTextContent();
						valorInformado = Double.parseDouble(eElement.getElementsByTagName("valorInformado").item(0).getTextContent());
						qtdExecutada = Integer.parseInt(eElement.getElementsByTagName("qtdExecutada").item(0).getTextContent());
						valorProcessado = Double.parseDouble(eElement.getElementsByTagName("valorProcessado").item(0).getTextContent());
						valorLiberado = Double.parseDouble(eElement.getElementsByTagName("valorProcessado").item(0).getTextContent());
						valorInformadoGuia = Double.parseDouble(eElement.getElementsByTagName("valorInformadoGuia").item(0).getTextContent());
						valorProcessadoGuia = Double.parseDouble(eElement.getElementsByTagName("valorProcessadoGuia").item(0).getTextContent());
						valorLiberadoGuia = Double.parseDouble(eElement.getElementsByTagName("valorLiberadoGuia").item(0).getTextContent());
					}
					guia.setPrestador(numeroGuiaPrestador);
					guia.setOperadora(numeroGuiaOperadora);
					guia.setSenha(senha);
					guia.setBeneficiario(nomeBeneficiario);
					guia.setCarteira(numeroCarteira);
					guia.setDataIni(dataInicioFat);
					guia.setSituacao(situacaoGuia);
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
					guia.setDetalheGuia(detalheGuia);
					guia.setValorInformado(valorInformadoGuia);
					guia.setValorProcessado(valorProcessadoGuia);
					guia.setValorLiberado(valorLiberadoGuia);
					guias.add(guia);
				}
				
				NodeList nListLote = doc.getElementsByTagName("dadosProtocolo");
				
				for(int i = 0; i < nListLote.getLength(); i++) {
					Lote lote = new Lote();
					Node nNode = nListLote.item(i);
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						numeroLotePrestador = Integer.parseInt(eElement.getElementsByTagName("numeroLotePrestador").item(0).getTextContent());
						numeroProtocolo = Integer.parseInt(eElement.getElementsByTagName("numeroProtocolo").item(0).getTextContent());
						var dt = formato.parse(eElement.getElementsByTagName("dataProtocolo").item(0).getTextContent());
						dataProtocolo.setTime(dt);
						situacaoProtocolo = Integer.parseInt(eElement.getElementsByTagName("situacaoProtocolo").item(0).getTextContent());						
					}
					
					lote.setNumero(numeroLotePrestador);
					lote.setProtocolo(numeroProtocolo);
					lote.setData(dataProtocolo);
					lote.setSituacao(situacaoProtocolo);
					lote.setGuia(guias);
					lotes.add(lote);
				}
			}
		}

		return lotes;
	}

	public void LerGravaArquivo() {
		String item = null;
		File f = new File("D:/ArquivoXml");
		var auxDir = f.list();
		Calendar data = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		String linha;
		data.setTime(d);

		Arquivo c = new Arquivo();

		try {
			for(var arquivo : auxDir) {
				item = Obter(arquivo);

				if(item == null) {
					String file = f.getPath() + "\\" + arquivo;
					FileReader arq = new FileReader(file);
					BufferedReader lerArq = new BufferedReader(arq);
					StringBuilder sb = new StringBuilder();
					String aux = lerArq.readLine();

					while(aux != null) {
						aux = lerArq.readLine();
						if(aux!= null) {
							sb.append(aux);
						}
					}
					linha = sb.toString();

					c.setNomeArquivo(arquivo);
					c.setXml(linha);
					c.setDtImportacao(data);
					Adicionar(c);

					lerArq.close();
				}
			}
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
