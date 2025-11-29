package model;

public class Produto {
	private double valor;
	private int qtd, id;
	private String nome, desc;
	
	
	public Produto(int id, double valor, int qtd, String nome, String desc) {
		this.id = id;
		this.valor = valor;
		this.qtd = qtd;
		this.nome = nome;
		this.desc = desc;
		
	}
	
	public Produto(double valor, int qtd, String nome, String desc) {
		this.valor = valor;
		this.qtd = qtd;
		this.nome = nome;
		this.desc = desc;
		
	}
	
	public void imprimir() {
		System.out.println(this.nome);
		System.out.println(this.qtd);
		System.out.println(this.valor);
		System.out.println(this.desc);
		System.out.println("###################################");
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public int getQtd() {
		return qtd;
	}


	public void setQtd(int qtd) {
		this.qtd = qtd;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getDesc() {
		return desc;
	}
	
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
