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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generos;
    }
}