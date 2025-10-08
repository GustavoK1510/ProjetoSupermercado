package controller;

import model.CarrinhoDAO;
import view.TelaCupomFiscal;

public class CupomFiscalController {
	private TelaCupomFiscal view;
	private CarrinhoDAO model;
	private Navegador navegador;
	
	public CupomFiscalController(TelaCupomFiscal view, CarrinhoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.fechar(e->{
			this.model.removerTodos();
			
			this.navegador.navegarPara("Compras");
		});
	}
}
