package br.com.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Profissional;

public class ProfissionalDao {
	private Connection connection;

	public ProfissionalDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Profissional profissional) {
		boolean adicionou;
		String sql = "insert into profissional(nome,especializacao,numeroconselho) values(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, profissional.getNome());
			stmt.setString(2, profissional.getEspecializacao());
			stmt.setInt(3, Integer.parseInt(profissional.getNumeroconselho()));
			
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

	public Profissional Obter(String nome) {
		String sql = "select * from profissional where nome = '" + nome + "'";		
		Profissional profissional = new Profissional();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				profissional.setId(rs.getInt("Id"));
				profissional.setNome(rs.getString("nome"));
				profissional.setEspecializacao(rs.getString("especializacao"));
				profissional.setNumeroconselho(rs.getString("numeroconselho"));
			}
			stmt.close();
			rs.close();

			return profissional;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Existe(String nome) {
		String sql = "select count(*) as QTD from profissional where nome = '" + nome + "'";		
		int qtd = 0;
		boolean exist = false;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				qtd = rs.getInt("QTD");
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
	
	public List<Profissional> Listar() {
		String sql = "select * from profissional";
		List<Profissional> profissionais = new ArrayList<Profissional>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var profissional = new Profissional();

				profissional.setId(rs.getInt("ID"));
				profissional.setNome(rs.getString("nome"));
				profissional.setEspecializacao(rs.getString("especializacao"));
				profissional.setNumeroconselho(rs.getString("numeroconselho"));
								
				profissionais.add(profissional);
			}
			stmt.close();
			rs.close();
			
			return profissionais;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Atualizar(Profissional profissional) {
		boolean adicionou;
		String sql = "update profissional set nome = ?, especializacao = ?, numeroconselho = ? where ID = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, profissional.getNome());
			stmt.setString(2, profissional.getEspecializacao());
			stmt.setInt(3, Integer.parseInt(profissional.getNumeroconselho()));
			stmt.setInt(4, profissional.getId());
			
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
	
	public boolean Excluir(int id) {
		String sql = "delete from profissional where ID = '" + id + "'";		
		
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
