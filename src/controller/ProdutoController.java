package controller;

import model.Produto;
import model.ProdutoDAO;
import view.TelaCadastroProduto;

public class ProdutoController {
	private final TelaCadastroProduto view;
	private final ProdutoDAO model;
	private final Navegador navegador;
	
	public ProdutoController(TelaCadastroProduto view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.salvar(e -> {
			String nome = view.getNome();
			double valor = view.getValor();
			int qtd = view.getQtd();
			String desc = view.getDesc();
			
			if(nome.isEmpty()||desc.isEmpty()) {
				this.view.exibirMensagem("Erro!", "Complete todos os campos!", 1);
			}else {
				if(this.model.buscarPorNome(nome)) {
					Produto p = new Produto(valor, qtd, nome, desc);
					this.model.atualizar(p);
					
					this.view.atualizarTabela(this.model.listarTodos());
					this.view.setEdit(false);
					this.view.setSalvar(false);
					this.view.limparFormulario();
				}else {
					Produto p = new Produto(valor, qtd, nome, desc);
					this.model.addProduto(p);
					
					this.view.atualizarTabela(this.model.listarTodos());
					this.view.setEdit(false);
					this.view.setSalvar(false);
					this.view.limparFormulario();
				}
			}
		});
		
		this.view.editar(e->{

			int linha = view.getTable().getSelectedRow();
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Nenhum produto selecionado!", 1);
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
		        
		        this.view.setSalvar(true);
		        
		        this.view.setEdit(true);
			}
		});
		
		this.view.nProduto(e->{
			this.view.limparFormulario();
			this.view.setEdit(true);
			this.view.setSalvar(true);
		});
		
		this.view.voltar(e->{
			navegador.navegarPara("Login");
		});
		
		this.view.atualizarTabela(this.model.listarTodos());
	}

}
