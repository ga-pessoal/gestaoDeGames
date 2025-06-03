package estacio.br.dao;

import estacio.br.model.Game;
import estacio.br.util.ConnectionFactory;
import java.sql.*;
import java.util.*;

public class GameDAO {

    public List<Game> listar() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Game> lista = new ArrayList<>();
        String sql = "SELECT * FROM games";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return lista;
    }

    public void inserir(Game game) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO games (titulo, id_genero, nome_imagem) VALUES (?, ?, ?)";

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, game.getTitulo());
            stmt.setInt(2, game.getIdGenero());
            stmt.setString(3, game.getNomeImagem());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    public List<Game> listarGamesComNotas(int id_usuario) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Game> games = new ArrayList<>();

        String sql = "SELECT g.id, g.titulo, ge.titulo AS genero, g.nome_imagem, n.nota, n.id_usuario " +
                "FROM games g " +
                "INNER JOIN nota_games n ON g.id = n.id_game AND n.id_usuario = ? " +
                "LEFT JOIN genero ge ON g.id_genero = ge.id";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_usuario);

            rs = stmt.executeQuery();

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
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return games;
    }

    public List<Game> listarGamesComMediaNotas() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Game> games = new ArrayList<>();

        String sql = "SELECT g.id, g.titulo, ge.titulo AS genero, g.nome_imagem, AVG(n.nota) AS nota " +
                "FROM games g " +
                "LEFT JOIN genero ge ON g.id_genero = ge.id " +
                "LEFT JOIN nota_games n ON g.id = n.id_game " +
                "GROUP BY g.id, g.titulo, g.id_genero";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return games;
    }

    public Game gamePorId(int id){
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM games WHERE id = ?";
        Game g = new Game();

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                g.setId(rs.getInt("id"));
                g.setTitulo(rs.getString("titulo"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return g;
    }

    public boolean editar(Game game) throws  SQLException{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        int linhasAfetadas = 0;
        String sql = "UPDATE games SET titulo = ?, id_genero = ? WHERE id = ?";

        try{
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, game.getTitulo());
            stmt.setInt(2, game.getIdGenero());
            stmt.setInt(3, game.getId());

            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return linhasAfetadas > 0;
    }

    public boolean deletar(int id) throws  SQLException{
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        int linhasAfetadas = 0;
        String sql = "DELETE FROM games WHERE id = ?";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return linhasAfetadas > 0;
    }

}
