package estacio.br.util;

import java.sql.*;

public class ConnectionFactory {
    private static final String user = "root";
    private static final String password = "admin";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gestao_de_games", user, password);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }
}