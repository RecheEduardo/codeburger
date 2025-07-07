package br.com.hamburgueria.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.hamburgueria.dao.ProdutoDAO;
import br.com.hamburgueria.model.Produto;
import br.com.hamburgueria.model.Usuario;

import java.awt.*;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudProdutos extends JFrame {
    private static final long serialVersionUID = 1L;

    // -------------------------------- ATRIBUTOS DA CLASSE --------------------------------
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome;
    private JTextField txtTipo;
    private JTextField txtPreco;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnNovo;
    private JButton btnDeletar;
    private ProdutoDAO produtoDAO;
    private int produtoSelecionadoId = -1;
    // -------------------------------- FIM DOS ATRIBUTOS DA CLASSE ------------------------

    public TelaCrudProdutos(Usuario usuario) {
        this.setTitle("Controle de Produtos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 750);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 36);
        final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);
        final Font fonteCampos = new Font("Century Gothic", Font.PLAIN, 18);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelConteudo, BorderLayout.CENTER);

        // -------------------------------- HEADER DA JANELA --------------------------------
        JLabel labelTitulo = new JLabel("<html>Operações de Produtos</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudo.add(labelTitulo);

        painelConteudo.add(Box.createVerticalStrut(15));

        // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        painelConteudo.add(separador);
        // -------------------------------- FIM DO HEADER DA JANELA -------------------------

        painelConteudo.add(Box.createVerticalStrut(5));

        // -------------------------------- CONTEUDO PRINCIPAL --------------------------------
        JPanel painelConteudoPrincipal = new JPanel();
        painelConteudoPrincipal.setLayout(new BoxLayout(painelConteudoPrincipal, BoxLayout.Y_AXIS));
        painelConteudo.add(painelConteudoPrincipal);

        // ----------------------- FORMULÁRIO DE ENTRADA -----------------------
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(lblNome, gbc);

        txtNome = new JTextField(30);
        txtNome.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFormulario.add(txtNome, gbc);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblTipo, gbc);

        txtTipo = new JTextField(30);
        txtTipo.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFormulario.add(txtTipo, gbc);

        JLabel lblPreco = new JLabel("Preço Unitário (R$):");
        lblPreco.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(lblPreco, gbc);

        txtPreco = new JTextField(30);
        txtPreco.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFormulario.add(txtPreco, gbc);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(lblDescricao, gbc);

        txtDescricao = new JTextArea(4, 30);
        txtDescricao.setFont(fonteCampos);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelFormulario.add(scrollDescricao, gbc);

        painelConteudoPrincipal.add(painelFormulario);
        painelConteudoPrincipal.add(Box.createVerticalStrut(15));
        // ----------------------- FIM DO FORMULÁRIO DE ENTRADA --------------------

        // ----------------------- BOTÕES DE AÇÃO DO FORMULÁRIO -----------------------
        JPanel painelBotoesFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoesFormulario.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnNovo = new JButton("Novo");
        btnNovo.setFont(fonteAppRegular);
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        painelBotoesFormulario.add(btnNovo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteAppRegular);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        painelBotoesFormulario.add(btnSalvar);

        btnDeletar = new JButton("Deletar");
        btnDeletar.setFont(fonteAppRegular);
        btnDeletar.setBackground(new Color(200, 70, 70));
        btnDeletar.setForeground(Color.WHITE);
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarProduto();
            }
        });
        painelBotoesFormulario.add(btnDeletar);

        painelConteudoPrincipal.add(painelBotoesFormulario);
        painelConteudoPrincipal.add(Box.createVerticalStrut(20));
        // ----------------------- FIM DOS BOTÕES DE AÇÃO DO FORMULÁRIO --------------------

        // SEPARADOR DE SEÇÃO
        JSeparator separadorListaPrincipal = new JSeparator(SwingConstants.HORIZONTAL);
        separadorListaPrincipal.setForeground(Color.BLACK);
        separadorListaPrincipal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        painelConteudoPrincipal.add(separadorListaPrincipal);

        painelConteudoPrincipal.add(Box.createVerticalStrut(10));

        // ----------------------- TABELA DOS PRODUTOS -----------------------
        JLabel labelTituloTabelaProdutos = new JLabel("• Tabela de Produtos:");
        labelTituloTabelaProdutos.setFont(fonteAppRegular);
        labelTituloTabelaProdutos.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudoPrincipal.add(labelTituloTabelaProdutos);

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        String[] camposProdutos = {"ID", "Nome", "Tipo", "Preço Unitário", "Descrição"};
        tableModel = new DefaultTableModel(camposProdutos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configura o tamanho máximo da tabela para 10 registros
        int alturaLinha = table.getRowHeight();
        int alturaTabelaMaxima = alturaLinha * 10;
        Dimension dimensaoScrollPane = new Dimension(Integer.MAX_VALUE, alturaTabelaMaxima);
        scrollPane.setPreferredSize(dimensaoScrollPane);
        scrollPane.setMaximumSize(dimensaoScrollPane);
        
        painelConteudoPrincipal.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    preencherCamposComSelecao();
                }
            }
        });

        produtoDAO = new ProdutoDAO();
        carregarProdutosNaTabela();
        // ----------------------- FIM DA TABELA DOS PRODUTOS -----------------------

        // -------------------------------- FIM DO CONTEUDO PRINCIPAL --------------------------------


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

    // -------------------------------- MÉTODOS DE SUPORTE --------------------------------
    private void carregarProdutosNaTabela() {
        tableModel.setRowCount(0);
        List<Produto> listaProdutos = produtoDAO.listarProdutos();
        for (Produto p : listaProdutos) {
            tableModel.addRow(new Object[]{p.getId(), p.getNome(), p.getTipo(), "R$" + String.format("%.2f", p.getPreco()), p.getDescricao()});
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtTipo.setText("");
        txtPreco.setText("");
        txtDescricao.setText("");
        produtoSelecionadoId = -1;
        table.clearSelection();
    }

    private void preencherCamposComSelecao() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            produtoSelecionadoId = (int) table.getValueAt(selectedRow, 0);
            txtNome.setText((String) table.getValueAt(selectedRow, 1));
            txtTipo.setText((String) table.getValueAt(selectedRow, 2));
            String precoStr = ((String) table.getValueAt(selectedRow, 3)).replace("R$", "").trim();
            txtPreco.setText(precoStr);
            txtDescricao.setText((String) table.getValueAt(selectedRow, 4));
        }
    }

    private void salvarProduto() {
        String nome = txtNome.getText().trim();
        String tipo = txtTipo.getText().trim();
        String precoStr = txtPreco.getText().trim().replace(",", ".");
        String descricao = txtDescricao.getText().trim();

        if (nome.isEmpty() || tipo.isEmpty() || precoStr.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr);
            if (preco < 0) {
                 JOptionPane.showMessageDialog(this, "O preço não pode ser negativo.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                 return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido. Por favor, insira um número válido.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setTipo(tipo);
        produto.setPreco(preco);
        produto.setDescricao(descricao);

        if (produtoSelecionadoId == -1) {
            produtoDAO.cadastrarProduto(produto);
        } else {
            produto.setId(produtoSelecionadoId);
            produtoDAO.atualizarProduto(produto);
        }
        carregarProdutosNaTabela();
        limparCampos();
    }

    private void deletarProduto() {
        if (produtoSelecionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para deletar.", "Nenhuma Seleção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar o produto selecionado (ID: " + produtoSelecionadoId + ")?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            produtoDAO.excluirProduto(produtoSelecionadoId);
            carregarProdutosNaTabela();
            limparCampos();
        }
    }
    // -------------------------------- FIM DOS MÉTODOS DE SUPORTE --------------------------------
}