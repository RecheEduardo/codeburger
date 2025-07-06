package br.com.hamburgueria.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Pedido;

public class PedidoDAO {
    public List<Pedido> listarPedidos() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pedido> pedidos = new ArrayList<>();
	    
		try {
	        conn = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM pedidos";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("idPedido"));
				pedido.setIdUsuario(rs.getInt("idUsuario"));
				pedido.setDataPedido(rs.getDate("dataPedido").toLocalDate());
				pedido.setStatusPedido(rs.getString("statusPedido"));
                pedidos.add(pedido);
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
		return pedidos;
	}
}
