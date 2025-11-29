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
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

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

        setLayout(new MigLayout("fillx, filly, insets 10","[grow 40][5px][grow 60]","[][10px][grow][]"));

        btnVoltar = new JButton("Voltar");
        add(btnVoltar, "cell 0 0, left");

        JLabel lbTitulo = new JLabel("CADASTRO DE PRODUTOS");
        lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbTitulo, "cell 0 0 3 1, growx");

        btnNProduto = new JButton("Novo Produto");
        add(btnNProduto, "cell 2 0, right, wrap");

        JSeparator sepTop = new JSeparator();
        add(sepTop, "cell 0 1 3 1, growx, wrap");

        scroll = new JScrollPane();
        tableProduto = new JTable();

        String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
        tableModel = new DefaultTableModel(colunas, 0);
        tableProduto.setModel(tableModel);

        scroll.setViewportView(tableProduto);

        add(scroll, "cell 0 2, grow, push");

        JSeparator sepVert = new JSeparator(SwingConstants.VERTICAL);
        add(sepVert, "cell 1 2, growy");

        JPanel form = new JPanel(new MigLayout("insets 0, fillx, filly","[][grow]","[][][][][grow][]"));

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
        form.add(lbNome, "cell 0 0, right");

        tf_Nome = new JTextField();
        form.add(tf_Nome, "cell 1 0, growx");

        JLabel lbQtd = new JLabel("Qtd. em estoque:");
        lbQtd.setFont(new Font("Tahoma", Font.PLAIN, 13));
        form.add(lbQtd, "cell 0 1, right");

        tf_Qtd = new JTextField();
        form.add(tf_Qtd, "cell 1 1, growx");

        JLabel lbValor = new JLabel("Valor:");
        lbValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
        form.add(lbValor, "cell 0 2, right");

        tf_Valor = new JTextField();
        form.add(tf_Valor, "cell 1 2, growx");

        JLabel lbDesc = new JLabel("Descrição:");
        lbDesc.setFont(new Font("Tahoma", Font.PLAIN, 13));
        form.add(lbDesc, "cell 0 3, top, right");

        ta_Desc = new JTextArea();
        ta_Desc.setLineWrap(true);
        ta_Desc.setWrapStyleWord(true);

        JScrollPane scrollDesc = new JScrollPane(ta_Desc);
        scrollDesc.setPreferredSize(new Dimension(220, 200));

        form.add(scrollDesc, "cell 1 3, grow, push");

        btnSalvar = new JButton("Salvar");
        btnSalvar.setEnabled(false);
        form.add(btnSalvar, "cell 1 4, right");

        add(form, "cell 2 2, grow");

        btnEditar = new JButton("Editar");
        add(btnEditar, "cell 0 3, split 2, left");

        btnExcluir = new JButton("Excluir");
        add(btnExcluir, "cell 0 3, left");

        JSeparator sepBottom = new JSeparator();
        add(sepBottom, "cell 0 3 3 1, growx, wrap");

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
        if (valor == -1) {
            JOptionPane.showMessageDialog(null, "Erro! Complete todos os campos!");
            return -1;
        } else if (valor == -2) {
            JOptionPane.showMessageDialog(null, "Erro! Digite um valor válido!");
            return -1;
        } else {
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

    public void limparFormulario() {
        this.tf_Nome.setText("");
        this.tf_Valor.setText("");
        this.tf_Qtd.setText("");
        this.ta_Desc.setText("");
    }
}
