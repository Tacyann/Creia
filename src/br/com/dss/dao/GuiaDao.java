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

public class GuiaDao {
	private Connection connection;

	public GuiaDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Guia guia) {
		boolean adicionou;
		String sql = "insert into guia(lote,numeroGuiaPrestador,numeroGuiaOperadora,senha,nomeBeneficiario,numeroCarteira,dataInicioFat,situacaoGuia,valorInformadoGuia,valorProcessadoGuia,valorLiberadoGuia)"+
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, guia.getNumero());
			stmt.setInt(2, guia.getPrestador());
			stmt.setInt(3, guia.getOperadora());
			stmt.setInt(4, guia.getSenha());
			stmt.setString(5, guia.getBeneficiario());
			stmt.setString(6, guia.getCarteira());
			stmt.setDate(7, new Date(guia.getDataIni().getTimeInMillis()));
			stmt.setInt(8, guia.getSituacao());
			stmt.setDouble(9, guia.getValorInformado());
			stmt.setDouble(10, guia.getValorProcessado());
			stmt.setDouble(11, guia.getValorLiberado());

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

	public Guia Obter(int lote) {
		String sql = "select * from guia where lote = '" + lote + "'";		
		Guia guia = new Guia();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				guia.setId(rs.getInt("Id"));
				guia.setNumero(rs.getInt("lote"));
				guia.setPrestador(rs.getInt("numeroGuiaPrestador"));
				guia.setOperadora(rs.getInt("numeroGuiaOperadora"));
				guia.setSenha(rs.getInt("senha"));
				guia.setBeneficiario(rs.getString("nomeBeneficiario"));
				guia.setCarteira(rs.getString("numeroCarteira"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataInicioFat"));
				guia.setDataIni(calendario);
				guia.setSituacao(rs.getInt("situacaoGuia"));
				guia.setValorInformado(rs.getDouble("valorInformadoGuia"));
				guia.setValorProcessado(rs.getDouble("valorProcessadoGuia"));
				guia.setValorLiberado(rs.getDouble("valorLiberadoGuia"));
			}
			
			stmt.close();
			rs.close();

			return guia;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Guia> Listar() {
		String sql = "select * from guia";
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
				guia.setBeneficiario(rs.getString("nomeBeneficiario"));
				guia.setCarteira(rs.getString("numeroCarteira"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dataInicioFat"));
				guia.setDataIni(calendario);
				guia.setSituacao(rs.getInt("situacaoGuia"));
				guia.setValorInformado(rs.getDouble("valorInformadoGuia"));
				guia.setValorProcessado(rs.getDouble("valorProcessadoGuia"));
				guia.setValorLiberado(rs.getDouble("valorLiberadoGuia"));
				
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
