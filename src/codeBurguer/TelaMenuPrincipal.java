package codeBurguer;
import javax.swing.*;
import java.awt.*;

public class TelaMenuPrincipal extends JFrame {
 
	private static final long serialVersionUID = 1L;

	public TelaMenuPrincipal() {
		this.setTitle("Menu de operações");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 650);
        this.setLocationRelativeTo(null);
        
        final Font fonteAppBold = new Font("Century Gothic", Font.BOLD, 26);
        final Font fonteAppRegular = new Font("Century Gothic", Font.PLAIN, 24);

        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.add(painelLogin);

        JLabel labelTitulo = new JLabel("<html>Menu principal</html>");
        labelTitulo.setFont(fonteAppBold);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
     // SEPARADOR DE SEÇÃO
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // faz a linha preencher horizontalmente
        
     // Adiciona espaçamento entre os elementos
        painelLogin.add(labelTitulo);
        painelLogin.add(Box.createVerticalStrut(15));
        
        painelLogin.add(separador);
        painelLogin.add(Box.createVerticalStrut(10));
	}
}
