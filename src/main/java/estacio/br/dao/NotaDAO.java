package estacio.br.dao;

import estacio.br.model.Nota;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NotaDAO {

    private Connection conn;

    public NotaDAO () {
        conn = ConnectionFactory.getConnection();
    }

    public void inserir(Nota nota){
        String sql = "INSERT INTO nota_games (nota, id_game, id_usuario) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, nota.getNota());
            stmt.setInt(2, nota.getIdGame());
            stmt.setInt(3, nota.getIdUsuario());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
