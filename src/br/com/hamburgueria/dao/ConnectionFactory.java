package br.com.hamburgueria.dao;

import java.sql.*;

public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/hamburgueria_db?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            // Se algo der errado (driver não encontrado, banco offline, usuário/senha incorretos)
            System.err.println("Erro na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace(); // Imprime o rastreamento do erro para ajudar na depuração.

            // Lança uma RuntimeException para sinalizar que um erro grave ocorreu
            throw new RuntimeException("Falha ao conectar ao banco de dados.", e);
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
    
}