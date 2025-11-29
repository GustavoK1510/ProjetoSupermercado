package controller;

import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastroProduto;
import view.TelaDeCompras;
import view.TelaLogin;

public class LoginController {
	private final TelaLogin view;
	private final TelaCadastroProduto viewProduto;
	private final TelaDeCompras viewCompras;
	private final UsuarioDAO model;
	private final ProdutoDAO modelProduto;
	private final Navegador navegador;
	private Usuario usuarioLogado;
	
	public LoginController(TelaLogin view, TelaCadastroProduto viewProduto, TelaDeCompras viewCompras, UsuarioDAO model, ProdutoDAO modelProduto, Navegador navegador) {
		this.view = view;
		this.viewProduto = viewProduto;
		this.viewCompras = viewCompras;
		this.model = model;
		this.modelProduto = modelProduto;
		this.navegador = navegador;
		
		this.view.cadastrar(e ->{
			this.navegador.navegarPara("Cadastro");
			
			this.view.limparFormulario();
			
		});
		
		this.view.entrar(e ->{
			String nome = view.getNome();
			String cpf = view.getCpf();
			
			if(nome.equals("")||cpf.equals("")) {
				this.view.exibirMensagem("Erro!", "Complete todos os campos!", 0);
				return;
			}
			
			Usuario userEncontrado = this.model.buscarPorCpfDB(cpf);
			
			if(userEncontrado==null){
				this.view.exibirMensagem("Erro!", "Nome ou CPF inválido! Tente novamente!", 0);
				return;
			}
			
			if(!userEncontrado.getNome().equalsIgnoreCase(nome)) {
				this.view.exibirMensagem("Erro!", "Nome e CPF inválido! Tente novamente!", 0);
				return;
			}
			
			this.usuarioLogado = userEncontrado;
			
			this.view.limparFormulario();
			
			if(usuarioLogado.isAdmin()) {
				this.navegador.navegarPara("Produto");
				
				this.viewProduto.atualizarTabela(this.modelProduto.listarTodosDB());
			} 
			
			else {
				this.navegador.navegarPara("Compras");
				
				this.viewCompras.atualizarTabela(this.modelProduto.listarTodosDB());
			}
		});
		
		
	}
	
	public Usuario getUser() {
		return this.usuarioLogado;
	}
	
}
