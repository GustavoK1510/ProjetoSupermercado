package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import model.ProdutoDAO;

public class TelaDeCompras extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_Nome, tf_Qtd, tf_Valor, tf_QtdCompra;
	private JTextArea ta_Desc;
	private JButton btnSelecionar, btnAdd, btnVoltar, btnVerCarrinho, btnMais, btnMenos;
	private DefaultTableModel tableModel;
	private JTable tableProduto;
	private JScrollPane scroll;
	
	/**
	 * Create the panel.
	 */
	public TelaDeCompras() {
		setPreferredSize(new Dimension(900, 555));
		setLayout(null);
		
		this.scroll = new JScrollPane();
		this.scroll.setBounds(20, 86, 372, 397);
		add(this.scroll);
		
		this.tableProduto = new JTable();
		this.scroll.setViewportView(this.tableProduto);

		String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
		this.tableModel = new DefaultTableModel(colunas, 0);
		this.tableProduto.setModel(this.tableModel);
		
		JLabel lbCOMPRAS = new JLabel("COMPRAS");
		lbCOMPRAS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbCOMPRAS.setHorizontalAlignment(SwingConstants.CENTER);
		lbCOMPRAS.setBounds(333, 11, 250, 59);
		add(lbCOMPRAS);
		
		this.ta_Desc = new JTextArea();
		this.ta_Desc.setBounds(670, 247, 220, 236);
		add(this.ta_Desc);
		
		JLabel lbDesc = new JLabel("Descrição:");
		lbDesc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lbDesc.setBounds(668, 215, 69, 21);
		add(lbDesc);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setHorizontalAlignment(SwingConstants.CENTER);
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNome.setBounds(670, 81, 69, 21);
		add(lbNome);
		
		JLabel lbQtd = new JLabel("Qtd. em estoque:");
		lbQtd.setHorizontalAlignment(SwingConstants.CENTER);
		lbQtd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbQtd.setBounds(613, 124, 127, 21);
		add(lbQtd);
		
		JLabel lbValor = new JLabel("Valor:");
		lbValor.setHorizontalAlignment(SwingConstants.CENTER);
		lbValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbValor.setBounds(668, 169, 69, 21);
		add(lbValor);
		
		this.tf_Nome = new JTextField();
		this.tf_Nome.setBounds(734, 82, 156, 20);
		add(this.tf_Nome);
		this.tf_Nome.setColumns(10);
		
		this.tf_Qtd = new JTextField();
		this.tf_Qtd.setColumns(10);
		this.tf_Qtd.setBounds(734, 125, 156, 20);
		add(this.tf_Qtd);
		
		this.tf_Valor = new JTextField();
		this.tf_Valor.setColumns(10);
		this.tf_Valor.setBounds(734, 170, 156, 20);
		add(this.tf_Valor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 900, 36);
		add(separator);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(0, 530, 900, 36);
		add(separator1);
		
		this.btnSelecionar = new JButton("Selecionar produto");
		this.btnSelecionar.setBounds(20, 494, 142, 25);
		add(this.btnSelecionar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(535, 66, 12, 466);
		add(separator_1);
		
		this.btnAdd = new JButton("Adicionar ao carrinho");
		this.btnAdd.setBounds(670, 494, 220, 25);
		this.btnAdd.setEnabled(false);
		add(this.btnAdd);
		
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setBounds(0, 11, 114, 25);
		add(this.btnVoltar);
		
		this.btnVerCarrinho = new JButton("Ver Carrinho");
		this.btnVerCarrinho.setBounds(776, 31, 114, 25);
		add(this.btnVerCarrinho);
		
		this.tf_QtdCompra = new JTextField();
		this.tf_QtdCompra.setBounds(577, 496, 40, 23);
		add(this.tf_QtdCompra);
		this.tf_QtdCompra.setColumns(10);
		
		this.btnMais = new JButton("+");
		this.btnMais.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.btnMais.setBounds(535, 496, 42, 23);
		add(this.btnMais);
		
		this.btnMenos = new JButton("-");
		btnMenos.setEnabled(false);
		this.btnMenos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.btnMenos.setBounds(617, 495, 42, 23);
		add(this.btnMenos);
		
		setEnabled(false);
		setEdit(false);
		setBotoes(false);
		setMais(false);
		setMenos(false);
		setBtnSelec(true);
		
	}
	
	public String getNome() {
		return this.tf_Nome.getText();
	}
	
	public double getValor() {
		return Double.parseDouble(this.tf_Valor.getText());
	}
	
	public String getDesc() {
		return this.ta_Desc.getText();
	}
	
	public int getQtd() {
		return Integer.parseInt(this.tf_Qtd.getText());
	}
	
	public void setBotoes(boolean botoes) {
		this.btnAdd.setEnabled(botoes);
	}
	
	public void setMais(boolean mais) {
		this.btnMais.setEnabled(mais);
	}
	
	public void setMenos(boolean menos) {
		this.btnMenos.setEnabled(menos);
	}
	
	public int getQtdCompra() {
		return Integer.parseInt(this.tf_QtdCompra.getText());
	}
	
	public void setQtdCompra(Integer QtdCompra) {
		this.tf_QtdCompra.setText(QtdCompra.toString());
	}
	
	public void setAddCarrinho(boolean habilitado) {
	    this.btnAdd.setEnabled(habilitado);
	}
	
	public void setValor(double valor) {
	    this.tf_Valor.setText(String.valueOf(valor));
	}
	
	public JTable getTable() {
		return this.tableProduto;
	}
	
	public void setNome(String nome) {
	    this.tf_Nome.setText(nome);
	}
	
	public void setDesc(String desc) {
	    this.ta_Desc.setText(desc);
	}
	
	public void addCarrinho(ActionListener actionListener) {
		this.btnAdd.addActionListener(actionListener);
	}
	
	public void selecionar(ActionListener actionListener) {
		this.btnSelecionar.addActionListener(actionListener);
	}
	
	public void voltar(ActionListener actionListener) {
		this.btnVoltar.addActionListener(actionListener);
	}
	
	public void verCarrinho(ActionListener actionListener) {
		this.btnVerCarrinho.addActionListener(actionListener);
	}
	
	public void mais(ActionListener actionListener) {
		this.btnMais.addActionListener(actionListener);
	}
	
	public void menos(ActionListener actionListener) {
		this.btnMenos.addActionListener(actionListener);
	}
	
	public void atualizarTabela(List<Produto> produtos) {
	    tableModel.setRowCount(0);
	    for (Produto p : produtos) {
	        tableModel.addRow(new Object[]{p.getNome(), p.getQtd(), p.getValor(), p.getDesc()});
	    }
	}
	
	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}
	
	public void setQtd(int qtd) {
	    this.tf_Qtd.setText(String.valueOf(qtd));
	}
	
	public void setBtnSelec(boolean select) {
		this.btnSelecionar.setEnabled(select);
	}
	
	public void setEnabled(boolean enabled) {
		this.tf_Nome.setEnabled(enabled);
	    this.tf_Qtd.setEnabled(enabled);
	    this.tf_Valor.setEnabled(enabled);
	    this.ta_Desc.setEnabled(enabled);
	}
	
	public void setEdit(boolean edit) {
	    this.tf_QtdCompra.setEditable(edit);
	}
	
	public void limparFormulario(){
		this.tf_Nome.setText("");
		this.tf_Valor.setText("");
		this.tf_Qtd.setText("");
		this.ta_Desc.setText("");
		this.tf_QtdCompra.setText("");
	}
}
