package estacio.br.dao;

import estacio.br.model.Genero;
import estacio.br.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public List<Genero> listar() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT id, titulo FROM genero";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setId(rs.getInt("id"));
                genero.setTitulo(rs.getString("titulo"));
                generos.add(genero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return generos;
    }

    public Genero generoPorId(int id){
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM genero WHERE id = ?";
        Genero g = new Genero();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                g.setId(rs.getInt("id"));
                g.setTitulo(rs.getString("titulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return g;
    }

    public boolean editar(Genero genero) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        int linhasAfetadas = 0;
        String sql = "UPDATE genero SET titulo = ?, WHERE id = ?";

        try{
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, genero.getTitulo());
            stmt.setInt(3, genero.getId());

            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return linhasAfetadas > 0;
    }

    public boolean deletar(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        int linhasAfetadas = 0;
        String sql = "DELETE FROM genero WHERE id = ?";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return linhasAfetadas > 0;
    }

    public void inserir(Genero genero) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT into genero (titulo) VALUES (?) ";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, genero.getTitulo());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }
    }
}