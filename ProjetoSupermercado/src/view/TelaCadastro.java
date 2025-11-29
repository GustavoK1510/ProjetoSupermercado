package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class TelaCadastro extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField tf_Nome, tf_Cpf;
    private JButton bt_Cadastrar;
    private JRadioButton btnSim, btnNao;
    private ButtonGroup grupo;

    /**
     * Tela responsiva com MigLayout
     */
    public TelaCadastro() {
        setLayout(new MigLayout("wrap 2, insets 20, gap 15","[grow,fill][grow,fill]","[]20[]20[]20[]20[]"));

        JLabel lbLogin = new JLabel("CADASTRO");
        lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbLogin, "span 2, growx");

        JLabel lbNome = new JLabel("NOME:");
        lbNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lbNome);

        tf_Nome = new JTextField();
        tf_Nome.setHorizontalAlignment(SwingConstants.CENTER);
        add(tf_Nome, "growx");

        JLabel lbCpf = new JLabel("CPF:");
        lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lbCpf);

        tf_Cpf = new JTextField();
        tf_Cpf.setHorizontalAlignment(SwingConstants.CENTER);
        add(tf_Cpf, "growx");

        JLabel lbAdmin = new JLabel("Administrador?");
        lbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbAdmin);

        JPanel painelRadio = new JPanel(new MigLayout("insets 0, gap 10", "[grow][grow]"));
        btnSim = new JRadioButton("Sim");
        btnNao = new JRadioButton("Não");

        painelRadio.add(btnSim, "growx");
        painelRadio.add(btnNao, "growx");

        grupo = new ButtonGroup();
        grupo.add(btnSim);
        grupo.add(btnNao);

        add(painelRadio, "growx");

        bt_Cadastrar = new JButton("Cadastrar");
        bt_Cadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(bt_Cadastrar, "span 2, growx, gaptop 10");
    }


    public String getNome() {
        return this.tf_Nome.getText();
    }

    public String getCpf() {
        return this.tf_Cpf.getText();
    }

    public boolean getSim() {
        return this.btnSim.isSelected();
    }

    public boolean getNao() {
        return this.btnNao.isSelected();
    }

    public void cadastrar(ActionListener actionListener) {
        this.bt_Cadastrar.addActionListener(actionListener);
    }

    public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
    }

    public void limparFormulario(){
        this.tf_Nome.setText("");
        this.tf_Cpf.setText("");
        this.grupo.clearSelection();
    }
}
