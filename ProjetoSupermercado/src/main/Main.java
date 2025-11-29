package main;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import controller.CadastroController;
import controller.CarrinhoController;
import controller.CompraController;
import controller.CupomFiscalController;
import controller.LoginController;
import controller.Navegador;
import controller.ProdutoController;
import model.CarrinhoDAO;
import model.ProdutoDAO;
import model.UsuarioDAO;
import view.Frame;
import view.TelaCadastro;
import view.TelaCadastroProduto;
import view.TelaCarrinho;
import view.TelaCupomFiscal;
import view.TelaDeCompras;
import view.TelaLogin;

public class Main {

	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(
				new Font("Arial", Font.PLAIN, 18)
				));
		
		Frame frame = new Frame();
		Navegador navegador = new Navegador(frame);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
		
		TelaCadastro telaCadastro = new TelaCadastro();
		TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
		TelaCarrinho telaCarrinho = new TelaCarrinho();
		TelaDeCompras telaDeCompras = new TelaDeCompras();
		TelaLogin telaLogin = new TelaLogin();
		TelaCupomFiscal telaCupomFiscal = new TelaCupomFiscal();
		
		CadastroController cadastroController = new CadastroController(telaCadastro, usuarioDAO, navegador);
		ProdutoController produtoController = new ProdutoController(telaCadastroProduto, produtoDAO, navegador);
		CompraController compraController = new CompraController(telaDeCompras, telaCarrinho, telaCupomFiscal, produtoDAO, carrinhoDAO, navegador);
		LoginController loginController = new LoginController(telaLogin, telaCadastroProduto, telaDeCompras, usuarioDAO, produtoDAO, navegador);
		CarrinhoController carrinhoController = new CarrinhoController(telaCarrinho, telaDeCompras, telaCupomFiscal, loginController, carrinhoDAO, produtoDAO, navegador);
		CupomFiscalController cupomFiscalController = new CupomFiscalController(telaCupomFiscal, carrinhoDAO, navegador);
		
		
		navegador.addPainel("Login", telaLogin);
		navegador.addPainel("Cadastro", telaCadastro);
		navegador.addPainel("Produto", telaCadastroProduto);
		navegador.addPainel("Compras", telaDeCompras);
		navegador.addPainel("Cupom", telaCupomFiscal);
		navegador.addPainel("Carrinho", telaCarrinho);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		navegador.navegarPara("Login");
	}

}
