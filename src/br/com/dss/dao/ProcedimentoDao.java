package br.com.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Procedimento;

public class ProcedimentoDao {
	private Connection connection;

	public ProcedimentoDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Procedimento procedimento) {
		boolean adicionou;
		String sql = "insert into procedimento(codigoTabela,codigoProcedimento,descricaoProcedimento) values(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, procedimento.getTabela());
			stmt.setInt(2, procedimento.getProcedimento());
			stmt.setString(3, procedimento.getDescricao());
			
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

	public Procedimento Obter(int codigoProcedimento) {
		String sql = "select * from procedimento where codigoProcedimento = '" + codigoProcedimento + "'";		
		Procedimento procedimento = new Procedimento();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				procedimento.setId(rs.getInt("Id"));
				procedimento.setTabela(rs.getInt("codigoTabela"));
				procedimento.setProcedimento(rs.getInt("codigoProcedimento"));
				procedimento.setDescricao(rs.getString("descricaoProcedimento"));
			}
			stmt.close();
			rs.close();

			return procedimento;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Existe(int codigoProcedimento) {
		String sql = "select count(*) as QTD from procedimento where codigoProcedimento = '" + codigoProcedimento + "'";		
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
	
	public List<Procedimento> Listar() {
		String sql = "select * from procedimento";
		List<Procedimento> procedimentos = new ArrayList<Procedimento>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var procedimento = new Procedimento();

				procedimento.setId(rs.getInt("ID"));
				procedimento.setTabela(rs.getInt("codigoTabela"));
				procedimento.setProcedimento(rs.getInt("codigoProcedimento"));
				procedimento.setDescricao(rs.getString("descricaoProcedimento"));
								
				procedimentos.add(procedimento);
			}
			stmt.close();
			rs.close();
			
			return procedimentos;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int id) {
		String sql = "delete from procedimento where ID = '" + id + "'";		
		
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
