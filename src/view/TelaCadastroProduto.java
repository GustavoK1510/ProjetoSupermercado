package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import model.ProdutoDAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TelaCadastroProduto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_Nome, tf_Qtd, tf_Valor;
	private JTextArea ta_Desc;
	private JButton btnEditar, btnExcluir, btnSalvar, btnNProduto, btnVoltar;
	private DefaultTableModel tableModel;
	private JTable tableProduto;
	private JScrollPane scroll;
	
	/**
	 * Create the panel.
	 */
	public TelaCadastroProduto() {
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
		
		JLabel lbCADASTRODEPRODUTOS = new JLabel("CADASTRO DE PRODUTOS");
		lbCADASTRODEPRODUTOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbCADASTRODEPRODUTOS.setHorizontalAlignment(SwingConstants.CENTER);
		lbCADASTRODEPRODUTOS.setBounds(182, 11, 532, 59);
		add(lbCADASTRODEPRODUTOS);
		
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
		
		this.btnEditar = new JButton("Editar");
		this.btnEditar.setBounds(20, 494, 114, 25);
		add(this.btnEditar);
		
		this.btnExcluir = new JButton("Excluir");
		this.btnExcluir.setBounds(278, 494, 114, 25);
		add(this.btnExcluir);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(535, 66, 12, 466);
		add(separator_1);
		
		this.btnSalvar = new JButton("Salvar");
		this.btnSalvar.setBounds(776, 494, 114, 25);
		this.btnSalvar.setEnabled(false);
		add(this.btnSalvar);
		
		this.btnNProduto = new JButton("Novo Produto");
		this.btnNProduto.setBounds(776, 32, 114, 25);
		add(this.btnNProduto);
		
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setBounds(0, 11, 114, 25);
		add(this.btnVoltar);
		
		setEdit(false);
	}
	
	public String getNome() {
		return this.tf_Nome.getText();
	}
	
	public double getValor() {
		String texto = this.tf_Valor.getText();

	    if (texto.isEmpty()) {
	        return -1;
	    }

	    try {
	        return Double.parseDouble(texto);
	    } catch (NumberFormatException e) {
	        return -2;
	    }
	}
	
	public void setQtd(int qtd) {
	    this.tf_Qtd.setText(String.valueOf(qtd));
	}
	
	public int getQtd() {
		String texto = this.tf_Qtd.getText().trim();
		double valor = getValor();
		if(valor==-1) {
			JOptionPane.showMessageDialog(null, "Erro! Complete todos os campos!");
			return -1;
		}else if(valor==-2){
			JOptionPane.showMessageDialog(null, "Erro! Digite um valor válido!");
			return -1;
		}else {
			if (texto.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Erro! Complete todos os campos!");
		        return -1;
		    }

		    try {
		        return Integer.parseInt(texto);
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Erro! Digite um valor válido!");
		        return -1;
		    }
		}
	}
	
	public void setSalvar(boolean habilitado) {
	    this.btnSalvar.setEnabled(habilitado);
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
	
	public String getDesc() {
		return this.ta_Desc.getText();
	}
	
	public void setDesc(String desc) {
	    this.ta_Desc.setText(desc);
	}
	
	public void setEdit(boolean editaveis) {
	    this.tf_Nome.setEditable(editaveis);
	    this.tf_Qtd.setEditable(editaveis);
	    this.tf_Valor.setEditable(editaveis);
	    this.ta_Desc.setEditable(editaveis);
	}
	
	public void editar(ActionListener actionListener) {
		this.btnEditar.addActionListener(actionListener);
	}
	
	public void excluir(ActionListener actionListener) {
		this.btnExcluir.addActionListener(actionListener);
	}
	
	public void salvar(ActionListener actionListener) {
		this.btnSalvar.addActionListener(actionListener);
	}
	
	public void nProduto(ActionListener actionListener) {
		this.btnNProduto.addActionListener(actionListener);
	}
	
	public void voltar(ActionListener actionListener) {
		this.btnVoltar.addActionListener(actionListener);
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
	
	public void limparFormulario(){
		this.tf_Nome.setText("");
		this.tf_Valor.setText("");
		this.tf_Qtd.setText("");
		this.ta_Desc.setText("");
	}
}
