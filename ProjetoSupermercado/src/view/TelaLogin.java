package view;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

public class TelaLogin extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField tf_Nome, tf_Cpf;
    private JButton bt_Entrar, bt_Cadastrar;

    public TelaLogin() {

        setPreferredSize(new Dimension(900, 555));
        setLayout(new MigLayout("fill","[grow][center][grow]","[grow][30][30][30][50][grow]"));

        JLabel lbLogin = new JLabel("LOGIN");
        lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbLogin, "cell 1 0, center");

        JLabel lbNome = new JLabel("NOME:");
        lbNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lbNome, "cell 1 1, split 2, flowx, gapright 20");

        tf_Nome = new JTextField(15);
        add(tf_Nome, "cell 1 1, growx");

        JLabel lbCpf = new JLabel("CPF:");
        lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lbCpf, "cell 1 2, split 2, flowx, gapright 20");

        tf_Cpf = new JTextField(15);
        add(tf_Cpf, "cell 1 2, growx");

        bt_Entrar = new JButton("Entrar");
        bt_Entrar.setFont(new Font("Tahoma", Font.PLAIN, 14));

        bt_Cadastrar = new JButton("Cadastrar-se");
        bt_Cadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));

        add(bt_Entrar, "cell 1 3, split 2, gapright 30");
        add(bt_Cadastrar, "cell 1 3");

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

    public void limparFormulario() {
        this.tf_Nome.setText("");
        this.tf_Cpf.setText("");
    }
}
