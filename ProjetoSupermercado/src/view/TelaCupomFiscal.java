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

import net.miginfocom.swing.MigLayout;

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
        setLayout(new MigLayout("fillx, filly, insets 20","[grow]","[][]20[][][grow][]20[]"));

        this.lbNome = new JLabel("");
        this.lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.lbNome.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.lbNome, "growx, wrap");

        this.lbCpf = new JLabel("");
        this.lbCpf.setHorizontalAlignment(SwingConstants.CENTER);
        this.lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(this.lbCpf, "growx, wrap");

        this.tableProds = new JTable();
        String[] colunas = {"Nome", "Qtd", "Valor", "Descrição"};
        this.tableModel = new DefaultTableModel(colunas, 0);
        this.tableProds.setModel(this.tableModel);

        this.scroll = new JScrollPane(this.tableProds);
        add(this.scroll, "grow, push, wrap");

        this.lbTotal = new JLabel("");
        this.lbTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(this.lbTotal, "split 2, alignx right");

        this.btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(this.btnFechar, "alignx left, wrap");

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
        this.lbTotal.setText("Total pago: R$" + total);
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
