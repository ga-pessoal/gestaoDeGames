package estacio.br.dao;

import estacio.br.model.Game;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {
    private Connection conn;

    public GameDAO () {
        conn = ConnectionFactory.getConnection();
    }

    public void inserir(Game game) {

        String sql = "INSERT INTO games (titulo, id_genero, nome_imagem) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, game.getTitulo());
            stmt.setInt(2, game.getIdGenero());
            stmt.setString(3, game.getNomeImagem());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
