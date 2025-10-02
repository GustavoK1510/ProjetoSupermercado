package model;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private final ArrayList<Produto> listaDeProdutos;
	
	public ProdutoDAO() {
		this.listaDeProdutos = new ArrayList<>();
	}
	
	public void addProduto(Produto produto)
	{
		if (produto != null) {
			this.listaDeProdutos.add(produto);
		}
	}
	
	public List<Produto> listarTodos() {
		return new ArrayList<>(this.listaDeProdutos);
	}
	
	public boolean buscarPorNome(String nome) {
		for (Produto produto : this.listaDeProdutos) {
			if (produto.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean atualizar(Produto produtoAtualizado) {
		for (int i = 0; i < this.listaDeProdutos.size(); i++) {
			Produto produtoExistente = this.listaDeProdutos.get(i);

			if (produtoExistente.getNome().equalsIgnoreCase(produtoAtualizado.getNome())) {
				this.listaDeProdutos.set(i, produtoAtualizado);
				return true;
			}
		}
		return false;
	}
	
	
}
