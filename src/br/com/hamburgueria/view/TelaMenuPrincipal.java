package br.com.hamburgueria.view;

import javax.swing.*;

import br.com.hamburgueria.model.Usuario;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuPrincipal extends JFrame {
 
	private static final long serialVersionUID = 1L;

	public TelaMenuPrincipal(Usuario usuario) {
        final String nomeUsuario = usuario.getNome();
      
        this.setTitle("Menu de operações");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 650);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        
        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 36);
        final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);

        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelLogin, BorderLayout.CENTER); // Add o painel de conteúdo principal ao centro

        // Header Principal
        JLabel labelTitulo = new JLabel("<html>Menu principal</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // SUBTITULO
        JLabel labelMenuOptions = new JLabel("Olá " + nomeUsuario + "! Quais dados deseja acessar?");
        labelMenuOptions.setFont(fonteAppRegular);
        labelMenuOptions.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // BOTAO DE ESTOQUE
        JButton botaoEstoque = new JButton("Estoque");
        botaoEstoque.setFont(fonteAppBold);
        botaoEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoEstoque.setMaximumSize(new Dimension(1280, 50));
        botaoEstoque.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                 new TelaCrudEstoque(usuario).setVisible(true);
                 dispose();
           }
         });
        
        // BOTAO DE PEDIDOS
        JButton botaoPedidos = new JButton("Pedidos");
        botaoPedidos.setFont(fonteAppBold);
        botaoPedidos.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoPedidos.setMaximumSize(new Dimension(1280, 50));
        botaoPedidos.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                 new TelaCrudPedidos(usuario).setVisible(true);
                 dispose();
           }
         });
        
        
        // BOTAO DE PRODUTOS
        JButton botaoProdutos = new JButton("Produtos");
        botaoProdutos.setFont(fonteAppBold);
        botaoProdutos.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoProdutos.setMaximumSize(new Dimension(1280, 50));
        botaoProdutos.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	            new TelaCrudProdutos(usuario).setVisible(true);
	            dispose();
	        }
    	});
        
     // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente
        
     // Adiciona espaçamento entre os elementos
        painelLogin.add(labelTitulo);
        painelLogin.add(Box.createVerticalStrut(15));
        
        painelLogin.add(labelMenuOptions);
        painelLogin.add(Box.createVerticalStrut(15));
        
        painelLogin.add(separador);
        painelLogin.add(Box.createVerticalStrut(10));

        painelLogin.add(botaoEstoque);
        painelLogin.add(Box.createVerticalStrut(15));

        painelLogin.add(botaoPedidos);
        painelLogin.add(Box.createVerticalStrut(15));

        painelLogin.add(botaoProdutos);
        painelLogin.add(Box.createVerticalStrut(15));

      // RODAPE DA JANELA
      JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

      // BOTAO DE SAIR
      JButton botaoVoltar = new JButton("Sair");
      botaoVoltar.setFont(fonteAppBold);
      botaoVoltar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
                dispose();
          }
      });
      
      painelBotao.add(botaoVoltar);
      this.add(painelBotao, BorderLayout.SOUTH);
	}
}
