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
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Procedimento;

public class DetalhesGuiaDao {
	private Connection connection;

	public DetalhesGuiaDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(DetalheGuia detalheguia, int prestador) {
		boolean adicionou;
		String sql = "insert into detalhesguia(numPrestador,dataRealizacao,procedimento,grauParticipacao,valorInformado,qtdExecutada,valorProcessado,valorLiberado) "+
		"values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, prestador);
			stmt.setDate(2, new Date(detalheguia.getDataRealizacao().getTimeInMillis()));
			stmt.setInt(3, detalheguia.getProcedimento().getProcedimento());
			stmt.setInt(4, detalheguia.getGrauParticipacao());
			stmt.setDouble(5, detalheguia.getValorInformado());
			stmt.setInt(6, detalheguia.getQtdExecutada());
			stmt.setDouble(7, detalheguia.getValorProcessado());
			stmt.setDouble(8, detalheguia.getValorLiberado());
			
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

	public DetalheGuia Obter(int relacaoGuiasID) {
		String sql = "select * from detalheguia a join procedimento b on b.id = a.procedimentoid where relacaoGuiasID = '" + relacaoGuiasID + "'";		
		DetalheGuia detalheguia = new DetalheGuia();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				detalheguia.setId(rs.getInt("Id"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataRealizacao"));
				detalheguia.setDataRealizacao(calendario);
				detalheguia.setId(rs.getInt("relacaoGuiasID"));
				var procedimento = new Procedimento();
				procedimento.setId(rs.getInt("b.id"));
				procedimento.setTabela(rs.getInt("b.codigoTabela"));
				procedimento.setProcedimento(rs.getInt("b.codigoProcedimento"));
				procedimento.setDescricao(rs.getString("b.descricaoProcedimento"));
				detalheguia.setProcedimento(procedimento);
				detalheguia.setGrauParticipacao(rs.getInt("grauParticipacao"));
				detalheguia.setValorInformado(rs.getDouble("valorInformado"));
				detalheguia.setQtdExecutada(rs.getInt("qtdExecutada"));
				detalheguia.setValorProcessado(rs.getDouble("valorProcessado"));
				detalheguia.setValorLiberado(rs.getDouble("valorLiberado"));
			}
			stmt.close();
			rs.close();

			return detalheguia;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<DetalheGuia> Listar() {
		String sql = "select * from procedimento";
		List<DetalheGuia> detalheGuias = new ArrayList<DetalheGuia>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var detalheguia = new DetalheGuia();

				detalheguia.setId(rs.getInt("Id"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataRealizacao"));
				detalheguia.setDataRealizacao(calendario);
				detalheguia.setId(rs.getInt("relacaoGuiasID"));
				var procedimento = new Procedimento();
				procedimento.setId(rs.getInt("b.id"));
				procedimento.setTabela(rs.getInt("b.codigoTabela"));
				procedimento.setProcedimento(rs.getInt("b.codigoProcedimento"));
				procedimento.setDescricao(rs.getString("b.descricaoProcedimento"));
				detalheguia.setProcedimento(procedimento);
				detalheguia.setGrauParticipacao(rs.getInt("grauParticipacao"));
				detalheguia.setValorInformado(rs.getDouble("valorInformado"));
				detalheguia.setQtdExecutada(rs.getInt("qtdExecutada"));
				detalheguia.setValorProcessado(rs.getDouble("valorProcessado"));
				detalheguia.setValorLiberado(rs.getDouble("valorLiberado"));
								
				detalheGuias.add(detalheguia);
			}
			stmt.close();
			rs.close();
			
			return detalheGuias;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int relacaoGuiasID) {
		String sql = "delete from detalheguia where relacaoGuiasID = '" + relacaoGuiasID + "'";		
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, relacaoGuiasID);
			
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
