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
		
		this.view.cadastrar(e ->{
			String nome = view.getNome();
			String cpf = view.getCpf();
			boolean sim = view.getSim();
			boolean nao = view.getNao();
			
			if(nome.equals("")||cpf.equals("")||(!sim&&!nao)) {
				view.exibirMensagem("Erro", "Complete todos os campos!", 0);
			}
			else{
				this.view.exibirMensagem("Sucesso", "Cadastrado com sucesso!", 1);
				this.view.limparFormulario();
				
				Usuario u = new Usuario(nome, cpf, sim);
				this.model.addUsuario(u);
				
				this.navegador.navegarPara("Login");
			}
		});
		
		
	}
}
