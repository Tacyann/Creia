package br.com.dss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Procedimento;

public class GuiaDao {
	private Connection connection;

	public GuiaDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Guia guia, int numero) {
		boolean adicionou;
		String sql = "insert into guia(lote,numeroGuiaPrestador,numeroGuiaOperadora,senha,nomeBeneficiario,numeroCarteira,dataInicioFat,situacaoGuia,valorInformadoGuia,valorProcessadoGuia,valorLiberadoGuia)"+
				"values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, numero);
			stmt.setInt(2, guia.getPrestador());
			stmt.setInt(3, guia.getOperadora());
			stmt.setInt(4, guia.getSenha());
			stmt.setString(5, guia.getBeneficiario().getNome());
			stmt.setString(6, guia.getBeneficiario().getNumeroCarteira());
			stmt.setDate(7, new Date(guia.getDataIni().getTimeInMillis()));
			stmt.setInt(8, guia.getSituacaoGuia());
			stmt.setDouble(9, guia.getValorInformadoGuia());
			stmt.setDouble(10, guia.getValorProcessadoGuia());
			stmt.setDouble(11, guia.getValorLiberadoGuia());

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

	public List<Guia> Obter(String[] listaCliente, String[] listaProcedimento, Date dtIni, Date dtFim) {
		String sql;
		
		if(listaCliente.length == 0 && listaProcedimento.length == 0) {
			sql = "SELECT DISTINCT a.*, b.numPrestador, b.procedimento, c.descricaoProcedimento, b.valorInformado, b.qtdExecutada, b.valorProcessado, b.valorLiberado FROM guia a "
					+"JOIN detalhesguia b ON a.numeroGuiaPrestador = b.numPrestador "
					+"AND b.dataRealizacao >= '" + dtIni + "' AND b.dataRealizacao <= '" + dtFim + "'"
					+"JOIN procedimento c ON b.procedimento = c.codigoProcedimento";
		}else {
			int count = 0;
			StringBuilder sbCliente = new StringBuilder();
			for(var cliente : listaCliente) {
				sbCliente.append("'").append(cliente).append("'");
				count++;
				if(listaCliente.length > 1 && count !=listaCliente.length) {
					sbCliente.append(",");
				}
			}

			var paramCliente = sbCliente.toString();
			if(listaProcedimento.length == 0) {
				sql = "SELECT DISTINCT a.*, b.numPrestador, b.procedimento, c.descricaoProcedimento, b.valorInformado, b.qtdExecutada, b.valorProcessado, b.valorLiberado FROM guia a "
						+"JOIN detalhesguia b ON a.numeroGuiaPrestador = b.numPrestador "
						+"AND b.dataRealizacao >= '" + dtIni + "' AND b.dataRealizacao <= '" + dtFim + "'"
						+"LEFT JOIN procedimento c ON b.procedimento = c.codigoProcedimento "
						+"WHERE a.nomeBeneficiario IN (" + paramCliente + ") ";				
			}else {
				int countP = 0;
				StringBuilder sbProcedimento = new StringBuilder();
				for(var procedimento : listaProcedimento) {
					sbProcedimento.append(procedimento);
					countP++;
					if(listaProcedimento.length > 1 && countP !=listaProcedimento.length) {
						sbProcedimento.append(",");
					}
				}
				var paramProcedimento = sbProcedimento.toString();

				sql = "SELECT DISTINCT a.*, b.numPrestador, b.procedimento, c.descricaoProcedimento, b.valorInformado, b.qtdExecutada, b.valorProcessado, b.valorLiberado FROM guia a "
						+"JOIN detalhesguia b ON a.numeroGuiaPrestador = b.numPrestador "
						+"AND b.dataRealizacao >= '" + dtIni + "' AND b.dataRealizacao <= '" + dtFim + "'"
						+"JOIN procedimento c ON b.procedimento = c.codigoProcedimento ";
				if(listaCliente.length == 0) {							
					sql +="WHERE b.procedimento IN (" + paramProcedimento + ")";	
				}else {
					sql +="WHERE a.nomeBeneficiario IN (" + paramCliente + ") AND b.procedimento IN (" + paramProcedimento + ")";
				}
			}
		}

		List<Guia> guias = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var guia = new Guia();
				guia.setId(rs.getInt("Id"));
				guia.setPrestador(rs.getInt("numeroGuiaPrestador"));
				guia.setOperadora(rs.getInt("numeroGuiaOperadora"));
				guia.setSenha(rs.getInt("senha"));
				var beneficiario = new Beneficiario();
				beneficiario.setNome(rs.getString("nomeBeneficiario"));
				beneficiario.setNumeroCarteira(rs.getString("numeroCarteira"));
				guia.setBeneficiario(beneficiario);
				var procedimento = new Procedimento();
				procedimento.setProcedimento(rs.getInt("b.procedimento"));
				procedimento.setDescricao(rs.getString("c.descricaoProcedimento"));
				guia.setProcedimento(procedimento);
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataInicioFat"));
				guia.setDataIni(calendario);
				guia.setSituacaoGuia(rs.getInt("situacaoGuia"));
				guia.setValorInformadoGuia(rs.getDouble("valorInformadoGuia"));
				guia.setValorProcessadoGuia(rs.getDouble("valorProcessadoGuia"));
				guia.setValorLiberadoGuia(rs.getDouble("valorLiberadoGuia"));
				guia.setValorInformado(rs.getDouble("valorInformado"));
				guia.setValorProcessado(rs.getDouble("valorProcessado"));
				guia.setValorLiberado(rs.getDouble("valorLiberado"));
				guia.setQtdExecutada(rs.getInt("qtdExecutada"));

				guias.add(guia);
			}

			stmt.close();
			rs.close();

			return guias;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}

	public List<Guia> Obter(Date dtIni, Date dtFim) {
		String sql = "SELECT DISTINCT a.*, b.numPrestador, b.procedimento, c.descricaoProcedimento, b.valorInformado, b.qtdExecutada, b.valorProcessado, b.valorLiberado FROM guia a "
				+"JOIN detalhesguia b ON a.numeroGuiaPrestador = b.numPrestador "
				+"AND b.dataRealizacao >= '" + dtIni + "' AND b.dataRealizacao <= '" + dtFim + "'"
				+"JOIN procedimento c ON b.procedimento = c.codigoProcedimento";
		List<Guia> guias = new ArrayList<Guia>();
		System.out.println(sql);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var guia = new Guia();
				guia.setId(rs.getInt("Id"));
				guia.setPrestador(rs.getInt("numeroGuiaPrestador"));
				guia.setOperadora(rs.getInt("numeroGuiaOperadora"));
				guia.setSenha(rs.getInt("senha"));
				var beneficiario = new Beneficiario();
				beneficiario.setNome(rs.getString("nomeBeneficiario"));
				beneficiario.setNumeroCarteira(rs.getString("numeroCarteira"));
				guia.setBeneficiario(beneficiario);
				var procedimento = new Procedimento();
				procedimento.setProcedimento(rs.getInt("b.procedimento"));
				procedimento.setDescricao(rs.getString("c.descricaoProcedimento"));
				guia.setProcedimento(procedimento);
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataInicioFat"));
				guia.setDataIni(calendario);
				guia.setSituacaoGuia(rs.getInt("situacaoGuia"));
				guia.setValorInformadoGuia(rs.getDouble("valorInformadoGuia"));
				guia.setValorProcessadoGuia(rs.getDouble("valorProcessadoGuia"));
				guia.setValorLiberadoGuia(rs.getDouble("valorLiberadoGuia"));
				guia.setValorInformado(rs.getDouble("valorInformado"));
				guia.setValorProcessado(rs.getDouble("valorProcessado"));
				guia.setValorLiberado(rs.getDouble("valorLiberado"));
				guia.setQtdExecutada(rs.getInt("qtdExecutada"));

				guias.add(guia);
			}
			stmt.close();
			rs.close();

			return guias;
		}catch(SQLException e) {
			throw new RuntimeException(e.getSQLState());
		}finally {
			Conexao.FecharConexao();
		}
	}

	public List<Guia> Listar() {
		String sql = "SELECT DISTINCT a.* FROM guia a "
				+"JOIN detalhesguia b ON a.numeroGuiaPrestador = b.numPrestador ";

		List<Guia> guias = new ArrayList<Guia>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var guia = new Guia();
				guia.setId(rs.getInt("Id"));
				guia.setPrestador(rs.getInt("numeroGuiaPrestador"));
				guia.setOperadora(rs.getInt("numeroGuiaOperadora"));
				guia.setSenha(rs.getInt("senha"));
				var beneficiario = new Beneficiario();
				beneficiario.setNome(rs.getString("nomeBeneficiario"));
				beneficiario.setNumeroCarteira(rs.getString("numeroCarteira"));
				guia.setBeneficiario(beneficiario);
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataInicioFat"));
				guia.setDataIni(calendario);
				guia.setSituacaoGuia(rs.getInt("situacaoGuia"));
				guia.setValorInformadoGuia(rs.getDouble("valorInformadoGuia"));
				guia.setValorProcessadoGuia(rs.getDouble("valorProcessadoGuia"));
				guia.setValorLiberadoGuia(rs.getDouble("valorLiberadoGuia"));

				guias.add(guia);
			}
			stmt.close();
			rs.close();

			return guias;
		}catch(SQLException e) {
			throw new RuntimeException(Conexao.status);
		}finally {
			Conexao.FecharConexao();
		}
	}

	public boolean Excluir(int lote) {
		String sql = "delete from guia where ID = '" + lote + "'";		

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, lote);

			stmt.execute();
			stmt.close();

			return true;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
}
