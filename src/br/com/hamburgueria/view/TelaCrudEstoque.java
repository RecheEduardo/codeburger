package br.com.hamburgueria.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.hamburgueria.dao.InsumoDAO;
import br.com.hamburgueria.model.Insumo;
import br.com.hamburgueria.model.Usuario;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudEstoque extends JFrame {
    private static final long serialVersionUID = 1L;

    // -------------------------------- ATRIBUTOS DA CLASSE --------------------------------
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome;
    private JTextField txtQuantidade;
    private JTextField txtValidade;
    private JTextField txtIdFornecedor;
    private JButton btnSalvar;
    private JButton btnNovo;
    private JButton btnDeletar;
    private InsumoDAO insumoDAO;
    private int insumoSelecionadoId = -1;
    // -------------------------------- FIM DOS ATRIBUTOS DA CLASSE ------------------------

    public TelaCrudEstoque(Usuario usuario) {
        this.setTitle("Controle de Estoque");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 650);
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
        JLabel labelTitulo = new JLabel("<html>Operações de Estoque</html>");
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

        painelConteudo.add(Box.createVerticalStrut(10));

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

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblQuantidade, gbc);

        txtQuantidade = new JTextField(30);
        txtQuantidade.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFormulario.add(txtQuantidade, gbc);

        JLabel lblValidade = new JLabel("Validade (AAAA-MM-DD):");
        lblValidade.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(lblValidade, gbc);

        txtValidade = new JTextField(30);
        txtValidade.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFormulario.add(txtValidade, gbc);

        JLabel lblIdFornecedor = new JLabel("ID Fornecedor:");
        lblIdFornecedor.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(lblIdFornecedor, gbc);

        txtIdFornecedor = new JTextField(30);
        txtIdFornecedor.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelFormulario.add(txtIdFornecedor, gbc);

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
                salvarInsumo();
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
                deletarInsumo();
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

        // ----------------------- TABELA DO ESTOQUE -----------------------
        JLabel labelTituloTabelaEstoque = new JLabel("• Tabela de Estoque:");
        labelTituloTabelaEstoque.setFont(fonteAppRegular);
        labelTituloTabelaEstoque.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudoPrincipal.add(labelTituloTabelaEstoque);

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        String[] camposEstoque = {"ID do item", "Nome", "Quantidade", "Validade", "ID do fornecedor"};
        tableModel = new DefaultTableModel(camposEstoque, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

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

        insumoDAO = new InsumoDAO();
        carregarInsumosNaTabela();
        // ----------------------- FIM DA TABELA DO ESTOQUE -----------------------

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
    private void carregarInsumosNaTabela() {
        tableModel.setRowCount(0);
        List<Insumo> listaInsumos = insumoDAO.listarInsumos();
        for (Insumo i : listaInsumos) {
            tableModel.addRow(new Object[]{i.getId(), i.getNome(), i.getQuantidade(), i.getValidade(), i.getIdFornecedor()});
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtQuantidade.setText("");
        txtValidade.setText("");
        txtIdFornecedor.setText("");
        insumoSelecionadoId = -1;
        table.clearSelection();
    }

    private void preencherCamposComSelecao() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            insumoSelecionadoId = (int) table.getValueAt(selectedRow, 0);
            txtNome.setText((String) table.getValueAt(selectedRow, 1));
            txtQuantidade.setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            LocalDate validade = (LocalDate) table.getValueAt(selectedRow, 3);
            txtValidade.setText(validade.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            txtIdFornecedor.setText(String.valueOf(table.getValueAt(selectedRow, 4)));
        }
    }

    private void salvarInsumo() {
        String nome = txtNome.getText().trim();
        String quantidadeStr = txtQuantidade.getText().trim();
        String validadeStr = txtValidade.getText().trim();
        String idFornecedorStr = txtIdFornecedor.getText().trim();

        if (nome.isEmpty() || quantidadeStr.isEmpty() || validadeStr.isEmpty() || idFornecedorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int quantidade;
        LocalDate validade;
        int idFornecedor;

        try {
            quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade < 0) {
                 JOptionPane.showMessageDialog(this, "A quantidade não pode ser negativa.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                 return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida. Por favor, insira um número inteiro válido.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            validade = LocalDate.parse(validadeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use AAAA-MM-DD.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            idFornecedor = Integer.parseInt(idFornecedorStr);
            if (idFornecedor < 0) {
                 JOptionPane.showMessageDialog(this, "O ID do fornecedor não pode ser negativo.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                 return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID do Fornecedor inválido. Por favor, insira um número inteiro válido.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Insumo insumo = new Insumo();
        insumo.setNome(nome);
        insumo.setQuantidade(quantidade);
        insumo.setValidade(validade);
        insumo.setIdFornecedor(idFornecedor);

        if (insumoSelecionadoId == -1) {
            insumoDAO.cadastrarInsumo(insumo);
        } else {
            insumo.setId(insumoSelecionadoId);
            insumoDAO.atualizarInsumo(insumo);
        }
        carregarInsumosNaTabela();
        limparCampos();
    }

    private void deletarInsumo() {
        if (insumoSelecionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um insumo na tabela para deletar.", "Nenhuma Seleção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar o insumo selecionado (ID: " + insumoSelecionadoId + ")?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            insumoDAO.excluirInsumo(insumoSelecionadoId);
            carregarInsumosNaTabela();
            limparCampos();
        }
    }
    // -------------------------------- FIM DOS MÉTODOS DE SUPORTE --------------------------------
}