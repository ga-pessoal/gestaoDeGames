package estacio.br.controller;

import estacio.br.dao.GeneroDAO;
import estacio.br.dao.UsuarioDAO;
import estacio.br.model.Genero;
import estacio.br.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GenerosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.setAttribute("pageTitle", "Cadastro de Gênero");
            request.getRequestDispatcher("/jsp/cadastroGenero.jsp").forward(request, response);

        } else if ("editar".equals(action)) {
            int id_genero = Integer.parseInt(request.getParameter("id"));

            GeneroDAO dao = new GeneroDAO();
            Genero genero = dao.generoPorId(id_genero);

            request.setAttribute("genero", genero);
            request.setAttribute("pageTitle", "Edição de gênero");
            request.getRequestDispatcher("/jsp/cadastroGenero.jsp").forward(request, response);

        } else {
            // Lista Generos
            GeneroDAO dao = new GeneroDAO();
            List<Genero> lista = dao.listar();
            request.setAttribute("generos", lista);
            request.setAttribute("pageTitle", "Gêneros");
            request.getRequestDispatcher("/jsp/generos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("excluir".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            GeneroDAO generoDAO = new GeneroDAO();

            try {
                generoDAO.deletar(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<Genero> lista = generoDAO.listar();
            request.setAttribute("generos", lista);
            request.setAttribute("pageTitle", "Gêneros");
            request.getRequestDispatcher("/jsp/generos.jsp").forward(request, response);
        } else if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id_genero"));
            String titulo = request.getParameter("titulo");

            Genero generoObj = new Genero();
            generoObj.setId(id);
            generoObj.setTitulo(titulo);

            GeneroDAO generoDAO = new GeneroDAO();
            try {
                generoDAO.editar(generoObj);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            List<Genero> lista = generoDAO.listar();
            request.setAttribute("generos", lista);
            request.setAttribute("pageTitle", "Gêneros");
            request.getRequestDispatcher("/jsp/generos.jsp").forward(request, response);
        } else {
            // Pega os dados de cadastroGenero.jsp
            String titulo = request.getParameter("titulo");

            // Montar o objeto com os valores das variáveis acima
            Genero generoObj = new Genero();
            generoObj.setTitulo(titulo);

            // Inserir os dados na base de dados DAO
            GeneroDAO generoDAO = new GeneroDAO();
            generoDAO.inserir(generoObj);

            List<Genero> lista = generoDAO.listar();
            request.setAttribute("generos", lista);
            request.setAttribute("pageTitle", "Gêneros");
            request.getRequestDispatcher("/jsp/generos.jsp").forward(request, response);
        }
    }
}
