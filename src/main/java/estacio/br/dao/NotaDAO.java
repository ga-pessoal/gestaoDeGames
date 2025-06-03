package estacio.br.dao;

import estacio.br.model.Nota;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotaDAO {

    public void inserir(Nota nota){
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO nota_games (nota, id_game, id_usuario) VALUES (?, ?, ?)";

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, nota.getNota());
            stmt.setInt(2, nota.getIdGame());
            stmt.setInt(3, nota.getIdUsuario());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }
    }
}
