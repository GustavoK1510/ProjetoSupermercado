package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private final ArrayList<Produto> listaDeProdutos;
	
	public ProdutoDAO() {
		this.listaDeProdutos = new ArrayList<>();
	}
	
	public void addProdutoDB(Produto p) {
        String sql = "INSERT INTO produto(nome, quantidade, valor, descricao) VALUES(?,?,?,?)";
        
        try (Connection conn = Conexao.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQtd());
            stmt.setDouble(3, p.getValor());
            stmt.setString(4, p.getDesc());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Produto> listarTodosDB() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getDouble("valor"),
                    rs.getInt("quantidade"), 
                    rs.getString("nome"),
                    rs.getString("descricao")
                );
                produtos.add(p);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }
	
	 public boolean atualizarDB(Produto produto) {
	        String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ?, descricao = ? WHERE id = ?";
	        
	        try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, produto.getNome());
	            stmt.setInt(2, produto.getQtd());
	            stmt.setDouble(3, produto.getValor());
	            stmt.setString(4, produto.getDesc());
	            stmt.setInt(5, produto.getId());
	            
	            int linhasAfetadas = stmt.executeUpdate();
	            return linhasAfetadas > 0;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	 public boolean removerDB(int id) {
	        String sql = "DELETE FROM produto WHERE id = ?";
	        
	        try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setInt(1, id);
	            int linhasAfetadas = stmt.executeUpdate();
	            return linhasAfetadas > 0;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	public boolean buscarPorNome(String nome) {
		for (Produto produto : this.listaDeProdutos) {
			if (produto.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public Produto buscarPorNomeDB(String nome) {
	    String sql = "SELECT * FROM produto WHERE nome = ?";
	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nome);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Produto(
	                rs.getInt("id"),
	                rs.getDouble("valor"),
	                rs.getInt("quantidade"),
	                rs.getString("nome"),
	                rs.getString("descricao")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public int qtdPorNome(String nome) {
		for (Produto produto : this.listaDeProdutos) {
			if (buscarPorNome(nome)) {
				return produto.getQtd();
			}
		}
		return -1;
	}
	
	
}
