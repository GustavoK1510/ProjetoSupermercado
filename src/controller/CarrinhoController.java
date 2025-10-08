package controller;

import model.CarrinhoDAO;
import model.ProdutoDAO;
import model.Usuario;
import view.TelaCarrinho;
import view.TelaCupomFiscal;
import view.TelaDeCompras;
import model.Produto;

public class CarrinhoController {
	private TelaCarrinho view;
	private TelaDeCompras viewCompras;
	private TelaCupomFiscal viewCupom;
	private LoginController loginController;
	private ProdutoDAO modelProduto;
	private CarrinhoDAO model;
	
	private Navegador navegador;
	
	public CarrinhoController(TelaCarrinho view, TelaDeCompras viewCompras, TelaCupomFiscal viewCupom, LoginController loginController, CarrinhoDAO model, ProdutoDAO modelProduto, Navegador navegador) {
		this.view = view;
		this.viewCompras = viewCompras;
		this.viewCupom = viewCupom;
		this.loginController = loginController;
		this.model = model;
		this.modelProduto = modelProduto;
		this.navegador = navegador;
		
		
		 
		this.view.remover(e->{
			int linha = this.view.getTable().getSelectedRow();
			
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Selecione um item para ser removido!", 1);
				return;
			}
			
			String nome = (String) view.getTable().getValueAt(linha, 0);
			int qtd = (int) view.getTable().getValueAt(linha, 1);
			double valor = (double) view.getTable().getValueAt(linha, 2);
			String desc = (String) view.getTable().getValueAt(linha, 3);
			
			Produto existente = this.modelProduto.buscarPorNomeDB(nome);
				
				if (existente != null) {
				    int qtdN = existente.getQtd() + qtd;
				    
				    if (qtdN <= 0) {
				        this.modelProduto.removerDB(existente.getId());
				        this.viewCompras.atualizarTabela(this.modelProduto.listarTodosDB());
				        
				    } else {
				        existente.setQtd(qtdN);
				        this.modelProduto.atualizarDB(existente);
				    }
				    
				} else {
				    Produto p = new Produto(valor, qtd, nome, desc);
				    this.modelProduto.addProdutoDB(p);
				}
				
				this.model.removerItem(nome);
				
				this.view.atualizarTabela(this.model.listarCarrinho());
				String total = this.view.getTotal(2);
				this.view.setTotal(total);
				
				this.viewCupom.setTotal(total);
			
		});
		
		this.view.finalizar(e->{
			Usuario user = this.loginController.getUser();
			
			this.viewCupom.atualizarTabela(this.model.listarCarrinho());
			this.viewCupom.setNome(user.getNome());
			this.viewCupom.setCpf(user.getCpf());
			
			this.navegador.navegarPara("Cupom");
			
		});
		
		this.view.voltar(e->{
			this.navegador.navegarPara("Compras");
			this.viewCompras.atualizarTabela(this.modelProduto.listarTodosDB());
			
		});
		
		
	}

}
