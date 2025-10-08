package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Produto;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class TelaCarrinho extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableCarrinho;
	private DefaultTableModel tableModel;
	private JButton btnVoltar, btnFinalizar, btnRemover;
	private JScrollPane scroll;
	private JLabel lbTotal;

	/**
	 * Create the panel.
	 */
	public TelaCarrinho() {
		setPreferredSize(new Dimension(900, 555));
		setLayout(null);
		
		JLabel lbCARRINHO = new JLabel("CARRINHO");
		lbCARRINHO.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbCARRINHO.setHorizontalAlignment(SwingConstants.CENTER);
		lbCARRINHO.setBounds(321, 0, 257, 68);
		add(lbCARRINHO);
		
		this.scroll = new JScrollPane();
		this.scroll.setBounds(254, 90, 395, 366);
		add(this.scroll);
		
		this.tableCarrinho = new JTable();
		this.scroll.setViewportView(this.tableCarrinho);
		
		String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
		this.tableModel = new DefaultTableModel(colunas, 0);
		this.tableCarrinho.setModel(this.tableModel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 519, 900, 36);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 67, 900, 36);
		add(separator_1);
		
		this.btnFinalizar = new JButton("Finalizar compra");
		this.btnFinalizar.setBounds(254, 467, 129, 30);
		add(this.btnFinalizar);
		
		this.btnRemover = new JButton("Remover");
		this.btnRemover.setBounds(520, 467, 129, 30);
		add(this.btnRemover);
		
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setBounds(10, 11, 129, 30);
		add(this.btnVoltar);
		
		this.lbTotal = new JLabel("");
		this.lbTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.lbTotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.lbTotal.setBounds(393, 475, 117, 33);
		add(this.lbTotal);

	}
	
	public void finalizar(ActionListener actionListener) {
		this.btnFinalizar.addActionListener(actionListener);
	}
	
	public void voltar(ActionListener actionListener) {
		this.btnVoltar.addActionListener(actionListener);
	}
	
	public void remover(ActionListener actionListener) {
		this.btnRemover.addActionListener(actionListener);
	}
	
	public void setTotal(String total) {
		this.lbTotal.setText("Total: R$"+ total);
	}
	
	public String getTotal(int coluna) {
		String total = "";
		Double soma = 0.0;
		
		for (int i = 0; i < this.tableCarrinho.getRowCount(); i++) {
			double valor = (double) this.tableCarrinho.getValueAt(i, coluna);
			int qtd = (int) this.tableCarrinho.getValueAt(i, 1);
			soma += qtd*valor;
		}
		
		total = soma.toString();
		return total;
	}
	
	public JTable getTable() {
		return this.tableCarrinho;
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
}
