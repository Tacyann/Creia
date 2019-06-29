package br.com.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Glosa;

public class GlosaDao {
	private Connection connection;

	public GlosaDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Glosa glosa) {
		boolean adicionou;
		String sql = "insert into motivoglosa(codigo,descricao) values(?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, glosa.getCodigo());
			stmt.setString(2, glosa.getDescricao());

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

	public Glosa Obter(Glosa glosa) {
		String sql = "select * from motivoglosa where codigo = " + glosa.getCodigo();		
		Glosa g = new Glosa();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				g.setCodigo(rs.getInt("codigo"));
				g.setDescricao(rs.getString("descricao"));
			}
			
			stmt.close();
			rs.close();
			
			return g;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Existe(Glosa glosa) {
		String sql = "select count(*) as QTD from motivoglosa where codigo = " + glosa.getCodigo();		
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
	
	public List<Glosa> Listar() {
		String sql = "select * from motivoglosa";
		
		List<Glosa> glosas = new ArrayList<Glosa>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var glosa = new Glosa();
				glosa.setCodigo(rs.getInt("codigo"));
				glosa.setDescricao(rs.getString("descricao"));
				glosas.add(glosa);
			}
			stmt.close();
			rs.close();
			
			return glosas;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int id) {
		String sql = "delete from motivoglosa where codigo = " + id;		
		
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
