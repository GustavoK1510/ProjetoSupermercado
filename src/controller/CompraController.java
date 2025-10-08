package controller;

import model.CarrinhoDAO;
import model.Produto;
import model.ProdutoDAO;
import view.TelaCarrinho;
import view.TelaCupomFiscal;
import view.TelaDeCompras;

public class CompraController {
	private TelaDeCompras view;
	private TelaCarrinho viewCarrinho;
	private TelaCupomFiscal viewCupom;
	private ProdutoDAO model;
	private CarrinhoDAO modelCarrinho;
	private Navegador navegador;
	Produto produtoSelecionado;
	
	public CompraController(TelaDeCompras view, TelaCarrinho viewCarrinho, TelaCupomFiscal viewCupom, ProdutoDAO model, CarrinhoDAO modelCarrinho, Navegador navegador) {
		this.view = view;
		this.viewCarrinho = viewCarrinho;
		this.viewCupom = viewCupom;
		this.model = model;
		this.modelCarrinho = modelCarrinho;
		this.navegador = navegador;
		
		this.view.addCarrinho(e -> {
		    String nome = view.getNome();
		    int qtdComprada = view.getQtdCompra();
		    int qtd = view.getQtd();
		    int qtdN = qtd - qtdComprada;
		    double valor = view.getValor();
		    String desc = view.getDesc();
		    
		    if (this.modelCarrinho.buscarPorNome(nome)) {
		        int qtdC = this.modelCarrinho.qtdPorNome(nome);
		        int qtdCa = qtdComprada + qtdC;

		        Produto carrinho = new Produto(valor, qtdCa, nome, desc);
		        this.modelCarrinho.atualizar(carrinho);

		    } else {
		        Produto ca = new Produto(valor, qtdComprada, nome, desc);
		        this.modelCarrinho.addNoCarrinho(ca);
		    }
		    
		    Produto atualizado = new Produto(produtoSelecionado.getId(), valor, qtdN, nome, desc);

		    if (qtdN <= 0) {
		        this.model.removerDB(produtoSelecionado.getId());
		    } else {
		        this.model.atualizarDB(atualizado);
		    }
		    
		    this.view.limparFormulario();
		    this.view.setBotoes(false);
		    this.view.setMais(false);
		    this.view.setMenos(false);
		    this.view.setEdit(false);
		    this.view.setBtnSelec(true);
		    this.view.atualizarTabela(this.model.listarTodosDB());
		    
		});
		
		this.view.selecionar(e->{
			int linha = view.getTable().getSelectedRow();
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Selecione um produto para ser adicionado!", 1);
				return;
			}else {
				produtoSelecionado = this.model.listarTodosDB().get(linha);
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
			this.navegador.navegarPara("Carrinho");
			
			this.viewCarrinho.atualizarTabela(this.modelCarrinho.listarCarrinho());
			String total = this.viewCarrinho.getTotal(2);
			this.viewCarrinho.setTotal(total);
			this.viewCupom.setTotal(total);
		});
		
		this.view.voltar(e->{
			this.navegador.navegarPara("Login");
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
		
		this.view.atualizarTabela(this.model.listarTodosDB());
	}

}
