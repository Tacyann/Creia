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
			stmt.setString(6, guia.getBeneficiario().getNumerocarteira());
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

	public List<Guia> Obter(String[] lista) {
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(var cliente : lista) {
			sb.append("'").append(cliente).append("'");
			count++;
			if(lista.length > 1 && count !=lista.length) {
				sb.append(",");
			}
		}

		var filtro = sb.toString();
		String sql = "select * from guia where nomeBeneficiario in (" + filtro + ")";
		System.out.println(sql);
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
				beneficiario.setNumerocarteira(rs.getString("numeroCarteira"));
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
				var beneficiario = new Beneficiario();
				beneficiario.setNome(rs.getString("nomeBeneficiario"));
				beneficiario.setNumerocarteira(rs.getString("numeroCarteira"));
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
