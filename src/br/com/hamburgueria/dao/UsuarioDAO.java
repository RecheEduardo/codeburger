package br.com.hamburgueria.dao;

import java.sql.*;
import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Usuario;

public class UsuarioDAO {
	
	public Usuario verificarLogin(String login, String senha) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;
	    
		try {
	        conn = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM usuarios where loginUsuario = ? AND senhaUsuario = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, login);
	        ps.setString(2, senha);
	        rs = ps.executeQuery();
	        
	        if(rs.next()) {
	        	usuario = new Usuario();
				usuario.setID(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nomeUsuario"));
				usuario.setLogin(rs.getString("loginUsuario"));
				usuario.setSenha(rs.getString("senhaUsuario"));
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
		return usuario;
	}
}