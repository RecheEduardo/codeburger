package br.com.hamburgueria.view;

import javax.swing.*;

import br.com.hamburgueria.model.Usuario;
import br.com.hamburgueria.dao.InsumoDAO;
import br.com.hamburgueria.model.Insumo;

import java.awt.*;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudEstoque extends JFrame {
	private static final long serialVersionUID = 1L;

	public TelaCrudEstoque(Usuario usuario) {
		this.setTitle("Controle de Estoque");
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

        // -------------------------------- HEADER DA JANELA --------------------------------
        JLabel labelTitulo = new JLabel("<html>Operações de Estoque</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudo.add(labelTitulo);

        painelConteudo.add(Box.createVerticalStrut(15));
        
        // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente
        painelConteudo.add(separador);
        // -------------------------------- FIM DO HEADER DA JANELA -------------------------

        painelConteudo.add(Box.createVerticalStrut(10));

        // -------------------------------- CONTEUDO PRINCIPAL --------------------------------
        JPanel painelConteudoPrincipal = new JPanel();
        painelConteudoPrincipal.setLayout(new BoxLayout(painelConteudoPrincipal, BoxLayout.Y_AXIS));
        painelConteudo.add(painelConteudoPrincipal);

        // -------------------- TABELA DO ESTOQUE --------------------
        JLabel labelTituloTabelaEstoque = new JLabel("• Tabela de Estoque:");
        labelTituloTabelaEstoque.setFont(fonteAppRegular);
        labelTituloTabelaEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudoPrincipal.add(labelTituloTabelaEstoque);

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        String[] camposEstoque = {"ID do item", "Nome", "Quantidade", "Validade", "ID do fornecedor"};

        // puxa a lista de insumos
        InsumoDAO insumoDAO = new InsumoDAO();
        List<Insumo> listaInsumos = insumoDAO.listarInsumos();

        // popula o objeto com a lista de insumos
        Object[][] dadosInsumos = new Object[listaInsumos.size()][5];
        for (int i = 0; i < listaInsumos.size(); i++) {
            Insumo p = listaInsumos.get(i);
            dadosInsumos[i][0] = p.getId();
            dadosInsumos[i][1] = p.getNome();
            dadosInsumos[i][2] = p.getQuantidade();
            dadosInsumos[i][3] = p.getValidade();
            dadosInsumos[i][4] = p.getIdFornecedor();
        }

        // adiciona os insumos na JTable
        JTable table = new JTable(dadosInsumos, camposEstoque);
        JScrollPane scrollPane = new JScrollPane(table);

        // configura o tamanho máximo da tabela para 10 registros
        int alturaLinha = table.getRowHeight();
        int alturaTabelaMaxima = alturaLinha * 10;
        Dimension dimensaoScrollPane = new Dimension(Integer.MAX_VALUE, alturaTabelaMaxima);
        scrollPane.setPreferredSize(dimensaoScrollPane);
        scrollPane.setMaximumSize(dimensaoScrollPane);

        // adiciona a tabela no conteudo principal
        painelConteudoPrincipal.add(scrollPane);
        // ----------------- FIM DA TABELA DO ESTOQUE -----------------

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        // SEPARADOR DE SEÇÃO
        JSeparator separadorListaPrincipal = new JSeparator(SwingConstants.HORIZONTAL);
        separadorListaPrincipal.setForeground(Color.BLACK);
        separadorListaPrincipal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        painelConteudoPrincipal.add(separadorListaPrincipal);

        painelConteudoPrincipal.add(Box.createVerticalStrut(10));

        // ----------------------- AÇÕES DO CRUD -----------------------
        JLabel labelAcoesEstoque = new JLabel("• Ações:");
        labelAcoesEstoque.setFont(fonteAppRegular);
        labelAcoesEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudoPrincipal.add(labelAcoesEstoque);

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        //                 AS AÇÕES DO CRUD VÃO VIR AQUI

        // ------------------ FIM DAS AÇÕES DO CRUD --------------------

        
        // ------------------------------------ RODAPE DA JANELA -----------------------------------
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.add(painelBotao, BorderLayout.SOUTH);

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
        // -------------------------------- FIM DO RODAPE DA JANELA --------------------------------
	}
}
