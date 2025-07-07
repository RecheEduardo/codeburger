package br.com.hamburgueria.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.hamburgueria.dao.PedidoDAO;
import br.com.hamburgueria.model.Pedido;
import br.com.hamburgueria.model.Usuario; //

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrudPedidos extends JFrame {
    private static final long serialVersionUID = 1L;

    // -------------------------------- ATRIBUTOS DA CLASSE --------------------------------
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtIdUsuario;
    private JTextField txtDataPedido;
    private JComboBox<String> cmbStatus;
    private JButton btnSalvar;
    private JButton btnNovo;
    private JButton btnDeletar;
    private PedidoDAO pedidoDAO;
    private int pedidoSelecionadoId = -1;
    private Usuario usuarioLogado;
    // -------------------------------- FIM DOS ATRIBUTOS DA CLASSE ------------------------

    public TelaCrudPedidos(Usuario usuario) {
        this.usuarioLogado = usuario;
        this.setTitle("Controle de Pedidos");
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
        JLabel labelTitulo = new JLabel("<html>Operações de Pedidos</html>");
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

        JLabel lblIdUsuario = new JLabel("ID Usuário:");
        lblIdUsuario.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(lblIdUsuario, gbc);

        txtIdUsuario = new JTextField(String.valueOf(usuarioLogado.getID())); //
        txtIdUsuario.setFont(fonteCampos);
        txtIdUsuario.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFormulario.add(txtIdUsuario, gbc);

        JLabel lblDataPedido = new JLabel("Data do Pedido (AAAA-MM-DD):");
        lblDataPedido.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblDataPedido, gbc);

        txtDataPedido = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        txtDataPedido.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFormulario.add(txtDataPedido, gbc);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(fonteAppRegular);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(lblStatus, gbc);

        String[] statusOpcoes = {"Pendente", "Em Preparação", "Pronto", "Entregue", "Cancelado"};
        cmbStatus = new JComboBox<>(statusOpcoes);
        cmbStatus.setFont(fonteCampos);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFormulario.add(cmbStatus, gbc);

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
                salvarPedido();
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
                deletarPedido();
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

        // ----------------------- TABELA DE PEDIDOS -----------------------
        JLabel labelTituloTabelaPedidos = new JLabel("• Tabela de Pedidos:");
        labelTituloTabelaPedidos.setFont(fonteAppRegular);
        labelTituloTabelaPedidos.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelConteudoPrincipal.add(labelTituloTabelaPedidos);

        painelConteudoPrincipal.add(Box.createVerticalStrut(15));

        String[] camposPedidos = {"ID", "ID Usuário", "Data do Pedido", "Status"};
        tableModel = new DefaultTableModel(camposPedidos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // configura o tamanho máximo da tabela para 10 registros
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

        pedidoDAO = new PedidoDAO();
        carregarPedidosNaTabela();
        // ----------------------- FIM DA TABELA DE PEDIDOS -----------------------

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
                new TelaMenuPrincipal(usuarioLogado).setVisible(true);
                dispose();
            }
        });
        painelBotao.add(botaoVoltar);
        // -------------------------------- FIM DO RODAPE DA JANELA --------------------------------
    }

    // -------------------------------- MÉTODOS DE SUPORTE --------------------------------
    private void carregarPedidosNaTabela() {
        tableModel.setRowCount(0);
        List<Pedido> listaPedidos = pedidoDAO.listarPedidos();
        for (Pedido p : listaPedidos) {
            tableModel.addRow(new Object[]{p.getId(), p.getIdUsuario(), p.getDataPedido(), p.getStatusPedido()});
        }
    }

    private void limparCampos() {
        txtDataPedido.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        cmbStatus.setSelectedIndex(0);
        pedidoSelecionadoId = -1;
        table.clearSelection();
    }

    private void preencherCamposComSelecao() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            pedidoSelecionadoId = (int) table.getValueAt(selectedRow, 0);
            txtIdUsuario.setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            LocalDate dataPedido = (LocalDate) table.getValueAt(selectedRow, 2);
            txtDataPedido.setText(dataPedido.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmbStatus.setSelectedItem(table.getValueAt(selectedRow, 3));
        }
    }

    private void salvarPedido() {
        String idUsuarioStr = txtIdUsuario.getText().trim();
        String dataPedidoStr = txtDataPedido.getText().trim();
        String status = (String) cmbStatus.getSelectedItem();

        if (idUsuarioStr.isEmpty() || dataPedidoStr.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idUsuario;
        LocalDate dataPedido;

        try {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Usuário inválido. Por favor, insira um número inteiro válido.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            dataPedido = LocalDate.parse(dataPedidoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use AAAA-MM-DD.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuario);
        pedido.setDataPedido(dataPedido);
        pedido.setStatusPedido(status);

        if (pedidoSelecionadoId == -1) {
            pedidoDAO.cadastrarPedido(pedido);
        } else {
            pedido.setId(pedidoSelecionadoId);
            pedidoDAO.atualizarPedido(pedido);
        }
        carregarPedidosNaTabela();
        limparCampos();
    }

    private void deletarPedido() {
        if (pedidoSelecionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido na tabela para deletar.", "Nenhuma Seleção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar o pedido selecionado (ID: " + pedidoSelecionadoId + ")?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            pedidoDAO.excluirPedido(pedidoSelecionadoId);
            carregarPedidosNaTabela();
            limparCampos();
        }
    }
    // -------------------------------- FIM DOS MÉTODOS DE SUPORTE --------------------------------
}