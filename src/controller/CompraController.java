package controller;

import model.CarrinhoDAO;
import model.Produto;
import model.ProdutoDAO;
import view.TelaDeCompras;

public class CompraController {
	private TelaDeCompras view;
	private ProdutoDAO model;
	private CarrinhoDAO modelCarrinho;
	private Navegador navegador;
	
	public CompraController(TelaDeCompras view, ProdutoDAO model, CarrinhoDAO modelCarrinho, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.modelCarrinho = modelCarrinho;
		this.navegador = navegador;
		
		this.view.addCarrinho(e->{
			String nome = view.getNome();
			int qtdComprada = view.getQtdCompra();
			int qtd = view.getQtd();
			int qtdN = qtd-qtdComprada;
			double valor = view.getValor();
			String desc = view.getDesc();
			
			if(this.modelCarrinho.buscarPorNome(nome)) {
				Produto carrinho = new Produto(valor, qtdComprada, nome, desc);
				this.modelCarrinho.atualizar(carrinho);
				
				Produto atualizado = new Produto(valor, qtdN, nome, desc);
				this.model.atualizar(atualizado);
				
				this.view.atualizarTabela(this.model.listarTodos());
				
				this.view.setBotoes(false);
				this.view.setMais(false);
				this.view.setMenos(false);
				this.view.setEdit(false);
				this.view.setBtnSelec(true);
				
			}else {
				Produto ca = new Produto(valor, qtdComprada, nome, desc);
				this.modelCarrinho.addNoCarrinho(ca);
				
				Produto atualizado = new Produto(valor, qtdN, nome, desc);
				this.model.atualizar(atualizado);
				
				this.view.atualizarTabela(this.model.listarTodos());
				
				this.view.setBotoes(false);
				this.view.setMais(false);
				this.view.setMenos(false);
				this.view.setEdit(false);
				this.view.setBtnSelec(true);
			}
			
		});
		
		this.view.selecionar(e->{
			int linha = view.getTable().getSelectedRow();
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Selecione um produto para ser adicionado!", 1);
				return;
			}else {
				String nome = (String) view.getTable().getValueAt(linha, 0);
				int qtd = (int) view.getTable().getValueAt(linha, 1);
				double valor = (double) view.getTable().getValueAt(linha, 2);
				String desc = (String) view.getTable().getValueAt(linha, 3);
				
				this.view.setNome(nome);
		        this.view.setQtd(qtd);
		        this.view.setValor(valor);
		        this.view.setDesc(desc);
		        this.view.setQtdCompra(1);
		        
		        this.view.setBotoes(true);
		        this.view.setMais(true);
		        this.view.setEdit(true);
		        this.view.setBtnSelec(false);
			}
		});
		
		this.view.verCarrinho(e->{
			navegador.navegarPara("Carrinho");
		});
		
		this.view.voltar(e->{
			navegador.navegarPara("Login");
		});
		
		this.view.mais(e->{
			Integer i = this.view.getQtdCompra();
			int n = this.view.getQtd();
			i++;
			this.view.setQtdCompra(i);
			if(i==n) {
				this.view.setMais(false);
			}
			
			if(i>1) {
				this.view.setMenos(true);
			}
		});
		
		this.view.menos(e->{
			Integer i = this.view.getQtdCompra();
			int n = this.view.getQtd();
			i--;
			this.view.setQtdCompra(i);
			if(i<n) {
				this.view.setMais(true);
			}
				
			if(i==1) {
				this.view.setMenos(false);
			}
		});
		
		this.view.atualizarTabela(this.model.listarTodos());
	}

}
