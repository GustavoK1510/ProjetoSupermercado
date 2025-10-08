package controller;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;

public class CadastroController {
	private final TelaCadastro view;
	private final UsuarioDAO model;
	private final Navegador navegador;
	
	public CadastroController(TelaCadastro view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.cadastrar(e -> {
		    String nome = view.getNome();
		    String cpf = view.getCpf();
		    boolean isAdmin = view.getSim();
		    
		    if(nome.isEmpty()||cpf.isEmpty()||(!this.view.getSim()&&!this.view.getNao())) {
		        this.view.exibirMensagem("Erro!", "Complete todos os campos!", 0);
		        return;
		    }

		    if(cpf.length() != 11 || !cpf.matches("\\d+")) {
		        this.view.exibirMensagem("Erro!", "CPF inválido! Deve conter 11 números.", 0);
		        return;
		    }

		    if(this.model.buscarPorCpfDB(cpf) != null) {
		        this.view.exibirMensagem("Erro!", "CPF já cadastrado!", 0);
		        return;
		    }

		    Usuario u = new Usuario(nome, cpf, isAdmin);
		    this.model.addUsuarioDB(u);

		    this.view.exibirMensagem("Sucesso!", "Cadastrado com sucesso!", 1);
		    this.view.limparFormulario();
		    this.navegador.navegarPara("Login");
		});

		
		
	}
}
