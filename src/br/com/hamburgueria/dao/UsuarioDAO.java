package br.com.hamburgueria.dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDAO {
	
	public boolean verificarLogin(String login, String senha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;
	    
		try {
	        conn = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM usuarios where login = ? AND senha = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, login);
	        ps.setString(2, senha);
	        rs = ps.executeQuery();
	        
	        if(rs.next()) {
	        	check = true;
	        }
	    } catch (SQLException ex) {
	    	JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + ex.getMessage());
	    } finally {
	    	try {
	    		// fechando todos os recursos utilizados na chamada do banco
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
	        }
	    }
		return check;
	}
}
