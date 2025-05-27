package estacio.br.dao;

import estacio.br.model.Usuario;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO () {
        conn = ConnectionFactory.getConnection();
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                lista.add(u);
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT into usuarios (nome, email, senha, id_tipo_usuario) VALUES (?, ?, ?, 1) ";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario usuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario u = new Usuario();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public boolean editar(Usuario usuario) throws SQLException {
        int linhasAfetadas = 0;
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());

            linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return linhasAfetadas > 0;
    }

    public boolean deletar(int id) throws SQLException {
        int linhasAfetadas = 0;
        String sql = "DELETE FROM usuarios WHERE id = ?";

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
