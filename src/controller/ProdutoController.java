package controller;

import model.Produto;
import model.ProdutoDAO;
import view.TelaCadastroProduto;

public class ProdutoController {
	private final TelaCadastroProduto view;
	private final ProdutoDAO model;
	private final Navegador navegador;
	
	private boolean modoNovo;
	private boolean modoEditar;
	
	private Produto produtoSelecionado;
	
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
				if(modoEditar) {
					this.produtoSelecionado.setNome(nome);
					this.produtoSelecionado.setValor(valor);
					this.produtoSelecionado.setQtd(qtd);
					this.produtoSelecionado.setDesc(desc);
					
					this.model.atualizarDB(produtoSelecionado);
					
					this.produtoSelecionado = null;
					
					this.view.atualizarTabela(this.model.listarTodosDB());
					this.view.setEdit(false);
					this.view.setSalvar(false);
					this.view.limparFormulario();
				}else if(modoNovo){
					Produto p = new Produto(valor, qtd, nome, desc);
					this.model.addProdutoDB(p);
					
					this.view.atualizarTabela(this.model.listarTodosDB());
					this.view.setEdit(false);
					this.view.setSalvar(false);
					this.view.limparFormulario();
				}
			}
		});
		
		this.view.editar(e->{
			
			this.modoEditar = true;
			this.modoNovo = false;

			int linha = view.getTable().getSelectedRow();
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Nenhum produto selecionado!", 1);
				return;
			}else {
				this.produtoSelecionado = this.model.listarTodosDB().get(linha);
				
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
			this.modoEditar = false;
			this.modoNovo = true;
			
			this.view.limparFormulario();
			this.view.setEdit(true);
			this.view.setSalvar(true);
		});
		
		this.view.excluir(e->{
			int linha = view.getTable().getSelectedRow();
			if(linha==-1) {
				this.view.exibirMensagem("Erro!", "Nenhum produto selecionado!", 1);
				return;
			}else {
				this.produtoSelecionado = this.model.listarTodosDB().get(linha);
				
				this.model.removerDB(this.produtoSelecionado.getId());
				
				this.view.atualizarTabela(this.model.listarTodosDB());
			}
		});
		
		this.view.voltar(e->{
			this.navegador.navegarPara("Login");
		});
		
		this.view.atualizarTabela(this.model.listarTodosDB());
	}

}
