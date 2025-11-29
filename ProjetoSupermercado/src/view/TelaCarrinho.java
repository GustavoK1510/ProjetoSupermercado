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
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

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
        setLayout(new MigLayout("fillx, filly, insets 0","[grow]","[][grow][]20[]"));

        this.btnVoltar = new JButton("Voltar");
        add(this.btnVoltar, "split 2, flowx, gapleft 10, gaptop 10");

        JLabel lbCARRINHO = new JLabel("CARRINHO");
        lbCARRINHO.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbCARRINHO.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbCARRINHO, "growx, wrap, gaptop 5");

        JSeparator separatorTop = new JSeparator();
        add(separatorTop, "growx, wrap");

        this.tableCarrinho = new JTable();

        String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
        this.tableModel = new DefaultTableModel(colunas, 0);
        this.tableCarrinho.setModel(this.tableModel);

        this.scroll = new JScrollPane(this.tableCarrinho);
        add(this.scroll, "grow, push, wrap");

        JSeparator separatorBottom = new JSeparator();
        add(separatorBottom, "growx, wrap");

        this.lbTotal = new JLabel("");
        this.lbTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.lbTotal.setHorizontalAlignment(SwingConstants.CENTER);

        add(this.lbTotal, "split 3, alignx center, gaptop 10");

        this.btnFinalizar = new JButton("Finalizar compra");
        add(this.btnFinalizar, "alignx center, gapleft 20");

        this.btnRemover = new JButton("Remover");
        add(this.btnRemover, "alignx center, gapleft 20, wrap");
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
        this.lbTotal.setText("Total: R$" + total);
    }

    public String getTotal(int coluna) {

        double soma = 0.0;

        for (int i = 0; i < this.tableCarrinho.getRowCount(); i++) {
            double valor = (double) this.tableCarrinho.getValueAt(i, coluna);
            int qtd = (int) this.tableCarrinho.getValueAt(i, 1);
            soma += qtd * valor;
        }

        return Double.toString(soma);
    }

    public JTable getTable() {
        return this.tableCarrinho;
    }

    public void atualizarTabela(List<Produto> produtos) {
        tableModel.setRowCount(0);
        for (Produto p : produtos) {
            tableModel.addRow(new Object[]{
                    p.getNome(),
                    p.getQtd(),
                    p.getValor(),
                    p.getDesc()
            });
        }
    }

    public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
    }
}
