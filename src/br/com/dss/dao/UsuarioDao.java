package br.com.dss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dss.Conexao;
import br.com.dss.modelo.Usuario;

public class UsuarioDao {
	private Connection connection;

	public UsuarioDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}

	public boolean Adicionar(Usuario usuario) {
		boolean adicionou = false;
		String sql = "insert into usuario(nomecompleto,endereco,complemento,numero,cidade,estadoID,cep,ddd,telefone,email,usuario,senha,nivel)"+
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNomeCompleto());
			stmt.setString(2, usuario.getEndereco());
			stmt.setString(3, usuario.getComplemento());
			stmt.setString(4, usuario.getNumero());
			stmt.setString(5, usuario.getCidade());
			stmt.setInt(6, usuario.getEstado());
			stmt.setString(7, usuario.getCep());
			stmt.setString(8, usuario.getDdd());
			stmt.setString(9, usuario.getTelefone());
			stmt.setString(10, usuario.getEmail());
			stmt.setString(11, usuario.getUsuario());
			stmt.setString(12, usuario.getSenha());
			stmt.setString(13, usuario.getNivel());

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

	public String[] Obter(String id) {
		String sql = "select * from usuario where ID = '" + id + "'";		
		String nome = null;
		String login = null;
		String senha = null;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				nome = rs.getString("nomecompleto");
				login = rs.getString("usuario");
				senha = rs.getString("senha");
			}
			stmt.close();
			rs.close();
			
			String[] cadastro = {nome,login,senha};

			return cadastro;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Usuario> Listar() {
		String sql = "select * from usuario";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var user = new Usuario();
				
				user.setId(rs.getInt("ID"));
				user.setNomeCompleto(rs.getString("nomecompleto"));
				user.setEndereco(rs.getString("endereco"));
				user.setComplemento(rs.getString("complemento"));
				user.setNumero(rs.getString("numero"));
				user.setCidade(rs.getString("cidade"));
				user.setEstado(rs.getInt("estadoID"));
				user.setCep(rs.getString("cep"));
				user.setDdd(rs.getString("ddd"));
				user.setTelefone(rs.getString("telefone"));
				user.setEmail(rs.getString("email"));
				user.setUsuario(rs.getString("usuario"));
				user.setSenha(rs.getString("senha"));
				user.setNivel(rs.getString("nivel"));
				usuarios.add(user);
			}
			stmt.close();
			rs.close();
			
			return usuarios;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Atualizar(Usuario usuario) {
		boolean atualizou = false;
		String sql = "update usuario set nomecompleto = ?,endereco = ?,complemento = ?,numero = ?,cidade = ?,estadoID = ?,cep = ?,ddd = ?,telefone = ?,email = ?,usuario = ?,senha = ?,nivel = ?"
				+ "where Id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNomeCompleto());
			stmt.setString(2, usuario.getEndereco());
			stmt.setString(3, usuario.getComplemento());
			stmt.setString(4, usuario.getNumero());
			stmt.setString(5, usuario.getCidade());
			stmt.setInt(6, usuario.getEstado());
			stmt.setString(7, usuario.getCep());
			stmt.setString(8, usuario.getDdd());
			stmt.setString(9, usuario.getTelefone());
			stmt.setString(10, usuario.getEmail());
			stmt.setString(11, usuario.getUsuario());
			stmt.setString(12, usuario.getSenha());
			stmt.setString(13, usuario.getNivel());
			stmt.setInt(14, usuario.getId());

			stmt.execute();
			stmt.close();

			atualizou = true;
			return atualizou;
		}catch(SQLException e) {
			//throw new RuntimeException(e);
			System.out.println(e);
			return atualizou = false;
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public boolean Excluir(int id) {
		String sql = "delete from usuario where ID = ?";		
		
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
