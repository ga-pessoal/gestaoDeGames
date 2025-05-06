package estacio.br.util;

import java.sql.*;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gestao_de_games", "root", "admin");
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }
}