package estacio.br;

import estacio.br.model.Usuario;
import estacio.br.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                lista.add(u);
                System.out.println("id: "+u.getId()+" email: "+u.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}