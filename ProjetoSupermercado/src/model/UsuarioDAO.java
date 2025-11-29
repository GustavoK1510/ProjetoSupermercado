package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private final ArrayList<Usuario> listaDeUsuarios;
	
	public UsuarioDAO() {
		this.listaDeUsuarios = new ArrayList<>();
	}
	
	public void addUsuario (Usuario usuario) {
		if (usuario != null) {
			this.listaDeUsuarios.add(usuario);
		}
	}
	
	 public void addUsuarioDB(Usuario usuario) {
	        String sql = "INSERT INTO usuario (nome, cpf, admin) VALUES (?, ?, ?)";

	        try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, usuario.getNome());
	            stmt.setString(2, usuario.getCpf());
	            stmt.setBoolean(3, usuario.isAdmin());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
	        }
	    }
	 
	 public Usuario buscarPorCpfDB(String cpf) {
	        String sql = "SELECT * FROM usuario WHERE cpf = ?";
	        Usuario usuario = null;

	        try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, cpf);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                usuario = new Usuario(
	                    rs.getString("nome"),
	                    rs.getString("cpf"),
	                    rs.getBoolean("admin")
	                );
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar usuário por CPF: " + e.getMessage());
	        }

	        return usuario;
	    }
	
	public List<Usuario> listarTodos(Usuario usuario){
		return new ArrayList<>(this.listaDeUsuarios);
	}
	
	public Usuario buscarPorCpf(String cpf) {
		for (Usuario usuario : this.listaDeUsuarios) {
			if (usuario.getCpf().equalsIgnoreCase(cpf)) {
				return usuario;
			}
		}
		return null;
	}

}
