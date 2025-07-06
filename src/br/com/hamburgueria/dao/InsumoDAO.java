package br.com.hamburgueria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Insumo;

public class InsumoDAO {
    public List<Insumo> listarInsumos() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Insumo> insumos = new ArrayList<>();
	    
		try {
	        conn = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM estoque";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	Insumo insumo = new Insumo();
				insumo.setId(rs.getInt("idItem"));
				insumo.setNome(rs.getString("nomeItem"));
				insumo.setQuantidade(rs.getInt("quantidade"));
				insumo.setValidade(rs.getDate("validade").toLocalDate());
				insumo.setIdFornecedor(rs.getInt("idFornecedor"));
                insumos.add(insumo);
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
		return insumos;
	}
}
