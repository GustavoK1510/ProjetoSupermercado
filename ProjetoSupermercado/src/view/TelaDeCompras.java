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
import net.miginfocom.swing.MigLayout;

public class TelaDeCompras extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField tf_Nome, tf_Qtd, tf_Valor, tf_QtdCompra;
    private JTextArea ta_Desc;
    private JButton btnSelecionar, btnAdd, btnVoltar, btnVerCarrinho, btnMais, btnMenos;
    private DefaultTableModel tableModel;
    private JTable tableProduto;
    private JScrollPane scroll;

    public TelaDeCompras() {

        setPreferredSize(new Dimension(900, 555));

        setLayout(new MigLayout("fill","[grow 0][grow][grow 0]","[60][grow][50]"));

        JLabel lbCOMPRAS = new JLabel("COMPRAS");
        lbCOMPRAS.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lbCOMPRAS.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbCOMPRAS, "cell 1 0, center");

        this.btnVoltar = new JButton("Voltar");
        add(this.btnVoltar, "cell 0 0, alignx left");

        this.btnVerCarrinho = new JButton("Ver Carrinho");
        add(this.btnVerCarrinho, "cell 2 0, right");

        this.scroll = new JScrollPane();
        add(this.scroll, "cell 0 1, grow");

        this.tableProduto = new JTable();
        this.scroll.setViewportView(this.tableProduto);

        String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
        this.tableModel = new DefaultTableModel(colunas, 0);
        this.tableProduto.setModel(this.tableModel);

        this.btnSelecionar = new JButton("Selecionar produto");
        add(this.btnSelecionar, "cell 0 2, left");

        JPanel panelRight = new JPanel(new MigLayout("fillx, insets 10","[grow]","[][][][][grow][]"));
        add(panelRight, "cell 2 1, grow");

        panelRight.add(new JLabel("Nome:"), "split 2");
        this.tf_Nome = new JTextField(15);
        panelRight.add(this.tf_Nome, "growx, wrap");

        panelRight.add(new JLabel("Qtd. em estoque:"), "split 2");
        this.tf_Qtd = new JTextField(15);
        panelRight.add(this.tf_Qtd, "growx, wrap");

        panelRight.add(new JLabel("Valor:"), "split 2");
        this.tf_Valor = new JTextField(15);
        panelRight.add(this.tf_Valor, "growx, wrap");

        panelRight.add(new JLabel("Descrição:"), "wrap");
        this.ta_Desc = new JTextArea(5, 20);
        this.ta_Desc.setLineWrap(true);
        this.ta_Desc.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(this.ta_Desc);
        panelRight.add(descScroll, "grow, wrap");

        this.btnMais = new JButton("+");
        this.tf_QtdCompra = new JTextField("0", 4);
        this.btnMenos = new JButton("-");

        panelRight.add(this.btnMais, "split 3");
        panelRight.add(this.tf_QtdCompra, "growx");
        panelRight.add(this.btnMenos, "wrap");

        this.btnAdd = new JButton("Adicionar ao carrinho");
        panelRight.add(this.btnAdd, "growx");

        setEnabled(false);
        setEdit(false);
        setBotoes(false);
        setMais(false);
        setMenos(false);
        setBtnSelec(true);
    }

    public String getNome() { return this.tf_Nome.getText(); }
    public double getValor() { return Double.parseDouble(this.tf_Valor.getText()); }
    public String getDesc() { return this.ta_Desc.getText(); }
    public int getQtd() { return Integer.parseInt(this.tf_Qtd.getText()); }

    public int getQtdCompra() { return Integer.parseInt(this.tf_QtdCompra.getText()); }
    public void setQtdCompra(Integer qtd) { this.tf_QtdCompra.setText(qtd.toString()); }

    public JTable getTable() { return this.tableProduto; }

    public void setNome(String nome) { this.tf_Nome.setText(nome); }
    public void setDesc(String d) { this.ta_Desc.setText(d); }
    public void setValor(double v) { this.tf_Valor.setText(String.valueOf(v)); }
    public void setQtd(int q) { this.tf_Qtd.setText(String.valueOf(q)); }

    public void setBotoes(boolean b) { this.btnAdd.setEnabled(b); }
    public void setMais(boolean b) { this.btnMais.setEnabled(b); }
    public void setMenos(boolean b) { this.btnMenos.setEnabled(b); }
    public void setBtnSelec(boolean b) { this.btnSelecionar.setEnabled(b); }

    public void setEnabled(boolean enabled) {
        this.tf_Nome.setEnabled(enabled);
        this.tf_Qtd.setEnabled(enabled);
        this.tf_Valor.setEnabled(enabled);
        this.ta_Desc.setEnabled(enabled);
    }

    public void setEdit(boolean edit) {
        this.tf_QtdCompra.setEditable(edit);
    }

    public void addCarrinho(ActionListener a) { this.btnAdd.addActionListener(a); }
    public void selecionar(ActionListener a) { this.btnSelecionar.addActionListener(a); }
    public void voltar(ActionListener a) { this.btnVoltar.addActionListener(a); }
    public void verCarrinho(ActionListener a) { this.btnVerCarrinho.addActionListener(a); }
    public void mais(ActionListener a) { this.btnMais.addActionListener(a); }
    public void menos(ActionListener a) { this.btnMenos.addActionListener(a); }

    public void atualizarTabela(List<Produto> produtos) {
        tableModel.setRowCount(0);
        for (Produto p : produtos) {
            tableModel.addRow(new Object[]{
                p.getNome(), p.getQtd(), p.getValor(), p.getDesc()
            });
        }
    }

    public void exibirMensagem(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(null, msg, titulo, tipo);
    }

    public void limparFormulario() {
        this.tf_Nome.setText("");
        this.tf_Valor.setText("");
        this.tf_Qtd.setText("");
        this.ta_Desc.setText("");
        this.tf_QtdCompra.setText("");
    }
}
