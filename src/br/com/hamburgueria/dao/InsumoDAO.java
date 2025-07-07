package br.com.hamburgueria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Insumo;

public class InsumoDAO {

    public void cadastrarInsumo(Insumo insumo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO estoque (nomeItem, quantidade, validade, idFornecedor) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, insumo.getNome());
            ps.setInt(2, insumo.getQuantidade());
            ps.setDate(3, Date.valueOf(insumo.getValidade()));
            ps.setInt(4, insumo.getIdFornecedor());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insumo cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar insumo: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void atualizarInsumo(Insumo insumo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE estoque SET nomeItem = ?, quantidade = ?, validade = ?, idFornecedor = ? WHERE idItem = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, insumo.getNome());
            ps.setInt(2, insumo.getQuantidade());
            ps.setDate(3, Date.valueOf(insumo.getValidade()));
            ps.setInt(4, insumo.getIdFornecedor());
            ps.setInt(5, insumo.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Insumo atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar insumo: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void excluirInsumo(int idInsumo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE FROM estoque WHERE idItem = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idInsumo);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Insumo excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum insumo encontrado com o ID: " + idInsumo, "Erro de Exclusão", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir insumo: " + ex.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

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