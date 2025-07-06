package br.com.hamburgueria.view;

import javax.swing.*;

import br.com.hamburgueria.dao.PedidoDAO;
import br.com.hamburgueria.model.Pedido;
import br.com.hamburgueria.model.Usuario;

import java.awt.*;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudPedidos extends JFrame{
	private static final long serialVersionUID = 1L;

	public TelaCrudPedidos(Usuario usuario) {
		this.setTitle("Controle de Pedidos");
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
        JLabel labelTitulo = new JLabel("<html>Operações de Pedidos</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painelConteudo.add(labelTitulo);
        painelConteudo.add(Box.createVerticalStrut(15));
        
        painelConteudo.add(separador);
        painelConteudo.add(Box.createVerticalStrut(10));

        // CONTEUDO PRINCIPAL
        JPanel painelConteudoPrincipal = new JPanel();
        painelConteudoPrincipal.setLayout(new BoxLayout(painelConteudoPrincipal, BoxLayout.Y_AXIS));
        painelConteudo.add(painelConteudoPrincipal);

        // TABELA DOS PEDIDOS
        JLabel labelTituloTabelaPedidos = new JLabel("• Tabela de Pedidos:");
        labelTituloTabelaPedidos.setFont(fonteAppRegular);
        labelTituloTabelaPedidos.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelConteudoPrincipal.add(labelTituloTabelaPedidos);
        painelConteudoPrincipal.add(Box.createVerticalStrut(15));
        
        String[] camposPedidos = {"ID", "ID do Usuário", "Data do Pedido", "Status atual"};

 		// puxa a lista de pedidos
        PedidoDAO pedidoDAO = new PedidoDAO();
 		List<Pedido> listaPedidos = pedidoDAO.listarPedidos();

 		// popula o objeto com a lista de pedidos
        Object[][] dadosPedidos = new Object[listaPedidos.size()][5];
 		for (int i = 0; i < listaPedidos.size(); i++) {
 			Pedido p = listaPedidos.get(i);
 			dadosPedidos[i][0] = p.getId();
 			dadosPedidos[i][1] = p.getIdUsuario();
 			dadosPedidos[i][2] = p.getDataPedido();
 			dadosPedidos[i][3] = p.getStatusPedido();
 		}
 		
        // adiciona os pedidos na JTable
        JTable table = new JTable(dadosPedidos, camposPedidos);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // configura o tamanho máximo da tabela para 10 registros
        int alturaLinha = table.getRowHeight();
        int alturaTabelaMaxima = alturaLinha * 10;
        Dimension dimensaoScrollPane = new Dimension(Integer.MAX_VALUE, alturaTabelaMaxima);
        scrollPane.setPreferredSize(dimensaoScrollPane);
        scrollPane.setMaximumSize(dimensaoScrollPane);
       
        painelConteudoPrincipal.add(scrollPane);

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
