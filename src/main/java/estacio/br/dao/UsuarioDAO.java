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

    public Usuario autenticar(String email, String senha) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";


        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setId_tipo_usuario(rs.getInt("id_tipo_usuario"));
                usuario.setSenha(rs.getString("senha")); // opcional guardar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return usuario;
    }

    public List<Usuario> listar() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                lista.add(u);
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

    public void inserir(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT into usuarios (nome, email, senha, id_tipo_usuario) VALUES (?, ?, ?, ?) ";

        try{
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId_tipo_usuario());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    public Usuario usuarioPorId(int id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario u = new Usuario();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        }

        return u;
    }

    public boolean editar(Usuario usuario) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        int linhasAfetadas = 0;
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try{
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());

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
        String sql = "DELETE FROM usuarios WHERE id = ?";

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
}
