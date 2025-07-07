package br.com.hamburgueria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hamburgueria.model.Pedido;

public class PedidoDAO {

    public void cadastrarPedido(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO pedidos (idUsuario, dataPedido, statusPedido) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pedido.getIdUsuario());
            ps.setDate(2, Date.valueOf(pedido.getDataPedido()));
            ps.setString(3, pedido.getStatus());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void atualizarPedido(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE pedidos SET idUsuario = ?, dataPedido = ?, statusPedido = ? WHERE idPedido = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pedido.getIdUsuario());
            ps.setDate(2, Date.valueOf(pedido.getDataPedido()));
            ps.setString(3, pedido.getStatus());
            ps.setInt(4, pedido.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar pedido: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public void excluirPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE FROM pedidos WHERE idPedido = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum pedido encontrado com o ID: " + idPedido, "Erro de Exclusão", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pedido: " + ex.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public List<Pedido> listarPedidos() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT idPedido, idUsuario, dataPedido, statusPedido FROM pedidos";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("idPedido"));
                pedido.setIdUsuario(rs.getInt("idUsuario"));
                pedido.setDataPedido(rs.getTimestamp("dataPedido").toLocalDateTime().toLocalDate());
                pedido.setStatus(rs.getString("statusPedido"));
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados ao listar pedidos: " + ex.getMessage());
        } finally {
            try {
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