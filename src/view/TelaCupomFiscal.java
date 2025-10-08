package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Produto;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

public class TelaCupomFiscal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableProds;
	private JLabel lbNome, lbCpf, lbTotal;
	private JScrollPane scroll;
	private JButton btnFechar;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public TelaCupomFiscal() {
		setPreferredSize(new Dimension(900, 555));
		setLayout(null);
		
		this.scroll = new JScrollPane();
		this.scroll.setBounds(327, 85, 241, 414);
		add(this.scroll);
		
		this.tableProds = new JTable();
		this.scroll.setViewportView(this.tableProds);
		
		String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
		this.tableModel = new DefaultTableModel(colunas, 0);
		this.tableProds.setModel(this.tableModel);
		
		this.lbNome = new JLabel("");
		this.lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.lbNome.setHorizontalAlignment(SwingConstants.CENTER);
		this.lbNome.setBounds(327, 11, 241, 32);
		add(this.lbNome);
		
		this.lbCpf = new JLabel("");
		this.lbCpf.setHorizontalAlignment(SwingConstants.CENTER);
		this.lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.lbCpf.setBounds(327, 54, 241, 32);
		add(this.lbCpf);
		
		this.btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.btnFechar.setBounds(444, 510, 124, 34);
		add(this.btnFechar);
		
		this.lbTotal = new JLabel("");
		this.lbTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.lbTotal.setBounds(280, 510, 154, 34);
		add(this.lbTotal);
		
		
	}
	
	public void fechar(ActionListener actionListener) {
		this.btnFechar.addActionListener(actionListener);
	}
	
	public void setNome(String nome) {
		this.lbNome.setText("Nome: " + nome);
	}
	
	public void setCpf(String cpf) {
		this.lbCpf.setText("CPF: " + cpf);
	}
	
	public void setTotal(String total) {
		this.lbTotal.setText("Total pago: R$"+ total);
	}
	
	public void atualizarTabela(List<Produto> produtos) {
	    tableModel.setRowCount(0);
	    for (Produto p : produtos) {
	        tableModel.addRow(new Object[]{p.getNome(), p.getQtd(), p.getValor(), p.getDesc()});
	    }
	}
	
	public JTable getTable() {
		return this.tableProds;
	}
	
	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}
	
	
}
