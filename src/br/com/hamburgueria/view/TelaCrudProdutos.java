package br.com.hamburgueria.view;

import javax.swing.*;

import br.com.hamburgueria.model.Usuario;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudProdutos extends JFrame {
	private static final long serialVersionUID = 1L;

	public TelaCrudProdutos(Usuario usuario) {
		this.setTitle("Controle de Produtos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 650);
        this.setLocationRelativeTo(null);
        
        this.setLayout(new BorderLayout());

        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 36);
        final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelConteudo, BorderLayout.CENTER); // Add o painel de conteúdo principal ao centro

        // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente

        // HEADER PRINCIPAL
        JLabel labelTitulo = new JLabel("<html>Operações de Produtos</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelConteudo.add(labelTitulo);
        painelConteudo.add(Box.createVerticalStrut(15));

        painelConteudo.add(separador);
        painelConteudo.add(Box.createVerticalStrut(10));

        // CONTEUDO PRINCIPAL
        JLabel labelSubTitulo = new JLabel("O CRUD dos Produtos vai acontecer aqui!");
        labelSubTitulo.setFont(fonteAppRegular);
        labelSubTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudo.add(labelSubTitulo);

        // RODAPE DA JANELA
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // BOTAO DE VOLTAR
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(fonteAppBold);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  new TelaMenuPrincipal(usuario).setVisible(true);
                  dispose();
            }
        });

        painelBotao.add(botaoVoltar);
        this.add(painelBotao, BorderLayout.SOUTH);
	}
}