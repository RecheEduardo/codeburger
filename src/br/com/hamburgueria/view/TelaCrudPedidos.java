package br.com.hamburgueria.view;

import javax.swing.*;
import java.awt.*;

public class TelaCrudPedidos extends JFrame{
	private static final long serialVersionUID = 1L;

	public TelaCrudPedidos() {
		this.setTitle("Controle de Pedidos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 650);
        this.setLocationRelativeTo(null);
        
        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 36);
        // final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);

        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelLogin);
        
        // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente

        // Header Principal
        JLabel labelTitulo = new JLabel("<html>Operações de Pedidos</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painelLogin.add(labelTitulo);
        painelLogin.add(Box.createVerticalStrut(15));
        
        painelLogin.add(separador);
        painelLogin.add(Box.createVerticalStrut(10));
	}
}
