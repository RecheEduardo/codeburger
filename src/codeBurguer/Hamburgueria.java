package codeBurguer;

import javax.swing.*;
import java.awt.*;

public class Hamburgueria extends JFrame {

    private static final long serialVersionUID = 1L;

    public Hamburgueria() {
        this.setTitle("Sistema de Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 650);
        this.setLocationRelativeTo(null);

        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 26);
        final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);

        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelLogin);

        JLabel labelTitulo = new JLabel("<html>Code Burger</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelSubTitulo = new JLabel("Sua Hamburgueria predileta!");
        labelSubTitulo.setFont(fonteAppRegular);
        labelSubTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente

        // LABEL DE LOGIN DO USUARIO
        JLabel labelUsuario = new JLabel("Login do usuário:");
        labelUsuario.setFont(fonteAppBold);
        labelUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);

        // INPUT DE LOGIN USUARIO
        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(fonteAppRegular);
        campoUsuario.setMaximumSize(new Dimension(1280, 40));
        campoUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);

        // LABEL DE SENHA
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonteAppBold);
        labelSenha.setAlignmentX(Component.LEFT_ALIGNMENT);

        // INPUT DE LOGIN SENHA
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setFont(fonteAppRegular);
        campoSenha.setMaximumSize(new Dimension(1280, 40));
        campoSenha.setAlignmentX(Component.LEFT_ALIGNMENT);

        // BOTAO DE LOGAR
        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.setFont(fonteAppBold);
        botaoLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoLogin.setMaximumSize(new Dimension(1280, 50));

        // Adiciona espaçamento entre os elementos
        painelLogin.add(labelTitulo);
        painelLogin.add(labelSubTitulo);
        painelLogin.add(Box.createVerticalStrut(15));
        
        painelLogin.add(separador);
        painelLogin.add(Box.createVerticalStrut(10));
        
        painelLogin.add(labelUsuario);
        painelLogin.add(Box.createVerticalStrut(10));
        
        painelLogin.add(campoUsuario);
        painelLogin.add(Box.createVerticalStrut(20));
        
        painelLogin.add(labelSenha);
        painelLogin.add(Box.createVerticalStrut(10));
        
        painelLogin.add(campoSenha);
        painelLogin.add(Box.createVerticalStrut(20));
        
        painelLogin.add(botaoLogin);

        this.setVisible(true);
    }
}
