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
	    		// fechando todos os recursos utilizados na chamada do banco
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
