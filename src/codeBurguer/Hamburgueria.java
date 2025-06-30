package codeBurguer;

import javax.swing.*;
import java.awt.*;

public class Hamburgueria extends JFrame {

    private static final long serialVersionUID = 1L;

    public Hamburgueria() {
        // Configurações básicas da janela
        this.setTitle("Sistema de Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 150);
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
        private Font arial = new Font("Arial", Font.BOLD, 20);

        // Painel principal com GridLayout para organizar os componentes
        JPanel painelLogin = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 linhas, 2 colunas, com espaçamento
        this.add(painelLogin);

        // Adiciona uma borda vazia para espaçamento interno
        painelLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Componentes da tela de login
        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(arial);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        campoUsuario.setFont(arial);

        JButton botaoLogin = new JButton("Login");
        

        // Adiciona os componentes ao painel
        painelLogin.add(labelUsuario);
        painelLogin.add(campoUsuario);
        painelLogin.add(labelSenha);
        painelLogin.add(campoSenha);
        painelLogin.add(new JLabel()); // Célula vazia para alinhar o botão
        painelLogin.add(botaoLogin);

        // Torna a janela visível
        this.setVisible(true);
    }
}