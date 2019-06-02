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
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Lote;

public class LoteDao {
	private Connection connection;

	public LoteDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Lote lote) {
		boolean adicionou;
		String sql = "insert into lote(numeroLotePrestador,numeroProtocolo,dataProtocolo,situacaoProtocolo,valorInformadoProtocolo,valorProcessadoProtocolo,valorLiberadoProtocolo)"+
				"values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, lote.getNumero());
			stmt.setInt(2, lote.getProtocolo());
			stmt.setDate(3, new Date(lote.getData().getTimeInMillis()));
			stmt.setInt(4, lote.getSituacao());
			stmt.setDouble(5, lote.getValorInformado());
			stmt.setDouble(6, lote.getValorProcessado());
			stmt.setDouble(7, lote.getValorLiberado());
			
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

	public Lote Obter(int numeroLote) {
		String sql = "select * from lote where numeroLotePrestador = '" + numeroLote + "'";		
		Lote lote = new Guia();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				lote.setId(rs.getInt("Id"));
				lote.setNumero(rs.getInt("lote"));
				lote.setProtocolo(rs.getInt("numeroProtocolo"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataProtocolo"));
				lote.setData(calendario);
				lote.setSituacao(rs.getInt("situacaoGuia"));
				lote.setValorInformado(rs.getDouble("valorInformadoGuia"));
				lote.setValorProcessado(rs.getDouble("valorProcessadoGuia"));
				lote.setValorLiberado(rs.getDouble("valorLiberadoGuia"));
			}
			
			stmt.close();
			rs.close();

			return lote;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Lote> Listar() {
		String sql = "select * from lote";
		List<Lote> lotes = new ArrayList<Lote>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var lote = new Lote();

				lote.setId(rs.getInt("Id"));
				lote.setNumero(rs.getInt("lote"));
				lote.setProtocolo(rs.getInt("numeroProtocolo"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataProtocolo"));
				lote.setData(calendario);
				lote.setSituacao(rs.getInt("situacaoGuia"));
				lote.setValorInformado(rs.getDouble("valorInformadoGuia"));
				lote.setValorProcessado(rs.getDouble("valorProcessadoGuia"));
				lote.setValorLiberado(rs.getDouble("valorLiberadoGuia"));
				
				lotes.add(lote);
			}
			stmt.close();
			rs.close();
			
			return lotes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int numeroLote) {
		String sql = "delete from lote where ID = '" + numeroLote + "'";		
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, numeroLote);
			
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
