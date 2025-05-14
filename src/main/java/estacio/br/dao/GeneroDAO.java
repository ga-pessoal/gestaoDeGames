package estacio.br.dao;

import estacio.br.model.Genero;
import estacio.br.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    private Connection conn;

    public GeneroDAO () {
        conn = ConnectionFactory.getConnection();
    }

    public List<Genero> listar() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT id, titulo FROM genero";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setId(rs.getInt("id"));
                genero.setTitulo(rs.getString("titulo"));
                generos.add(genero);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generos;
    }

    public Genero generoPorId(int id){
        String sql = "SELECT * FROM generos WHERE id = ?";
        Genero g = new Genero();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                g.setId(rs.getInt("id"));
                g.setTitulo(rs.getString("titulo"));

            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return g;
    }

    public boolean editar(Genero genero) throws SQLException {
        int linhasAfetadas = 0;
        String sql = "UPDATE generos SET titulo = ?, WHERE id = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, genero.getTitulo());
            stmt.setInt(3, genero.getId());

            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return linhasAfetadas > 0;
    }

    public boolean deletar(int id) throws SQLException {
        int linhasAfetadas = 0;
        String sql = "DELETE FROM generos WHERE id = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return linhasAfetadas > 0;
    }
}