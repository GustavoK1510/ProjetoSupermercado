package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_Nome, tf_Cpf;
	private JButton bt_Entrar, bt_Cadastrar;

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setPreferredSize(new Dimension(900, 555));
		setLayout(null);
		
		JLabel lbLogin = new JLabel("LOGIN");
		lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogin.setBounds(241, 11, 434, 95);
		add(lbLogin);
		
		JLabel lbNome = new JLabel("NOME:");
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbNome.setHorizontalAlignment(SwingConstants.CENTER);
		lbNome.setBounds(193, 193, 161, 47);
		add(lbNome);
		
		JLabel lbCpf = new JLabel("CPF:");
		lbCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbCpf.setBounds(193, 251, 161, 47);
		add(lbCpf);
		
		this.tf_Nome = new JTextField();
		this.tf_Nome.setBounds(353, 204, 240, 28);
		add(this.tf_Nome);
		this.tf_Nome.setColumns(10);
		
		this.tf_Cpf = new JTextField();
		this.tf_Cpf.setColumns(10);
		this.tf_Cpf.setBounds(353, 266, 240, 28);
		add(this.tf_Cpf);
		
		this.bt_Entrar = new JButton("Entrar");
		this.bt_Entrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.bt_Entrar.setBounds(265, 348, 115, 37);
		add(this.bt_Entrar);
		
		this.bt_Cadastrar = new JButton("Cadastrar-se");
		this.bt_Cadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.bt_Cadastrar.setBounds(478, 348, 115, 37);
		add(this.bt_Cadastrar);

	}
	
	
	public String getNome() {
		return this.tf_Nome.getText();
	}
	
	public String getCpf() {
		return this.tf_Cpf.getText();
	}
	
	public void cadastrar(ActionListener actionListener) {
		this.bt_Cadastrar.addActionListener(actionListener);
	}
	
	public void entrar(ActionListener actionListener) {
		this.bt_Entrar.addActionListener(actionListener);
	}
	
	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}
	
	public void limparFormulario(){
		this.tf_Nome.setText("");
		this.tf_Cpf.setText("");
	}
}
