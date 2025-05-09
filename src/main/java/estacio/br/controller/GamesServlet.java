package estacio.br.controller;

import estacio.br.dao.UsuarioDAO;
import estacio.br.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            // Exibe o formulário de cadastro
            request.setAttribute("pageTitle", "Cadastro de Games");
            request.getRequestDispatcher("/jsp/cadastroGame.jsp").forward(request, response);
        } else {
            // Lista games
            request.setAttribute("pageTitle", "Games");
            request.getRequestDispatcher("/jsp/games.jsp").forward(request, response);
        }

    }
}
