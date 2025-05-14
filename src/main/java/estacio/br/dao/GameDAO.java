package estacio.br.dao;

import estacio.br.model.Game;

import estacio.br.model.Usuario;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private Connection conn;

    public GameDAO () {
        conn = ConnectionFactory.getConnection();
    }

    public List<Game> listar() {
        List<Game> lista = new ArrayList<>();
        String sql = "SELECT * FROM games";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Game g = new Game();

                g.setId(rs.getInt("id"));
                g.setTitulo(rs.getString("titulo"));
                g.setIdGenero(rs.getInt("id_genero"));
                g.setNomeImagem(rs.getString("nome_imagem"));

                lista.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

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
