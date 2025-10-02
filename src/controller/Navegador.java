package controller;

import javax.swing.JPanel;

import view.Frame;

public class Navegador {
	
	private Frame frame;
	
	public Navegador(Frame frame) {
		this.frame = frame;
	}
	
	public void addPainel(String nome, JPanel tela) {
		this.frame.addTela(nome, tela);
	}
	
	public void navegarPara(String nome) {
		this.frame.mostrarTela(nome);
	}
	
	public void sair() {
		this.frame.dispose();
	}

}
