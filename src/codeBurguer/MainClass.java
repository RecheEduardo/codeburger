package codeBurguer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainClass {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Minha Janela Swing");
		frame.setSize(400, 300);              
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  

		JLabel label = new JLabel("Bee-vindo!", JLabel.CENTER);
		frame.add(label);

		frame.setVisible(true);
	}

}
