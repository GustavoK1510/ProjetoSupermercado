package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        this.cardLayout = new CardLayout();

        contentPane = new JPanel(this.cardLayout);
        this.contentPane.setPreferredSize(new Dimension(900, 555));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

    public void addTela(String nome, JPanel tela) {
        this.contentPane.add(tela, nome);
    }

    public void mostrarTela(String nome) {
        this.cardLayout.show(this.contentPane, nome);
        this.pack();
    }
}
