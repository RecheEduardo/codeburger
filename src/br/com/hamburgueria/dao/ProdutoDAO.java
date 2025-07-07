package br.com.hamburgueria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Produto;

public class ProdutoDAO {

    public void cadastrarProduto(Produto produto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO produtos (nomeProduto, tipoProduto, preco, descricao) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getTipo());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getDescricao());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void atualizarProduto(Produto produto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE produtos SET nomeProduto = ?, tipoProduto = ?, preco = ?, descricao = ? WHERE idProduto = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getTipo());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getDescricao());
            ps.setInt(5, produto.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void excluirProduto(int idProduto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE FROM produtos WHERE idProduto = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com o ID: " + idProduto, "Erro de Exclusão", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public List<Produto> listarProdutos() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();
	    
		try {
	        conn = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM produtos";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	Produto produto = new Produto();
				produto.setId(rs.getInt("idProduto"));
				produto.setNome(rs.getString("nomeProduto"));
				produto.setTipo(rs.getString("tipoProduto"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setDescricao(rs.getString("descricao"));
                produtos.add(produto);
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
		return produtos;
	}
}