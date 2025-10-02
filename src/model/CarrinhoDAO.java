package model;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
	
	private final ArrayList<Produto> listaCarrinho;
	
	public CarrinhoDAO() {
		this.listaCarrinho = new ArrayList<>();
	}
	
	public void addNoCarrinho(Produto produto)
	{
		if (produto != null) {
			this.listaCarrinho.add(produto);
		}
	}
	
	public List<Produto> listarCarrinho(){
		return new ArrayList<>(this.listaCarrinho);
	}
	
	public boolean buscarPorNome(String nome) {
		for (Produto produto : this.listaCarrinho) {
			if (produto.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean atualizar(Produto produtoAtualizado) {
		for (int i = 0; i < this.listaCarrinho.size(); i++) {
			Produto produtoExistente = this.listaCarrinho.get(i);

			if (produtoExistente.getNome().equalsIgnoreCase(produtoAtualizado.getNome())) {
				this.listaCarrinho.set(i, produtoAtualizado);
				return true;
			}
		}
		return false;
	}

}
