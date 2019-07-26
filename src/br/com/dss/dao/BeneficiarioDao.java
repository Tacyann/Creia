package br.com.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Beneficiario;

public class BeneficiarioDao {
	private Connection connection;

	public BeneficiarioDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Beneficiario beneficiario) {
		boolean adicionou;
		String sql = "insert into beneficiario(nome,numerocarteira) values(?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, beneficiario.getNome());
			stmt.setString(2, beneficiario.getNumeroCarteira());

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

	public Beneficiario Obter(Beneficiario beneficiario) {
		String sql = "select * from beneficiario where nome = '" + beneficiario.getNome() + "' and numerocarteira = '" + beneficiario.getNumeroCarteira() + "'";		
		Beneficiario b = new Beneficiario();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				b.setId(rs.getInt("Id"));
				b.setNome(rs.getString("nome"));
				b.setNumeroCarteira(rs.getString("numerocarteira"));
			}
			
			stmt.close();
			rs.close();
			
			return b;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public Beneficiario Obter(String filtro) {
		String sql = "select * from beneficiario where nome = '" + filtro + "'";		
		Beneficiario b = new Beneficiario();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				b.setId(rs.getInt("Id"));
				b.setNome(rs.getString("nome"));
				b.setNumeroCarteira(rs.getString("numerocarteira"));
			}
			
			stmt.close();
			rs.close();
			
			return b;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public int qtdClientes() {
		String sql = "select count(*) as QTD from beneficiario";		
		int qtd = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				qtd =rs.getInt("QTD");
			}
			
			stmt.close();
			rs.close();
			
			return qtd;
		}catch(SQLException e) {
			throw new RuntimeException(Conexao.status);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Existe(Beneficiario beneficiario) {
		String sql = "select count(*) as QTD from beneficiario where nome = '" + beneficiario.getNome() + "' and numerocarteira = '" + beneficiario.getNumeroCarteira() + "'";		
		int qtd = 0;
		boolean exist = false;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				qtd =rs.getInt("QTD");
			}
			
			stmt.close();
			rs.close();
			
			if(qtd > 0) {
				exist = true;
			}
			
			return exist;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Beneficiario> Listar() {
		String sql = "select * from beneficiario";
		
		List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var beneficiario = new Beneficiario();
				beneficiario.setId(rs.getInt("Id"));
				beneficiario.setNome(rs.getString("nome"));
				beneficiario.setNumeroCarteira(rs.getString("numerocarteira"));
				beneficiarios.add(beneficiario);
			}
			stmt.close();
			rs.close();
			
			return beneficiarios;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int id) {
		String sql = "delete from beneficiario where ID = '" + id + "'";		
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);
			
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
