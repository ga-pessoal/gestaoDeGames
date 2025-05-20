package estacio.br.dao;

import estacio.br.model.Game;
import estacio.br.util.ConnectionFactory;
import java.sql.*;
import java.util.*;

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

    public List<Game> listarGamesComNotas() {
        List<Game> games = new ArrayList<>();

        String sql = "SELECT g.id, g.titulo, ge.titulo AS genero, g.nome_imagem, n.nota " +
                "FROM games g " +
                "LEFT JOIN genero ge ON g.id_genero = ge.id " +
                "LEFT JOIN nota_games n ON g.id = n.id_game";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setTitulo(rs.getString("titulo"));
                game.setGenero(rs.getString("genero"));
                game.setNomeImagem(rs.getString("nome_imagem"));

                int nota = rs.getInt("nota");
                if (rs.wasNull()) {
                    game.setNota(0);
                } else {
                    game.setNota(nota);
                }

                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public List<Game> listarGamesComMediaNotas() {
        List<Game> games = new ArrayList<>();

        String sql = "SELECT g.id, g.titulo, ge.titulo AS genero, g.nome_imagem, AVG(n.nota) AS nota " +
                "FROM games g " +
                "LEFT JOIN genero ge ON g.id_genero = ge.id " +
                "LEFT JOIN nota_games n ON g.id = n.id_game " +
                "GROUP BY g.id, g.titulo, g.id_genero";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setTitulo(rs.getString("titulo"));
                game.setGenero(rs.getString("genero"));
                game.setNomeImagem(rs.getString("nome_imagem"));

                double media = rs.getDouble("nota");
                if (rs.wasNull()) {
                    game.setNota(0);
                } else {
                    game.setNota(media);
                }

                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }
}
